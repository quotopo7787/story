package org.com.story.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.com.story.dto.request.LoginRequest;
import org.com.story.dto.response.LoginResponse;
import org.com.story.entity.RefreshToken;
import org.com.story.security.JwtUtil;
import org.com.story.service.RefreshTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/sign-in")
    public LoginResponse login(@Valid @RequestBody LoginRequest req) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                req.getUsername(),
                                req.getPassword()
                        )
                );
        String accessToken =
                jwtUtil.generateAccessToken(req.getUsername());

        RefreshToken refreshToken =
                refreshTokenService.create(req.getUsername());

        return new LoginResponse(
                accessToken,
                refreshToken.getToken()
        );
    }

    @PostMapping("/refresh")
    public LoginResponse refresh(
            @RequestParam String refreshToken) {

        RefreshToken rt =
                refreshTokenService.verify(refreshToken);

        String newAccessToken =
                jwtUtil.generateAccessToken(rt.getUsername());

        return new LoginResponse(
                newAccessToken,
                refreshToken
        );
    }

    @PostMapping("/logout")
    public void logout(
            @RequestParam String refreshToken) {

        refreshTokenService.logout(refreshToken);
    }
}
