package com.example.oauth2.oauth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/login/oauth2")
    public String login(){

        System.out.println("test");
        return "/login/loginForm.html";
    }

}
