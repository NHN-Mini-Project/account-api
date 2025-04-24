package com.nhnacademy.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task.model.dto.RegisterRequest;
import com.nhnacademy.task.model.type.Cud;
import com.nhnacademy.task.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    AccountService accountService;

    @Test
    void register_success() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest("user", "password", "user@example.com", "이름", null);

        doNothing().when(accountService).registerMember(registerRequest);

        mockMvc.perform(post("/account/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value("user"))
                .andExpect(jsonPath("$.message").value("회원가입 성공"));

    }

    @Test
    void register_fail() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest(null, "password", "email@example.com", "이름", null);

        mockMvc.perform(post("/account/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteUser() throws Exception{
        String userId = "user";

        doNothing().when(accountService).deleteMember();

        mockMvc.perform(delete("/account/user/{userId}",userId))
                .andExpect(status().isNoContent());
    }


    @Test
    void dormantUser() throws Exception{
        String userId = "user";

        doNothing().when(accountService).dormantMember(Cud.DORMANT, userId);

        mockMvc.perform(
                put("/account/user/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value("user"))
                .andExpect(jsonPath("$.message").value("유저 상태"));
    }
}
