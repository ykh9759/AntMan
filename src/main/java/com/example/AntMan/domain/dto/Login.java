package com.example.AntMan.domain.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {

    @NotBlank(message = "아이디를 입력 해주세요.")
    private String id;

    @NotBlank(message = "비밀번호를 입력 해주세요.")
    private String password;

}
