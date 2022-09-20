package com.example.AntMan.controller;

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
    public String getStock(Model model) {

        return "";
    }

}