package com.skillverse.authentication.server.service.impl;

import com.skillverse.authentication.server.entity.RefreshToken;
import com.skillverse.authentication.server.entity.User;
import com.skillverse.authentication.server.repo.RefreshTokenRepository;
import com.skillverse.authentication.server.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final long REFRESH_TOKEN_DURATION = 7 * 24 * 60 * 60; // 7 days

    @Override
    public String createRefreshToken(User user) {

        String token = UUID.randomUUID().toString();

        RefreshToken refreshToken = RefreshToken.builder()
                .token(token)
                .user(user)
                .expiryDate(Instant.now().plusSeconds(REFRESH_TOKEN_DURATION))
                .build();

        refreshTokenRepository.save(refreshToken);

        return token;
    }

    @Override
    public RefreshToken verifyRefreshToken(String token) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            throw new RuntimeException("Refresh token expired");
        }

        return refreshToken;
    }
}