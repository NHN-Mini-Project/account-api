package com.nhnacademy.task.service.impl;

import com.nhnacademy.task.exception.AlreadyExistUserException;
import com.nhnacademy.task.exception.NotFoundUserException;
import com.nhnacademy.task.model.entity.User;
import com.nhnacademy.task.model.request.LoginRequest;
import com.nhnacademy.task.model.request.RegisterRequest;
import com.nhnacademy.task.model.type.Cud;
import com.nhnacademy.task.repository.AccountRepository;
import com.nhnacademy.task.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public boolean existsUser(String userId) {
        return accountRepository.existsByUserId(userId);
    }

    @Override
    public void loginUser(LoginRequest userRequest) {
        if(Objects.isNull(userRequest) || Objects.isNull(userRequest.getUserId()) || userRequest.getUserId().isEmpty() || Objects.isNull(userRequest.getPassword()) || userRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException();
        }

        if(!existsUser(userRequest.getUserId())) {
            throw new AlreadyExistUserException("ID 값에 해당하는 유저가 이미 존재합니다.");
        }

        String userId = userRequest.getUserId();
        String password = userRequest.getPassword();

        User loginUser = accountRepository.findUserByUserIdAndPassword(userId, password);
        if(Objects.isNull(loginUser)) {
            throw new NotFoundUserException("ID 또는 Password가 일치하지 않습니다.");
        }

    }

    @Override
    public void registerUser(RegisterRequest registerRequest) {
        if(Objects.isNull(registerRequest) || Objects.isNull(registerRequest.getUserId()) || registerRequest.getUserId().isEmpty() || Objects.isNull(registerRequest.getPassword()) || registerRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException();
        }

        if(existsUser(registerRequest.getUserId())) {
            throw new AlreadyExistUserException("ID 값에 해당하는 유저가 이미 존재합니다.");
        }

        String userId = registerRequest.getUserId();
        String password = registerRequest.getPassword();
        Cud cud = registerRequest.getCud();

        accountRepository.save(new User(userId, password, cud));
    }


}
