package com.example.todaylunch.domain.auth.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    // 응답 정보를 담고 있는 객체
    private final KakaoResponse kakaoResponse;
    private final String role;

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    // 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role;
            }
        });
        return collection;
    }

    @Override
    public String getName() {
        return kakaoResponse.getName();
    }

    // provider + providerId
    public String getUsername() {
        return kakaoResponse.getProvider() + " " + kakaoResponse.getProviderId();
    }
}
