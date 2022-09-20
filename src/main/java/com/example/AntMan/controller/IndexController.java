package com.example.AntMan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.AntMan.domain.entity.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        Member member;

        if (session != null) {
            member = (Member) session.getAttribute("LOGIN_MEMBER");
        } else {
            member = null;
        }

        model.addAttribute("member", member);

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "member/signUp";
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }
}