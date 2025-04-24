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
@Table(name = "member")
public class Member {

    @Id
    @Column(name = "member_id")
    private String memberId;

    @NotNull
    private String password;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    private Cud cud;

}
