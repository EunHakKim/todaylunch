package com.example.todaylunch.repository;

import com.example.todaylunch.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {
    Optional<Token> findByAccessToken(String accessToken);
}
