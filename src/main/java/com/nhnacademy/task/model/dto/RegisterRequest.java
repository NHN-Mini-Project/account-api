package com.nhnacademy.task.model.dto;

import com.nhnacademy.task.model.type.Cud;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotNull
    String memberId;
    @NotNull
    String password;

    @NotNull
    String email;
    @NotNull
    String name;

    Cud cud;

}
