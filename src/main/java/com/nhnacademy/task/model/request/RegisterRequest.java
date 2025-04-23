package com.nhnacademy.task.model.request;

import com.nhnacademy.task.model.type.Cud;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    String userId;
    String password;

    Cud cud;

}
