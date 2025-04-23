package com.nhnacademy.task.service;

import com.nhnacademy.task.model.dto.LoginRequest;
import com.nhnacademy.task.model.dto.RegisterRequest;
import com.nhnacademy.task.model.type.Cud;

public interface AccountService {

    boolean existsUser(String userId);

    void loginUser(LoginRequest userRequest);

    void registerUser(RegisterRequest registerRequest);

    void deleteUser(String userId);

    void dormantUser(Cud cud, String userId);

    void logoutUser(String userId);

}
