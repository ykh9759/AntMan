package com.example.AntMan.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockInfo {

    private Long mrktTotAmt; // 종가 * 상장주식수
    private String mrktCtg; // 주식의 시장 구분 (KOSPI/KOSDAQ/KONEX 중 1)
    private String clpr; // 정규시장의 매매시간종료시까지 형성되는 최종가격
    private Integer vs; // 전일 대비 등락
    private Float fltRt; // 전일 대비 등락에 따른 비율
    private Integer mkp; // 정규시장의 매매시간개시후 형성되는 최초가격
    private Integer hipr; // 하루 중 가격의 최고치
    private Integer lopr; // 하루 중 가격의 최저치
    private Long trqu; // 체결수량의 누적 합계
    private Long trPrc; // 거래건 별 체결가격 * 체결수량의 누적 합계
    private Long lstgStCnt; // 종목의 상장주식수
    private String basDt; // 기준일자
    private String srtnCd; // 종목 코드보다 짧으면서 유일성이 보장되는 코드(6자리)
    private String isinCd; // 국제 채권 식별 번호. 유가증권(채권)의 국제인증 고유번호
    private String itmsNm; // 유가증권 국제인증 고유번호 코드 이름
}
