package com.nhnacademy.task.model.entity;

import com.nhnacademy.task.model.type.Cud;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private String userId;

    @NotNull
    private String password;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String name;
    
    private Cud cud;

}
