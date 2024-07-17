package com.example.todaylunch.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String email;
    private String profileUrl;
    private String memberKey;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(Long id, String nickname, String email, String profileUrl, String memberKey, Role role) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.profileUrl = profileUrl;
        this.memberKey = memberKey;
        this.role = role;
    }
}
