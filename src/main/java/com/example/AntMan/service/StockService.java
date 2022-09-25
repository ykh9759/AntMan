package com.example.AntMan.service;

import org.springframework.stereotype.Service;
import com.example.AntMan.utils.Api;

@Service
public class StockService {

    public String getKospi() {
        String result = Api.callApi();

        return result;
    }
}
