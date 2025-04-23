package com.nhnacademy.task.service;

import com.nhnacademy.task.model.request.LoginRequest;
import com.nhnacademy.task.model.request.RegisterRequest;

public interface AccountService {

    boolean existsUser(String userId);

    void loginUser(LoginRequest userRequest);

    void registerUser(RegisterRequest registerRequest);

}
