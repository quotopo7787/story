package org.com.story.service.impl;

import lombok.RequiredArgsConstructor;
import org.com.story.entity.RefreshToken;
import org.com.story.repository.RefreshTokenRepository;
import org.com.story.service.RefreshTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository repository;

    private final long REFRESH_TOKEN_EXP =
            1000L * 60 * 60 * 24 * 7; // 7 ngày

    public RefreshToken create(String username) {
        repository.deleteByUsername(username);

        return repository.save(
                RefreshToken.builder()
                        .username(username)
                        .token(UUID.randomUUID().toString())
                        .expiryDate(
                                Instant.now().plusMillis(REFRESH_TOKEN_EXP)
                        )
                        .build()
        );
    }

    public RefreshToken verify(String token) {
        RefreshToken rt = repository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (rt.getExpiryDate().isBefore(Instant.now())) {
            repository.delete(rt);
            throw new RuntimeException("Refresh token expired");
        }
        return rt;
    }

    @Override
    public void logout(String refreshToken) {
        repository.deleteByToken(refreshToken);
    }
}
