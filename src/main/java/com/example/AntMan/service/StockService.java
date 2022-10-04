package com.example.AntMan.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.example.AntMan.utils.Api;

@Service
public class StockService {

    public Object getStockMarketIndex(String idxNm) throws UnsupportedEncodingException {

        // 한글은 url 인코딩을 해준다
        String encodeIdxNm = URLEncoder.encode(idxNm, "UTF-8");

        StringBuilder url = new StringBuilder();
        url.append("http://apis.data.go.kr/1160100/service/GetMarketIndexInfoService/getStockMarketIndex");
        url.append(
                "?serviceKey=ZSQ5tFt%2BI3mMbrSETJGSYuuiYkCCg3Djc5AFceQcedwdmP32HOZg3%2B9LFSRkmYMhhpl1YN0eaphylK%2BakSraIg%3D%3D");
        url.append("&numOfRows=22");
        url.append("&idxNm=" + encodeIdxNm);
        url.append("&resultType=json");

        System.out.print(url);

        JSONObject param = new JSONObject();
        param.put("type", "GET");
        param.put("url", url.toString());

        JSONObject result = Api.callApi(param);

        return result;
    }
}
