package com.example.AntMan.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.AntMan.domain.entity.Member;
import com.example.AntMan.service.StockService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {

    @Autowired
    StockService stockService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        HttpSession session = request.getSession(false);
        Member member;

        if (session != null) {
            member = (Member) session.getAttribute("LOGIN_MEMBER");
        } else {
            member = null;
        }

        model.addAttribute("member", member);

        Object kospi = stockService.getStockMarketIndex("코스피");
        model.addAttribute("kospi", kospi);

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