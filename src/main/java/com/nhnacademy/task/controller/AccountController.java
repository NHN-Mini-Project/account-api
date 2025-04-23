package com.nhnacademy.task.controller;

import com.nhnacademy.task.model.request.LoginRequest;
import com.nhnacademy.task.model.request.RegisterRequest;
import com.nhnacademy.task.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/user")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        accountService.registerUser(registerRequest);

        return ResponseEntity.ok("register");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {


        return ResponseEntity.ok("deleteUser");
    }

}
