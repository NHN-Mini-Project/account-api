package com.nhnacademy.task.service.impl;

import com.nhnacademy.task.exception.AlreadyExistMemberException;
import com.nhnacademy.task.exception.NotFoundMemberException;
import com.nhnacademy.task.model.dto.RegisterRequest;
import com.nhnacademy.task.model.entity.Member;
import com.nhnacademy.task.model.type.Cud;
import com.nhnacademy.task.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountServiceImpl accountService;

    Member mockMember;

    @BeforeEach
    void setUp() {
        mockMember = new Member("member","password","email@example.com","이름",null);
    }

    @Test
    void existsMember() {
        Member mockMember = new Member("member","password","email@example.com","이름",null);
        String memberId = mockMember.getMemberId();

        when(accountRepository.existsByMemberId(memberId)).thenReturn(true);

        boolean result = accountService.existsMember(memberId);

        assertTrue(result);
        verify(accountRepository).existsByMemberId(memberId);
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
    void registerMember_AlreadyExists() {
        RegisterRequest registerRequest = new RegisterRequest("member", "password", "email@example.com", "이름", null );

        when(accountRepository.existsByMemberId("member")).thenReturn(true);

        assertThrows(AlreadyExistMemberException.class, () -> accountService.registerMember(registerRequest));
    }

    @Test
    void deleteMember() {
        Member mockMember = new Member("member","password","email@example.com","이름",null);
        String memberId = mockMember.getMemberId();

        when(accountRepository.findMemberByMemberId(memberId)).thenReturn(mockMember);
        doNothing().when(accountRepository).deleteMemberByMemberId(memberId);

        accountService.deleteMember(memberId);

        verify(accountRepository).deleteMemberByMemberId(memberId);
    }

    @Test
    void deleteMember_NotFound(){
        String memberId = "notFound";

        when(accountRepository.findMemberByMemberId(memberId)).thenReturn(null);

        assertThrows(NotFoundMemberException.class, () -> accountService.deleteMember(memberId));
    }

    @Test
    void deleteMember_IllegalArgument(){
        assertThrows(IllegalArgumentException.class, () -> accountService.deleteMember(null));
    }

    @Test
    void deleteMember_Empty(){
        assertThrows(IllegalArgumentException.class, () -> accountService.deleteMember(""));
    }

    @Test
    void dormantMember() {
        Member mockMember = new Member("member","password","email@example.com","이름", null);
        String memberId = mockMember.getMemberId();
        Cud cud = Cud.DORMANT;

        when(accountRepository.existsByMemberId(memberId)).thenReturn(true);
        doNothing().when(accountRepository).dormantMember(cud, memberId);

        accountService.dormantMember(cud, memberId);

        verify(accountRepository).dormantMember(cud, memberId);



    }

    @Test
    void logoutMember() {
    }
}