package com.example.todaylunch.domain.auth.service;

import com.example.todaylunch.domain.auth.dto.CustomOAuth2User;
import com.example.todaylunch.domain.auth.dto.KakaoResponse;
import com.example.todaylunch.domain.user.entity.Role;
import com.example.todaylunch.domain.user.entity.User;
import com.example.todaylunch.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    // OAuth2 인증 요청을 받아 사용자 정보를 불러오는 메서드
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // 상위 클래스에 위임하여 사용자 정보를 불러온다.
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());

        // provider 식별
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        KakaoResponse kakaoResponse = null;
        if (registrationId.equals("kakao")){
            kakaoResponse = new KakaoResponse(oAuth2User.getAttributes());

        } else {
            return null;
        }

        String username = kakaoResponse.getProvider() + " " + kakaoResponse.getProviderId();

        // 기존 사용자 조회
        Optional<User> existData = userRepository.findByUsername(username);
        Role role = Role.GUEST;

        // 사용자가 없으면 새로운 사용자 저장
        if (existData.isEmpty()) {
            userRepository.save(User.builder()
                            .name(kakaoResponse.getName())
                            .email(kakaoResponse.getEmail())
                            .profileUrl(kakaoResponse.getProfileUrl())
                            .username(username)
                            .role(role)
                    .build());
            // 사용자가 있으면 기존 사용자 정보 업데이트
        } else {
            existData.get().updateUsername(username);
            existData.get().updateEmail(kakaoResponse.getEmail());
            role = existData.get().getRole();

            userRepository.save(existData.get());
        }

        return new CustomOAuth2User(kakaoResponse, Role.GUEST.name());
    }
}
