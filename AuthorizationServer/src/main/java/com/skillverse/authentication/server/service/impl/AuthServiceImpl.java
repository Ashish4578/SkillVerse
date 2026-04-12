package com.skillverse.authentication.server.service.impl;

import com.skillverse.authentication.server.dto.request.LoginRequest;
import com.skillverse.authentication.server.dto.request.RefreshTokenRequest;
import com.skillverse.authentication.server.dto.response.TokenResponse;
import com.skillverse.authentication.server.entity.User;
import com.skillverse.authentication.server.security.CustomUserDetails;
import com.skillverse.authentication.server.service.AuthService;
import com.skillverse.authentication.server.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

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
}
