package com.skillverse.authentication.server.service;

import com.skillverse.authentication.server.dto.response.TokenResponse;
import com.skillverse.authentication.server.entity.User;

public interface TokenService {

    TokenResponse generateToken(User user);

    TokenResponse refreshToken(String refreshToken);

    boolean validateToken(String token);
}