package com.example.oauth2.oauth2.service;

import com.example.oauth2.oauth2.exception.OAuth2AuthenticationProcessingException;
import com.example.oauth2.oauth2.user.OAuth2UserInfo;
import com.example.oauth2.oauth2.user.OAuth2UserInfoFactory;
import com.example.oauth2.oauth2.user.OAuth2UserPrincipal;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {
    //여긴 access 토큰으로 객체 생성하는 곳
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //기본적으로 스프링이 OAuth2UserService의 구현체인 DefaultOAuth2UserService 생성시킴(아마 bean 만들지 않으면 생성시키는듯 함) *  OAuth2UserService는 userDetailService를 상속받음
        //토큰 방식의 로그인을 해봤다면 이해하기 쉬울거

        //직접 구현하기보단 그냥 상솓받은 거 쓰는게 편해서 갖다씀
        OAuth2User oAuth2User = super.loadUser(userRequest);
        //기본적인 검사 끝냄
        //db나 뭐나 추가 검사하고싶으면 여기다 로직 작성

//      return oAuth2User; 이것만으로도 충분하지만, 소셜사이트 별로 response가 다 달라서 custom 하겠음
        return processOAuth2User(userRequest, oAuth2User);
    }



    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        String registrationId = userRequest.getClientRegistration() // 도메인(naver, google)
                .getRegistrationId(); // domain

        String accessToken = userRequest.getAccessToken().getTokenValue(); // 도메인에서 받은 token

        //걍 도메인 별로 분기한게 다인데?
        //OAuth2User, UserDetails 이거 구현체로

        //도메인 별로 reponse 다르기때문에 통일시키는 작업 진행
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId,
                accessToken,
                oAuth2User.getAttributes());

        // OAuth2UserInfo field value validation
        if (!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        OAuth2UserPrincipal oauth2User = new OAuth2UserPrincipal(oAuth2UserInfo);
        oauth2User.setLoginType("oauth2");
        oauth2User.setAddress("test");
        oauth2User.setPhone("000000000");

        //그렇게 response 통일시킨 custom 객체를 이용해서 OAuth2User 구현체 만듦
        return oauth2User;
    }
}
