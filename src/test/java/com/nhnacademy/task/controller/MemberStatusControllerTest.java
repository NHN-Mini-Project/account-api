package com.nhnacademy.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task.model.dto.MemberCudRequest;
import com.nhnacademy.task.model.type.Cud;
import com.nhnacademy.task.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberStatusController.class)
class MemberStatusControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    AccountService accountService;

    @Test
    void getMemberStatus_dormant_success() throws Exception {
        MemberCudRequest request = new MemberCudRequest("member", Cud.DORMANT);

        doNothing().when(accountService).dormantMember(request.getCud(), request.getMemberId());

        mockMvc.perform(post("/account/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId").value("member"))
                .andExpect(jsonPath("$.message").value("유저 상태 변경"));

        verify(accountService).dormantMember(request.getCud(), request.getMemberId());
    }

}