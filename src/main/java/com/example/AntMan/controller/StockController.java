package com.example.AntMan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.AntMan.domain.dto.StockInfo;
import com.example.AntMan.domain.entity.Member;
import com.example.AntMan.service.StockService;
import com.example.AntMan.utils.Alert;

@Controller
public class StockController {

    @Autowired
    StockService stockService;

    // 검색 페이지
    @GetMapping("/stock")
    public String search(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession(false);
        Member member = null;

        if (session != null) {
            member = (Member) session.getAttribute("LOGIN_MEMBER");
        } else {
            Alert.alertAndMovePage(response, "로그인 후 이용이 가능합니다.", "/");
            return "";
        }
        model.addAttribute("member", member);

        return "search";
    }

    @GetMapping("/stock/search")
    @ResponseBody
    public List<StockInfo> getStock(@RequestParam String search) {
        System.out.println(search);

        List<StockInfo> data = stockService.getStockPriceInfo(search);

        return data;
    }

}
