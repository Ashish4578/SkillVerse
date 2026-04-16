package com.skillverse.authentication.server.service.impl;

import com.skillverse.authentication.server.dto.response.TokenResponse;
import com.skillverse.authentication.server.entity.RefreshToken;
import com.skillverse.authentication.server.entity.User;
import com.skillverse.authentication.server.repo.RefreshTokenRepository;
import com.skillverse.authentication.server.security.JwtTokenProvider;
import com.skillverse.authentication.server.service.RefreshTokenService;
import com.skillverse.authentication.server.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements TokenService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.expiration}")
    private long expiration;

    @Override
    public TokenResponse generateToken(User user) {

        String accessToken = jwtTokenProvider.generateAccessToken(user);

        String refreshToken = refreshTokenService.createRefreshToken(user);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(expiration)
                .build();
    }

    @Override
    public TokenResponse refreshToken(String refreshToken) {

        RefreshToken token = refreshTokenService.verifyRefreshToken(refreshToken);

        String newAccessToken = jwtTokenProvider.generateAccessToken(token.getUser());

        return TokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(expiration)
                .build();
    }

    @Override
    public boolean validateToken(String token) {
        return jwtTokenProvider.validateToken(token);
    }

    @Override
    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}