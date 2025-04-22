package com.nhnacademy.controller;

import com.nhnacademy.model.request.UserRequest;
import com.nhnacademy.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {



    @PostMapping("/user")
    public String postUser(@RequestBody UserRequest userRequest) {
        String userId = userRequest.getUserId();
        String password = userRequest.getPassword();



        return "OK";
    }

}
