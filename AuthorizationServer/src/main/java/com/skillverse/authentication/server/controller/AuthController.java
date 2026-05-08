package com.skillverse.authentication.server.controller;

import com.skillverse.authentication.server.config.MyKafkaProducer;
import com.skillverse.authentication.server.dto.request.LoginRequest;
import com.skillverse.authentication.server.dto.request.RefreshTokenRequest;
import com.skillverse.authentication.server.dto.request.RegisterRequest;
import com.skillverse.authentication.server.dto.response.TokenResponse;
import com.skillverse.authentication.server.entity.UserCreatedEvent;
import com.skillverse.authentication.server.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final MyKafkaProducer myKafkaProducer;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest request) {
        log.info("AuthController :: register username={}", request.getUsername());
        authService.register(request);
        UserCreatedEvent userCreatedEvent= UserCreatedEvent
                .builder()
                .id(new Random().nextLong())
                .username(request.getUsername())
                .email(request.getEmail())
                .contactNumber("1234567890")
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        myKafkaProducer.sendUserDetailsToUserService(userCreatedEvent);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(
            @RequestBody @Valid LoginRequest request) {
        log.info("AuthController :: login username={}", request.getUsername());
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(
            @RequestBody @Valid RefreshTokenRequest request) {
        log.info("AuthController :: refresh token request");
        return ResponseEntity.ok(authService.refreshToken(request));
    }

    // Optional future
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestBody @Valid RefreshTokenRequest request) {
        log.info("AuthController :: logout request");
        authService.logout(request);
        return ResponseEntity.noContent().build();
    }
}