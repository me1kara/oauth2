package com.example.oauth2.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.oauth2.oauth2.controller.MessagesController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value={MessagesController.class})
public class SecurityTest {
    @Autowired
    private MockMvc mockMvc;   // HTTP 호출을 위한 MockMVC 사용

    @WithMockUser
    @Test
    void testSecurityByUser() throws Exception { // 유저확인
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @WithMockUser("한준")
    @Test
    void testUserInfo() throws Exception { // 유저의 정보 확인
        mockMvc.perform(get("/api/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated().withUsername("한준"));
    }

    @WithMockUser(username="user",password = "password")
    @Test
    void formLoginTest() throws Exception { // 유저의 정보 확인
        mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated().withUsername("user"));
    }

    @Test
    void testSecurityByAny() throws Exception { // 비로그인 유저 자격이 없을때 redirect
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().is3xxRedirection()); // 302로 리다이렉트 됨
        //status().isOk(): HTTP 상태 코드가 200인지 확인합니다.
        //status().isNotFound(): HTTP 상태 코드가 404인지 확인합니다.
        //status().isBadRequest(): HTTP 상태 코드가 400인지 확인합니다.
        //status().isInternalServerError(): HTTP 상태 코드가 500인지 확인합니다.
        //status().isUnauthorized(): HTTP 상태 코드가 401인지 확인합니다.
        //status().isForbidden(): HTTP 상태 코드가 403인지 확인합니다.
        //status().is3xxRedirection(): HTTP 상태 코드가 3xx 중 하나인지 확인합니다.
    }


}
