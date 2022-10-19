package com.example.AntMan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.AntMan.domain.dto.StockIndex;
import com.example.AntMan.domain.dto.StockTopRank;
import com.example.AntMan.domain.entity.Member;
import com.example.AntMan.service.StockService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {

    @Autowired
    StockService stockService;

    // 테스트페이지
    @GetMapping("/test")
    public String test() {
        return "test";
    }

    // 메인페이지
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        Member member = null;

        if (session != null) {
            member = (Member) session.getAttribute("LOGIN_MEMBER");
        }
        model.addAttribute("member", member);

        List<StockIndex> kospi = stockService.getStockMarketIndex("코스피"); // 코스피지수
        List<StockIndex> kosdaq = stockService.getStockMarketIndex("코스닥"); // 코스닥지수
        model.addAttribute("kospi", kospi);
        model.addAttribute("kosdaq", kosdaq);

        List<StockTopRank> KospiTopRise = stockService.getStockTopRank("0", 5, "rise"); // 코스피 상승 상위 10위
        List<StockTopRank> KosdaqTopRise = stockService.getStockTopRank("1", 5, "rise"); // 코스피 상승 상위 10위
        model.addAttribute("KospiTopRise", KospiTopRise);
        model.addAttribute("KosdaqTopRise", KosdaqTopRise);

        List<StockTopRank> KospiTopFall = stockService.getStockTopRank("0", 5, "fall"); // 코스피 상승 상위 10위
        List<StockTopRank> KosdaqTopFall = stockService.getStockTopRank("1", 5, "fall"); // 코스피 상승 상위 10위
        model.addAttribute("KospiTopFall", KospiTopFall);
        model.addAttribute("KosdaqTopFall", KosdaqTopFall);

        return "index";
    }

    // 로그인페이지
    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    // 회원가입페이지
    @GetMapping("/sign-up")
    public String signUp() {
        return "member/signUp";
    }

    // 검색 페이지
    @GetMapping("/stock-search")
    public String search(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        Member member = null;

        if (session != null) {
            member = (Member) session.getAttribute("LOGIN_MEMBER");
        }
        model.addAttribute("member", member);

        return "search";
    }
}