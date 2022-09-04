package com.example.AntMan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.AntMan.domain.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "index";
        }

        Member member = (Member) session.getAttribute("LOGIN_MEMBER");

        if (member == null) {
            return "index";
        }

        model.addAttribute("member", member);
        return "indexLogin";
    }

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "member/signUp";
    }
}