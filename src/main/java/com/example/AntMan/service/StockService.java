package com.example.AntMan.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.example.AntMan.domain.dto.StockIndex;
import com.example.AntMan.domain.dto.StockInfo;
import com.example.AntMan.domain.dto.StockTopRank;
import com.example.AntMan.utils.Api;
import com.example.AntMan.utils.JsoupCrawling;
import com.example.AntMan.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StockService {

    // 주식 지수 정보 가져온다
    public List<StockIndex> getStockMarketIndex(String idxNm) {

        // 한달전 날짜
        Calendar mon = Calendar.getInstance();
        mon.add(Calendar.MONTH, -3);
        String startDate = new java.text.SimpleDateFormat("yyyyMMdd").format(mon.getTime());
        System.out.println("구분: " + idxNm);
        System.out.println("시작날짜: " + startDate);

        // 한글은 url 인코딩을 해준다
        String encodeIdxNm = "";
        try {
            encodeIdxNm = URLEncoder.encode(idxNm, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // url세팅
        StringBuilder url = new StringBuilder();
        url.append("http://apis.data.go.kr/1160100/service/GetMarketIndexInfoService/getStockMarketIndex");
        url.append(
                "?serviceKey=ZSQ5tFt%2BI3mMbrSETJGSYuuiYkCCg3Djc5AFceQcedwdmP32HOZg3%2B9LFSRkmYMhhpl1YN0eaphylK%2BakSraIg%3D%3D");
        url.append("&numOfRows=100");
        url.append("&resultType=json");
        url.append("&idxNm=" + encodeIdxNm);
        url.append("&beginBasDt=" + startDate);

        System.out.println("URL: " + url);

        JSONObject param = new JSONObject();
        param.put("type", "GET");
        param.put("url", url.toString());

        // 지수정보 API
        JSONObject apiData = Api.callApi(param);

        // 응답 받은 JSON 값중 지수정보 추출
        Object obj = apiData.getJSONObject("response").getJSONObject("body").getJSONObject("items")
                .get("item");

        // 지수정보 배열로 매핑
        ObjectMapper mapper = new ObjectMapper();
        List<StockIndex> result = new ArrayList<StockIndex>();
        try {
            result = Arrays.asList(mapper.readValue(obj.toString(), StockIndex[].class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }

    // 주식 종목 정보 가져온다
    public List<StockInfo> getStockPriceInfo(String search) {

        // 한달전 날짜
        Calendar mon = Calendar.getInstance();
        mon.add(Calendar.MONTH, -3);
        String startDate = new java.text.SimpleDateFormat("yyyyMMdd").format(mon.getTime());
        System.out.println("시작날짜: " + startDate);

        String strSearch = "";
        if (Utils.isNumeric(search)) {
            strSearch = "&likeSrtnCd=" + search;
        } else {
            // 한글은 url 인코딩을 해준다
            try {
                strSearch = "&itmsNm=" + URLEncoder.encode(search, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        // url세팅
        StringBuilder url = new StringBuilder();
        url.append("http://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo");
        url.append(
                "?serviceKey=ZSQ5tFt%2BI3mMbrSETJGSYuuiYkCCg3Djc5AFceQcedwdmP32HOZg3%2B9LFSRkmYMhhpl1YN0eaphylK%2BakSraIg%3D%3D");
        url.append("&numOfRows=100");
        url.append("&resultType=json");
        url.append(strSearch);
        url.append("&beginBasDt=" + startDate);

        System.out.println("URL: " + url);

        JSONObject param = new JSONObject();
        param.put("type", "GET");
        param.put("url", url.toString());

        // 지수정보 API
        JSONObject apiData = Api.callApi(param);

        // 응답 받은 JSON 값중 지수정보 추출
        Object obj = apiData.getJSONObject("response").getJSONObject("body").getJSONObject("items")
                .get("item");

        // 지수정보 배열로 매핑
        ObjectMapper mapper = new ObjectMapper();
        List<StockInfo> result = new ArrayList<StockInfo>(); // 리턴값;
        try {
            result = Arrays.asList(mapper.readValue(obj.toString(),
                    StockInfo[].class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }

    // 상승 종복 리스트 가져온다. 10위까지
    public List<StockTopRank> getStockTopRank(String sosok, int Rank, String div) {

        String url = "https://finance.naver.com/sise/sise_" + div + ".naver?sosok=" + sosok; // 크롤링 url
        Document document = JsoupCrawling.conJsoupCrawling(url);

        JSONArray list = new JSONArray();

        String strList = getStockList(document, Rank);
        String[] line = strList.split("\n"); // 줄바꿈을 기준으로 문자열을 자른다.

        for (String val : line) {
            String[] index = val.split("\t"); // 탭을 기준으로 문자열을 자른다.

            Map<String, String> map = new HashMap<String, String>();

            map.put("rank", index[0].toString()); // 상승률 순위
            map.put("stockName", index[1].toString()); // 현재가
            map.put("price", index[2].toString()); // 현재가
            map.put("netChange", index[3].toString()); // 전일 대비 상승 가격
            map.put("fluctuationRate", index[4].toString()); // 등락율
            map.put("volume", index[5].toString()); // 등락율

            JSONObject obj = new JSONObject(map); // json으로 변환

            list.put(obj); // 한줄씩 넣는다.
        }

        // 지수정보 배열로 매핑
        ObjectMapper mapper = new ObjectMapper();
        List<StockTopRank> result = new ArrayList<StockTopRank>(); // 리턴값;
        try {
            result = Arrays.asList(mapper.readValue(list.toString(), StockTopRank[].class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }

    // 크롤링 데이터 문자열로 파싱
    public static String getStockList(Document document, int rank) {
        Elements stockTableBody = document.select("table.type_2 tbody tr");
        StringBuilder sb = new StringBuilder();
        for (Element element : stockTableBody) {
            String no = element.select("td").select(".no").text();
            String i = String.valueOf(rank + 1);
            if (Objects.equals(no, i)) // 10위까지만 가져온다.
                break;

            if (!no.isEmpty()) { // no값이 있을때만 가져온다.
                for (Element td : element.select("td")) {
                    String text = td.text();
                    sb.append(text);
                    sb.append("\t"); // 탭으로 구분
                }
                sb.append("\n"); // 줄바꿈
            }
        }
        return sb.toString();
    }
}
