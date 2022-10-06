package com.example.AntMan.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.AntMan.service.StockService;

@Controller
public class StockController {

    @Autowired
    StockService stockService;

    @GetMapping("/getStock")
    public String getStock(Model model) throws UnsupportedEncodingException {

        Object kospi = stockService.getStockMarketIndex("코스피");
        model.addAttribute("kospi", kospi);
        return "index";
    }

}
