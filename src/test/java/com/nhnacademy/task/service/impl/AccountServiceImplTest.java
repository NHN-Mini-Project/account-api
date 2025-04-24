package com.nhnacademy.task.service.impl;

import com.nhnacademy.task.model.dto.RegisterRequest;
import com.nhnacademy.task.model.entity.Member;
import com.nhnacademy.task.repository.AccountRepository;
import com.nhnacademy.task.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountServiceImpl accountService;

    @Test
    void existsMember() {
    }

    @Test
    void loginMember() {
    }

    @Test
    void registerMember() {
        RegisterRequest registerRequest = new RegisterRequest("member", "password", "email@example.com", "이름", null );

        when(accountRepository.existsByMemberId("member")).thenReturn(false);
        when(accountRepository.save(any(Member.class))).thenReturn(null);

        accountService.registerMember(registerRequest);

        verify(accountRepository).save(any(Member.class));
    }

    @Test
    void deleteMember() {
    }

    @Test
    void dormantMember() {
    }

    @Test
    void logoutMember() {
    }
}