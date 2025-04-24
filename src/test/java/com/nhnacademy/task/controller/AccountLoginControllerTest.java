package com.nhnacademy.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task.model.dto.LoginRequest;
import com.nhnacademy.task.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountLoginController.class)
public class AccountLoginControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    AccountService accountService;

    @Test
    void login() throws Exception{
        LoginRequest loginRequest = new LoginRequest("user", "password");

        doNothing().when(accountService).loginUser(loginRequest);

        mockMvc.perform(post("/account/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().)

    }
}
