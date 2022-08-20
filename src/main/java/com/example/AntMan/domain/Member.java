package com.example.AntMan.domain;

import javax.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "member")
public class Member extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    private String id;

    private String password;

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    private Integer status;

    @Builder
    public Member(String id, String password, String name, String phoneNumber, String email, Integer status) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.status = status;
    }
}