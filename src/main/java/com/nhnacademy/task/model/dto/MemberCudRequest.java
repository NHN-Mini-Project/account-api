package com.nhnacademy.task.model.dto;

import com.nhnacademy.task.model.type.Cud;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberCudRequest {

    private String memberId;
    private Cud cud;

}
