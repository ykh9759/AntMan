package com.example.AntMan.service;

import org.springframework.stereotype.Service;
import com.example.AntMan.utils.Api;

@Service
public class StockService {

    public Object getKospi() {
        Object result = Api.getStockMarketIndex();

        return result;
    }
}
