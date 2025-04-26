package com.nhnacademy.task.service;

import com.nhnacademy.task.model.dto.LoginRequest;
import com.nhnacademy.task.model.dto.RegisterRequest;
import com.nhnacademy.task.model.type.Cud;

public interface AccountService {

    boolean existsMember(String memberId);

    void loginMember(LoginRequest userRequest);

    void registerMember(RegisterRequest registerRequest);

    void deleteMember(String memberId);

    void dormantMember(Cud cud, String memberId);

    void logoutMember(String memberId);

    String getNameMember(String memberId);

    void updateMemberStatus(String memberId, Cud cud);

}
