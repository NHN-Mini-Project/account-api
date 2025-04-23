package com.nhnacademy.task.service;

public interface AccountService {

    boolean existsUser(String userId);

    void loginUser(String userId, String password);

}
