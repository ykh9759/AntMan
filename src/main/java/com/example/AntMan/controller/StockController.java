package com.example.AntMan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.AntMan.domain.dto.StockInfo;
import com.example.AntMan.service.StockService;

@Controller
public class StockController {

    @Autowired
    StockService stockService;

    @GetMapping("/stock/search")
    @ResponseBody
    public List<StockInfo> getStock(@RequestParam String search) {
        System.out.println(search);

        List<StockInfo> data = stockService.getStockPriceInfo(search);

        System.out.println(data);

        return data;
    }

}
