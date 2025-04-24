package com.nhnacademy.task.controller;

import com.nhnacademy.task.model.dto.LoginRequest;
import com.nhnacademy.task.model.dto.ResponseDto;
import com.nhnacademy.task.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountLoginController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequest memberRequest) {
        accountService.loginMember(memberRequest);

        ResponseDto loginResponseDto = new ResponseDto(memberRequest.getMemberId(), "로그인 성공");

        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponseDto);
    }

}
