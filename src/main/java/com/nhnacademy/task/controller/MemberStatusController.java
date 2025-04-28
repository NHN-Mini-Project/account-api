package com.nhnacademy.task.controller;

import com.nhnacademy.task.model.dto.MemberCudRequest;
import com.nhnacademy.task.model.dto.ResponseDto;
import com.nhnacademy.task.model.type.Cud;
import com.nhnacademy.task.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
public class MemberStatusController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account/status")
    public ResponseEntity<ResponseDto> getMemberStatus(@RequestBody MemberCudRequest memberCudRequest) {
        accountService.dormantMember(memberCudRequest.getCud(), memberCudRequest.getMemberId());

        ResponseDto responseDto = new ResponseDto(memberCudRequest.getMemberId(), "유저 상태 변경");

        return ResponseEntity.ok(responseDto);
    }

}
