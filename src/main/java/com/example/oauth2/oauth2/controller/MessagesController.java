package com.example.oauth2.oauth2.controller;

import com.example.oauth2.login.user.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MessagesController {
    //인증된 객체
    @GetMapping("/user")
    public String user(@AuthenticationPrincipal CustomUserDetails user) {


        return user.getLoginType();
    }

    @GetMapping("/api/hello")
    public String test() {
        return "Hello, world!";
    }
}
