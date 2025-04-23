package com.nhnacademy.task.controller;

import com.nhnacademy.task.model.request.LoginRequest;
import com.nhnacademy.task.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountLoginController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest userRequest) {
        accountService.loginUser(userRequest);

        return ResponseEntity.ok("login");
    }

}
