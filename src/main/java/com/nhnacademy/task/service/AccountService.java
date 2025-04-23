package com.nhnacademy.task.service;

import com.nhnacademy.task.model.request.LoginRequest;
import com.nhnacademy.task.model.request.RegisterRequest;
import com.nhnacademy.task.model.type.Cud;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccountService {

    boolean existsUser(String userId);

    void loginUser(LoginRequest userRequest);

    void registerUser(RegisterRequest registerRequest);

    void deleteUser(String userId);

    void dormantUser(Cud cud, String userId);

}
