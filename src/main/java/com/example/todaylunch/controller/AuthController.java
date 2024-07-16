package com.example.todaylunch.controller;

import com.example.todaylunch.dto.LoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    @GetMapping("/auth/success")
    public ResponseEntity<LoginResponse> loginSuccess(@RequestParam("accessToken") String accessToken) {
        return ResponseEntity.ok(LoginResponse.builder()
                        .accessToken(accessToken)
                        .build());
    }
}
