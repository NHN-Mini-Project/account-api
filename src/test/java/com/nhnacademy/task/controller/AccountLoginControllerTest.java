package com.nhnacademy.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task.model.dto.LoginRequest;
import com.nhnacademy.task.model.entity.Member;
import com.nhnacademy.task.model.type.Cud;
import com.nhnacademy.task.repository.AccountRepository;
import com.nhnacademy.task.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountLoginController.class)
public class AccountLoginControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoSpyBean
    AccountServiceImpl accountService;

    @MockitoBean
    AccountRepository accountRepository;

//    @Test
//    void login_success() throws Exception{
//        LoginRequest loginRequest = new LoginRequest("member");
//
//        Member mockMember = new Member("member","password","email@example.com","이름", Cud.JOIN);
//
//
//        when(accountRepository.existsByMemberId("member")).thenReturn(true);
//        when(accountRepository.findMemberByMemberId("member")).thenReturn(mockMember);
//
//
//        mockMvc.perform(post("/account/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(loginRequest)))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.memberId").value("member"))
//                .andExpect(jsonPath("$.password").value("password"));
//
//    }
}
