package com.example.AntMan.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockTopRank {

    private String rank; // 상승률 순위
    private String stockName; // 현재가
    private String price; // 현재가
    private String netChange; // 전일 대비 상승 가격
    private String fluctuationRate; // 등락율
    private String volume; // 등락율
}
