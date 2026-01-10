package org.com.story.service;

import org.com.story.entity.RefreshToken;

public interface RefreshTokenService {
    RefreshToken create(String username);

    public RefreshToken verify(String token);

    void logout(String refreshToken);
}
