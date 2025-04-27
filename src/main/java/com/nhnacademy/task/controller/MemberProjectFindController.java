package com.nhnacademy.task.controller;

import com.nhnacademy.task.model.dto.MemberIdRequest;
import com.nhnacademy.task.model.dto.ResponseDto;
import com.nhnacademy.task.model.dto.ResponseExistsMemberDto;
import com.nhnacademy.task.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MemberProjectFindController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/project/{projectId}/members")
    public ResponseEntity<ResponseExistsMemberDto> postProjectFindMember(@RequestBody MemberIdRequest memberIdRequest) {
        boolean check = accountService.existsMember(memberIdRequest.getMemberId());

        ResponseExistsMemberDto responseExistsMemberDto = new ResponseExistsMemberDto(check);
        log.info("response:{}", responseExistsMemberDto.isExistsMember());

        return ResponseEntity.status(HttpStatus.OK).body(responseExistsMemberDto);
    }

}
