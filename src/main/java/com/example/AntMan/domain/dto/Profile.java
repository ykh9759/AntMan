package com.example.AntMan.domain.dto;

import com.example.AntMan.domain.entity.User;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    private String id;

    @NotBlank(message = "비밀번호를 입력 해주세요.")
    private String password;

    @Transient // 컬럼 자동추가 방지
    @NotBlank(message = "비밀번호 재확인을 입력 해주세요.")
    private String passwordCheck;

    @NotBlank(message = "이름을 입력 해주세요.")
    private String name;

    @NotBlank(message = "전화번호를 입력 해주세요.")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Email(message = "이메일 형식으로 입력해 주세요")
    private String email;

    public User toEntity() {
        return User.builder()
                .id(id)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .email(email)
                .status(1)
                .build();

    }
}