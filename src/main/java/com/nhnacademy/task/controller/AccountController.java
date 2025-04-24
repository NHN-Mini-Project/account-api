package com.nhnacademy.task.controller;

import com.nhnacademy.task.model.dto.RegisterRequest;
import com.nhnacademy.task.model.dto.ResponseDto;
import com.nhnacademy.task.model.type.Cud;
import com.nhnacademy.task.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/member")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<ResponseDto> register(@RequestBody @Valid RegisterRequest registerRequest) {
        accountService.registerMember(registerRequest);

        ResponseDto registerResponseDto = new ResponseDto(registerRequest.getMemberId(), "회원가입 성공");

        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponseDto);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable String memberId) {
        accountService.deleteMember(memberId);

        ResponseDto deleteResponseDto = new ResponseDto(memberId, "멤버 삭제");

        return ResponseEntity.status(HttpStatus.CREATED).body(deleteResponseDto);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<ResponseDto> dormantUser(@PathVariable String memberId) {
        accountService.dormantMember(Cud.DORMANT, memberId);

        ResponseDto cudResponseDto = new ResponseDto(memberId, "멤버 상태");

        return ResponseEntity.status(HttpStatus.CREATED).body(cudResponseDto);
    }

}
