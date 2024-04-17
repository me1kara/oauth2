package com.example.oauth2.oauth2.user;

import com.example.oauth2.oauth2.exception.OAuth2AuthenticationProcessingException;
import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId,
                                                   String accessToken,
                                                   Map<String, Object> attributes) {

        //도메인 별로 객체 생성 분기
        if ("google".equals(registrationId)) {
            //아까 말했듯 도메인 별로 accesstoken에 대한 reponse 방식이 달라서 이렇게 변형시켜줘야 함
            //해당 객체들은 같은 인터페이스를 공유, userInfo는 걍 여기서 만든 인터페이스고
            return new GoogleOAuth2UserInfo(accessToken, attributes);
        } else if ("naver".equals(registrationId)) {
            return new NaverOAuth2UserInfo(accessToken, attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Login with " + registrationId + " is not supported");
        }
    }
}
