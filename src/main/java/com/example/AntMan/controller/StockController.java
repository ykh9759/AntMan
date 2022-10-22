package com.example.AntMan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.AntMan.domain.dto.StockTopRank;
import com.example.AntMan.service.StockService;

@Controller
public class StockController {

    @Autowired
    StockService stockService;

    @GetMapping("/getStock")
    public String getStock(Model model) {

        // List<StockIndex> data = stockService.getStockMarketIndex("코스피");
        // List<StockInfo> data = stockService.getStockPriceInfo("삼성전자");

        List<StockTopRank> data = stockService.getStockTopRank("0", 10, "rise");
        model.addAttribute("data", data);

        return "test";
    }

}
