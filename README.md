OAuth2

블로그를 보면서 소셜로그인을 간단하게 구현해보았습니다.

기본적인 개념
- 특정 사이트에 인증(로그인)을 위임하는 것
- 클라이언트 서버(me) <-> 리소스 서버(=인증서버, naver,google,etc)..

플로우
- 사용자는 클라이언트 서버에게 리소스를 요청하고, 클라이언트 서버는 인증인가를 리로스 서버에 위임한다.
- 사용자는 리소스 서버에서 로그인을 진행하면 redirect-url로 클라이언트 서버에 code와 함께 redirect 된다.
- 클라이언트 서버는 code를 통해 token을 리소스 서버에 요청하고, accesstoken을 발급받습니다.
- 사용자가 소셜로그인의 자격정보를 가진체 요청을 하면, 클라이언트는 자신이 갖고있는 accesstoken을 통해 소셜사이트에 필요한 resouce를 요청한다.

주요 로직
- SecurityConfig, application.yml 기본 설정
- DefaultOAuth2UserService을 상속받은 서비스
- 사이트 별로 다른 response를 통합하기 위한 OAuth2User 구현

참조 사이트 <br>
https://velog.io/@nefertiri/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0-OAuth2-%EB%8F%99%EC%9E%91-%EC%9B%90%EB%A6%AC <br>
https://velog.io/@discphy/SNS-%EB%A1%9C%EA%B7%B8%EC%9D%B8-Spring-OAuth2-Client
