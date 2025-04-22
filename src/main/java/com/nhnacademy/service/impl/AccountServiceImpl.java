package com.nhnacademy.service.impl;

import com.nhnacademy.exception.AlreadyExistUserException;
import com.nhnacademy.repository.AccountRepository;
import com.nhnacademy.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public boolean existsUser(String userId) {
        return accountRepository.existsByUserId(userId);
    }

    @Override
    public void loginUser(String userId, String password) {
        if(existsUser(userId)) {
            throw new AlreadyExistUserException("ID 값에 해당하는 유저가 이미 존재합니다.");
        }


    }


}
