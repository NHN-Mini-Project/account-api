package com.nhnacademy.task.service.impl;

import com.nhnacademy.task.exception.AlreadyExistMemberException;
import com.nhnacademy.task.exception.NotFoundMemberException;
import com.nhnacademy.task.model.entity.Member;
import com.nhnacademy.task.model.dto.LoginRequest;
import com.nhnacademy.task.model.dto.RegisterRequest;
import com.nhnacademy.task.model.type.Cud;
import com.nhnacademy.task.repository.AccountRepository;
import com.nhnacademy.task.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public boolean existsMember(String memberId) {
        return accountRepository.existsByMemberId(memberId);
    }

    @Override
    public void loginMember(LoginRequest memberRequest) {
        if(Objects.isNull(memberRequest) || Objects.isNull(memberRequest.getMemberId()) || memberRequest.getMemberId().isEmpty()) {
            throw new IllegalArgumentException();
        }

        if(!existsMember(memberRequest.getMemberId())) {
            throw new AlreadyExistMemberException("ID 값에 해당하는 유저가 존재하지 않습니다.");
        }

    }

    @Override
    public void registerMember(RegisterRequest registerRequest) {
        if(Objects.isNull(registerRequest) || Objects.isNull(registerRequest.getMemberId()) || registerRequest.getMemberId().isEmpty() || Objects.isNull(registerRequest.getPassword()) || registerRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException();
        }

        if(existsMember(registerRequest.getMemberId())) {
            throw new AlreadyExistMemberException("ID 값에 해당하는 유저가 이미 존재합니다.");
        }

        String memberId = registerRequest.getMemberId();
        String password = registerRequest.getPassword();
        String email = registerRequest.getEmail();
        String name = registerRequest.getName();

        accountRepository.save(new Member(memberId, password, email, name, Cud.JOIN));
    }

    @Override
    public void deleteMember(String memberId) {
        if(Objects.isNull(memberId)) {
            throw new IllegalArgumentException();
        }

        Member member = accountRepository.findMemberByMemberId(memberId);
        if(Objects.isNull(member)) {
            throw new NotFoundMemberException("ID 값에 해당하는 유저를 찾을 수 없습니다.");
        }

        accountRepository.deleteMemberByMemberId(memberId);
    }

    @Override
    public void dormantMember(Cud cud, String memberId) {
        if(Objects.isNull(cud) || Objects.isNull(memberId) || memberId.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if(!existsMember(memberId)) {
            throw new NotFoundMemberException("ID 값에 해당하는 유저를 찾을 수 없습니다.");
        }

        accountRepository.dormantMember(cud, memberId);
    }

    @Override
    public void logoutMember(String memberId) {
        if(Objects.isNull(memberId) || memberId.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if(!existsMember(memberId)) {
            throw new NotFoundMemberException("ID 값에 해당하는 유저를 찾을 수 없습니다.");
        }

    }

    @Override
    public String getNameMember(String memberId) {
        if(Objects.isNull(memberId)) {
            throw new IllegalArgumentException();
        }

        Member member = accountRepository.findMemberByMemberId(memberId);

        return member.getName();
    }

    @Override
    public void updateMemberStatus(String memberId, Cud cud) {
        if(Objects.isNull(memberId) || Objects.isNull(cud)) {
            throw new IllegalArgumentException();
        }

        accountRepository.dormantMember(cud, memberId);
    }


}
