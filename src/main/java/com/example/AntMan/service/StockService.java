package com.example.AntMan.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.example.AntMan.domain.dto.Kospi;
import com.example.AntMan.utils.Api;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StockService {

    public List<Kospi> getStockMarketIndex(String idxNm)
            throws UnsupportedEncodingException, JsonMappingException, JsonProcessingException {

        // 한달전 날짜
        Calendar mon = Calendar.getInstance();
        mon.add(Calendar.MONTH, -3);
        String startDate = new java.text.SimpleDateFormat("yyyyMMdd").format(mon.getTime());
        System.out.println("시작날짜: " + startDate);

        // 한글은 url 인코딩을 해준다
        String encodeIdxNm = URLEncoder.encode(idxNm, "UTF-8");

        // url세팅
        StringBuilder url = new StringBuilder();
        url.append("http://apis.data.go.kr/1160100/service/GetMarketIndexInfoService/getStockMarketIndex");
        url.append(
                "?serviceKey=ZSQ5tFt%2BI3mMbrSETJGSYuuiYkCCg3Djc5AFceQcedwdmP32HOZg3%2B9LFSRkmYMhhpl1YN0eaphylK%2BakSraIg%3D%3D");
        url.append("&numOfRows=30");
        url.append("&idxNm=" + encodeIdxNm);
        url.append("&resultType=json");
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
        List<Kospi> result = Arrays.asList(mapper.readValue(obj.toString(), Kospi[].class));
        result.stream().forEach(d -> {
            System.out.println(d.getBasDt() + " : " + d.getBasIdx());
        });

        return result;
    }
}
