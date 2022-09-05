package com.example.AntMan.dto;

import com.example.AntMan.domain.Member;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUp {

    @NotBlank(message = "아이디를 입력 해주세요.")
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

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .email(email)
                .status(1)
                .build();

    }
}