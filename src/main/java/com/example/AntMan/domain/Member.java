package com.example.AntMan.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "member")
public class Member extends Time {

    @Id // 테이블 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동증가
    private Integer no;

    @NotBlank(message = "아이디를 입력 해주세요.")
    private String id;

    @NotBlank(message = "비밀번호를 입력 해주세요.")
    private String password;

    @NotBlank(message = "이름을 입력 해주세요.")
    private String name;

    @NotBlank(message = "전화번호를 입력 해주세요.")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Email(message = "이메일 형식으로 입력해 주세요")
    private String email;

    private Integer status;

    @Builder
    public Member(String id, String password, String name, String phoneNumber, String email,
            Integer status) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.status = status;
    }
}