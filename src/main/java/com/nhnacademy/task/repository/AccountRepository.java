package com.nhnacademy.task.repository;

import com.nhnacademy.task.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<User, String> {

    boolean existsByUserId(String userId);

}
