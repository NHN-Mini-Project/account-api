package com.nhnacademy.task.repository;

import com.nhnacademy.task.model.entity.Member;
import com.nhnacademy.task.model.type.Cud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Member, String> {

    boolean existsByMemberId(String memberId);

    Member findMemberByMemberIdAndPassword(String memberId, String password);

    Member findMemberByMemberId(String memberId);

    void deleteMemberByMemberId(String memberId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Member u SET u.cud = :cud WHERE u.memberId = :memberId")
    void dormantMember(Cud cud, String memberId);


}
