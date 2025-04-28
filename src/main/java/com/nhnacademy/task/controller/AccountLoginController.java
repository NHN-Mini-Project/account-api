package com.nhnacademy.task.controller;

import com.nhnacademy.task.exception.WithdrawalMemberException;
import com.nhnacademy.task.model.dto.LoginRequest;
import com.nhnacademy.task.model.dto.LoginResponseDto;
import com.nhnacademy.task.model.entity.Member;
import com.nhnacademy.task.model.type.Cud;
import com.nhnacademy.task.repository.AccountRepository;
import com.nhnacademy.task.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountLoginController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/account/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequest memberRequest) {
        accountService.loginMember(memberRequest);

        String memberId = memberRequest.getMemberId();
        Member member = accountRepository.findMemberByMemberId(memberId);
        if(member.getCud() == Cud.WITHDRAWAL) {
            throw new WithdrawalMemberException("탈퇴한 회원입니다.");
        }

        if(member.getCud() == Cud.DORMANT) {
            accountService.updateMemberStatus(memberId, Cud.JOIN);
        }

        LoginResponseDto loginResponseDto = new LoginResponseDto(member.getMemberId(), member.getPassword());

        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponseDto);
    }

}
