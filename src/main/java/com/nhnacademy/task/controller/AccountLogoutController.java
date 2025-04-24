package com.nhnacademy.task.controller;

import com.nhnacademy.task.model.dto.ResponseDto;
import com.nhnacademy.task.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountLogoutController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account/logout/{memberId}")
    public ResponseEntity<ResponseDto> logout(@PathVariable String memberId) {
        accountService.logoutMember(memberId);

        ResponseDto logoutResponseDto = new ResponseDto(memberId, "로그아웃 성공");

        return ResponseEntity.status(HttpStatus.CREATED).body(logoutResponseDto);
    }

}
