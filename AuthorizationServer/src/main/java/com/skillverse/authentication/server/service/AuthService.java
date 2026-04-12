package com.skillverse.authentication.server.service;

import com.skillverse.authentication.server.dto.request.LoginRequest;
import com.skillverse.authentication.server.dto.request.RefreshTokenRequest;
import com.skillverse.authentication.server.dto.response.TokenResponse;

public interface AuthService {

    TokenResponse login(LoginRequest request);

    TokenResponse refreshToken(RefreshTokenRequest request);
}