package com.example.AntMan.domain.entity;

import javax.persistence.*;
import lombok.*;

@Getter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "user")
public class User extends Time {

    @Id // 테이블 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동증가
    private Integer no;

    private String id;

    private String password;

    private String salt;

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    private Integer status;

    @Builder
    public User(String id, String password, String name, String salt, String phoneNumber, String email,
            Integer status) {
        this.id = id;
        this.password = password;
        this.salt = salt;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.status = status;
    }

    public void passwordEncrypt(String encodePassword, String salt) {
        this.password = encodePassword;
        this.salt = salt;
    }

    public void update(User upUser) {
     
        this.name = upUser.getName();
        this.phoneNumber = upUser.getPhoneNumber();
        this.email = upUser.getEmail();
    }
}