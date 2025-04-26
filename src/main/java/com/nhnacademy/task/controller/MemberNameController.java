package com.nhnacademy.task.controller;

import com.nhnacademy.task.model.dto.MemberIdRequest;
import com.nhnacademy.task.model.dto.ResponseUserNameDto;
import com.nhnacademy.task.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberNameController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account/name")
    public ResponseEntity<ResponseUserNameDto> getUserName(@RequestBody MemberIdRequest memberIdRequest) {
        String name = accountService.getNameMember(memberIdRequest.getMemberId());

        ResponseUserNameDto responseUserNameDto = new ResponseUserNameDto(name);

        return ResponseEntity.ok(responseUserNameDto);
    }

}
