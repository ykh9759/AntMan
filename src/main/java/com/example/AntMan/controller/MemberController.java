package com.example.AntMan.controller;

import com.example.AntMan.service.MemberService;
import com.example.AntMan.domain.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "member/sign_up";
    }

    @PostMapping("/sign-up/store")
    public ResponseEntity<Member> save(Member member) {

        return new ResponseEntity<Member>(memberService.save(member), HttpStatus.OK);
    }

}