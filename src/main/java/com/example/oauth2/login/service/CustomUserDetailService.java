package com.example.oauth2.login.service;


import com.example.oauth2.login.user.CustomUser;
import com.example.oauth2.login.user.CustomUserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = User.withDefaultPasswordEncoder() // 테스트용 객체
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        CustomUserDetails userDetails1 = new CustomUserDetails(userDetails);
        userDetails1.setLoginType("form");

        return userDetails1; // 유저정보와 임의로 만든 유저 정보를 비교
    }
}
