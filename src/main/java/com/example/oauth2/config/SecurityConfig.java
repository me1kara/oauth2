package com.example.oauth2.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthenticationMethod;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .oauth2Login(Customizer.withDefaults());

//                .oauth2Login() 본인의 custom DetailService 있을 경우, custom bean 하나일 땐 노필요
//                    .userInfoEndpoint()
//                        .oidcUserService()
//                        .userService();
        return http.build();
    }


    // application.yml 파일 안에 말고 직접 작성할 경우
//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        return new InMemoryClientRegistrationRepository(
//                this.googleClientRegistration(),
//                this.naverClientRegistration()
//        );
//    }
//
//    private ClientRegistration googleClientRegistration() {
//        return ClientRegistration.withRegistrationId("google")
//                .clientId(GOOGLE_CLIENT_ID) // api-key
//                .clientSecret(GOOGLE_CLIENT_SECRET) // api-secret-key
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC) // secret-key도 받겠다
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}") // 리다이렉트 url
//                .scope("openid", "profile", "email", "address", "phone") // 요청할 정보
//                .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth") // provider 별 소셜 로그인 url
//                .tokenUri("https://www.googleapis.com/oauth2/v4/token") // 로그인 성공 후 엑세스 토큰 요청할 url?
//                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo") // 엑세스 토큰으로 유저정보를 요청할때 url?
//                .userNameAttributeName(IdTokenClaimNames.SUB)
//                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
//                .clientName("Google")
//                .build();
//    }
//    private ClientRegistration naverClientRegistration() {
//        return ClientRegistration.withRegistrationId("naver")
//                .clientId(NAVER_CLIENT_ID)
//                .clientSecret(NAVER_CLIENT_SECRET)
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
//                .scope("name")
//                .authorizationUri("https://nid.naver.com/oauth2.0/authorize")
//                .tokenUri("https://nid.naver.com/oauth2.0/token")
//                .userInfoUri("https://openapi.naver.com/v1/nid/me")
//                .userInfoAuthenticationMethod(new AuthenticationMethod("header"))
//                .userNameAttributeName("response")
//                .clientName("Naver")
//                .build();
//    }


}
