package com.example.todaylunch.domain.auth.dto;

import com.example.todaylunch.domain.member.entity.Role;
import com.example.todaylunch.domain.member.entity.Member;
import com.example.todaylunch.common.utils.KeyGenerator;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class OAuth2UserInfo {
    private String name;
    private String email;
    private String profile;

    public OAuth2UserInfo(String name, String email, String profile) {
        this.name = name;
        this.email = email;
        this.profile = profile;
    }

    public static OAuth2UserInfo of(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equals("kakao")) {
            return ofKakao(attributes);
        } else {
            //throw new AuthException(ILLEGAL_REGISTRATION_ID);
            return null;
        }
    }

    private static OAuth2UserInfo ofKakao(Map<String, Object> attributes) {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        return OAuth2UserInfo.builder()
                .name((String) profile.get("nickname"))
                .email((String) account.get("email"))
                .profile((String) profile.get("profile_image_url"))
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .nickname(name)
                .email(email)
                .profileUrl(profile)
                .memberKey(KeyGenerator.generateKey())
                .role(Role.GUEST)
                .build();
    }
}
