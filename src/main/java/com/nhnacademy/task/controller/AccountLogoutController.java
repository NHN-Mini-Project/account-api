package com.nhnacademy.task.controller;

import com.nhnacademy.task.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountLogoutController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account/logout/{userId}")
    public ResponseEntity<String> logout(@PathVariable String userId) {
        accountService.logoutUser(userId);

        return ResponseEntity.ok("logout");
    }

}
