package com.skillverse.authentication.server.service.impl;

import com.skillverse.authentication.server.config.UserCreatedProducer;
import com.skillverse.authentication.server.dto.request.LoginRequest;
import com.skillverse.authentication.server.dto.request.RefreshTokenRequest;
import com.skillverse.authentication.server.dto.request.RegisterRequest;
import com.skillverse.authentication.server.dto.response.TokenResponse;
import com.skillverse.authentication.server.entity.Role;
import com.skillverse.authentication.server.entity.User;
import com.skillverse.authentication.server.entity.UserCreatedDomainEvent;
import com.skillverse.authentication.server.repo.RoleRepository;
import com.skillverse.authentication.server.repo.UserRepository;
import com.skillverse.authentication.server.security.CustomUserDetails;
import com.skillverse.authentication.server.service.AuthService;
import com.skillverse.authentication.server.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.DuplicateResourceException;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final UserCreatedProducer userCreatedProducer;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public TokenResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User user = userDetails.getUser();

        return tokenService.generateToken(user);
    }

    @Override
    public TokenResponse refreshToken(RefreshTokenRequest request) {
        return tokenService.refreshToken(request.getRefreshToken());
    }

    @Override
    public void logout(RefreshTokenRequest request) {
        tokenService.deleteRefreshToken(request.getRefreshToken());
    }

    @Override
    @Transactional
    public void register(RegisterRequest request) {

        log.info("Registering user username={} email={}",
                request.getUsername(), request.getEmail());

        //  Check duplicate username
        userRepository.findByUsername(request.getUsername())
                .ifPresent(u -> {
                    throw new DuplicateResourceException("Username already exists");
                });

        //  Check duplicate email
        userRepository.findByEmail(request.getEmail())
                .ifPresent(u -> {
                    throw new DuplicateResourceException("Email already exists");
                });

        //  Fetch role
        Role role = roleRepository.findByName(request.getRole())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        //  Create user
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .roles(Set.of(role))
                .build();

        User savedUser = userRepository.save(user);

        log.info("User created successfully id={}", savedUser.getId());

        //  Publish event AFTER DB save
        applicationEventPublisher.publishEvent(new UserCreatedDomainEvent(savedUser));
    }

}