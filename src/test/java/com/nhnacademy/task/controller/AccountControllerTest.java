package com.nhnacademy.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task.exception.AlreadyExistMemberException;
import com.nhnacademy.task.exception.NotFoundMemberException;
import com.nhnacademy.task.model.dto.RegisterRequest;
import com.nhnacademy.task.model.type.Cud;
import com.nhnacademy.task.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    AccountService accountService;

    @Test
    void register_success() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest("member", "password", "member@example.com", "이름", null);

        doNothing().when(accountService).registerMember(registerRequest);

        mockMvc.perform(post("/account/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.memberId").value("member"))
                .andExpect(jsonPath("$.message").value("회원가입 성공"));

    }

    @Test
    void register_fail() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest(null, "password", "email@example.com", "이름", null);

        mockMvc.perform(post("/account/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void register_alreadyExist() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest("member", "password", "email@example.com", "이름", null);

        doThrow(new AlreadyExistMemberException("이미 존재하는 회원입니다."))
                .when(accountService).registerMember(any(RegisterRequest.class));

        mockMvc.perform(post("/account/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("이미 존재하는 회원입니다."));


    }

    @Test
    void deleteMember_success() throws Exception{
        String memberId = "member";

        doNothing().when(accountService).deleteMember(memberId);

        mockMvc.perform(delete("/account/member/{memberId}",memberId))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.memberId").value("member"))
                .andExpect(jsonPath("$.message").value("멤버 삭제"));
    }

    @Test
    void deleteMember_notFound() throws Exception {
        String memberId = "notFound";

        doThrow(new NotFoundMemberException("존재하지 않는 회원입니다."))
                .when(accountService).deleteMember(memberId);

        mockMvc.perform(delete("/account/member/{memberId}",memberId))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("존재하지 않는 회원입니다."));
    }


    @Test
    void dormantMember_success() throws Exception{
        String memberId = "member";

        doNothing().when(accountService).dormantMember(Cud.DORMANT, memberId);

        mockMvc.perform(
                put("/account/member/{memberId}", memberId)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.memberId").value("member"))
                .andExpect(jsonPath("$.message").value("멤버 상태"));
    }
}
