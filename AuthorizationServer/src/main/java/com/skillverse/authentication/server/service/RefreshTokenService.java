package com.skillverse.authentication.server.service;

import com.skillverse.authentication.server.entity.RefreshToken;
import com.skillverse.authentication.server.entity.User;

public interface RefreshTokenService {

    String createRefreshToken(User user);

    RefreshToken verifyRefreshToken(String token);
}