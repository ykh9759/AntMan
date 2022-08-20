package com.example.AntMan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class MemberController {

    @RequestMapping("/login")
    public String login() {
        return "member/login";
    }

    @RequestMapping("/sign-up")
    public String signUp() {
        return "member/sign_up";
    }
}