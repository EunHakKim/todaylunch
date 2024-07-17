package com.example.todaylunch.domain.auth.controller;

import com.example.todaylunch.domain.auth.dto.LoginResponse;
import com.example.todaylunch.domain.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final TokenService tokenService;

    @GetMapping("/auth/success")
    public ResponseEntity<LoginResponse> loginSuccess(@RequestParam("accessToken") String accessToken) {
        return ResponseEntity.ok(LoginResponse.builder()
                        .accessToken(accessToken)
                        .build());
    }

    @DeleteMapping("/auth/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal UserDetails userDetails) {
        tokenService.deleteRefreshToken(userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}
