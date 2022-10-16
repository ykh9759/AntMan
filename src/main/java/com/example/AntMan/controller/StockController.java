package com.example.AntMan.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.AntMan.domain.dto.StockIndex;
import com.example.AntMan.domain.dto.StockInfo;
import com.example.AntMan.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
public class StockController {

    @Autowired
    StockService stockService;

    @GetMapping("/getStock")
    public String getStock(Model model)
            throws UnsupportedEncodingException, JsonMappingException, JsonProcessingException {

        // List<StockIndex> data = stockService.getStockMarketIndex("코스피");
        // List<StockInfo> data = stockService.getStockPriceInfo("삼성전자");

        String data = stockService.getStockTopRise("0");
        model.addAttribute("data", data);

        return "test";
    }

}
