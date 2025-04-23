package com.nhnacademy.task.controller;

import com.nhnacademy.task.model.request.UserRequest;
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
