package com.example.todaylunch.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String profileUrl;
    private String username;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(Long id, String name, String email, String profileUrl, String username, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profileUrl = profileUrl;
        this.username = username;
        this.role = role;
    }

    public void updateUsername(String username) {
        this.username = username;
    }

    public void updateEmail(String email) {
        this.email = email;
    }
}
