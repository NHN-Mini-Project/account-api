package com.nhnacademy.task.controller;

import com.nhnacademy.task.model.dto.RegisterRequest;
import com.nhnacademy.task.model.dto.ResponseDto;
import com.nhnacademy.task.model.type.Cud;
import com.nhnacademy.task.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/user")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<ResponseDto> register(@RequestBody RegisterRequest registerRequest) {
        accountService.registerMember(registerRequest);

        ResponseDto registerResponseDto = new ResponseDto(registerRequest.getMemberId(), "회원가입 성공");

        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponseDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable String userId) {
        accountService.deleteMember();

        ResponseDto deleteResponseDto = new ResponseDto(userId, "유저 삭제");

        return ResponseEntity.status(HttpStatus.CREATED).body(deleteResponseDto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ResponseDto> dormantUser(@PathVariable String userId) {
        accountService.dormantMember(Cud.DORMANT, userId);

        ResponseDto cudResponseDto = new ResponseDto(userId, "유저 상태");

        return ResponseEntity.status(HttpStatus.CREATED).body(cudResponseDto);
    }

}
