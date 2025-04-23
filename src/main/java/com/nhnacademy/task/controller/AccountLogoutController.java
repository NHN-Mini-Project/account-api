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

    @PostMapping("/account/logout/{userId}")
    public ResponseEntity<ResponseDto> logout(@PathVariable String userId) {
        accountService.logoutUser(userId);

        ResponseDto logoutResponseDto = new ResponseDto(userId, "로그아웃 성공");

        return ResponseEntity.status(HttpStatus.CREATED).body(logoutResponseDto);
    }

}
