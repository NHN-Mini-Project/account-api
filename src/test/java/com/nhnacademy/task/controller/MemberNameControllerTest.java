package com.nhnacademy.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task.exception.NotFoundMemberException;
import com.nhnacademy.task.model.dto.MemberIdRequest;
import com.nhnacademy.task.model.dto.ResponseUserNameDto;
import com.nhnacademy.task.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberNameController.class)
class MemberNameControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    AccountService accountService;

    @Test
    void getUserName_Success() throws Exception {
        String memberId = "member";
        String userName = "name";
        MemberIdRequest memberIdRequest = new MemberIdRequest(memberId);
        ResponseUserNameDto responseUserNameDto = new ResponseUserNameDto();

        when(accountService.getNameMember(memberId)).thenReturn(userName);

        mockMvc.perform(post("/account/name")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(memberIdRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value(userName));
    }

    @Test
    void getUserName_NotFoundMember() throws Exception {
        String memberId = "noExistMember";
        MemberIdRequest memberIdRequest = new MemberIdRequest(memberId);

        when(accountService.getNameMember(memberId)).thenThrow(new NotFoundMemberException("존재하지 않는 회원입니다."));

        mockMvc.perform(post("/account/name")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberIdRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("존재하지 않는 회원입니다."));
    }
}