package com.nhnacademy.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountLogoutController.class)
public class AccountLogoutControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    AccountService accountService;

    @Test
    void logout_success() throws Exception {
        String memberId = "member";

        doNothing().when(accountService).logoutMember(memberId);

        mockMvc.perform(post("/account/logout/{memberId}",memberId)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.memberId").value(memberId))
                .andExpect(jsonPath("$.message").value("로그아웃 성공"));

    }
}
