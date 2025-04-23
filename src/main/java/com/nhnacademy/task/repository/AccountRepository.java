package com.nhnacademy.task.repository;

import com.nhnacademy.task.model.entity.User;
import com.nhnacademy.task.model.type.Cud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<User, String> {

    boolean existsByUserId(String userId);

    User findUserByUserIdAndPassword(String userId, String password);

    User findUserByUserId(String userId);

    void deleteUserByUserId(String userId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.cud = :cud WHERE u.userId = :userId")
    void dormantUser(Cud cud, String userId);


}
