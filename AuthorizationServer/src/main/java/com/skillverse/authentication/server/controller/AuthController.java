package com.skillverse.authentication.server.controller;

import com.skillverse.authentication.server.dto.request.LoginRequest;
import com.skillverse.authentication.server.dto.request.RefreshTokenRequest;
import com.skillverse.authentication.server.dto.response.TokenResponse;
import com.skillverse.authentication.server.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //  Login API
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(
            @RequestBody @Valid LoginRequest request) {

        TokenResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }

    //  Refresh Token API
    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(
            @RequestBody @Valid RefreshTokenRequest request) {

        TokenResponse response = authService.refreshToken(request);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "Only admin can access";
    }

     @GetMapping("/student")
    @PreAuthorize("hasRole('STUDENT')")
    public String student() {
         return "Only student can access";
     }

     @GetMapping("/creator")
    @PreAuthorize("hasRole('CREATOR')")
    public String creator() {
        return "Only creator can access";
    }

    @GetMapping("/superadmin")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public String superAdmin() {
        return "Only superadmin can access";
    }
}