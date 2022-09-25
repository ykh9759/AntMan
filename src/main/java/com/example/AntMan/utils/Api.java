package com.example.AntMan.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class Api {

    public static String callApi() {

        String result = "";

        try {
            URL url = new URL(
                    "http://apis.data.go.kr/1160100/service/GetMarketIndexInfoService/getStockMarketIndex?serviceKey=ZSQ5tFt%2BI3mMbrSETJGSYuuiYkCCg3Djc5AFceQcedwdmP32HOZg3%2B9LFSRkmYMhhpl1YN0eaphylK%2BakSraIg%3D%3D&numOfRows=22&idxNm=코스피");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET"); // http 메서드
            conn.setRequestProperty("Content-Type", "application/json"); // header Content-Type 정보
            conn.setRequestProperty("auth", "myAuth"); // header의 auth 정보
            conn.setDoOutput(true); // 서버로부터 받는 값이 있다면 true

            // 서버로부터 데이터 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = br.readLine()) != null) { // 읽을 수 있을 때 까지 반복
                sb.append(line);
            }

            JSONArray array = new JSONArray(sb.toString()); // json으로 변경 (역직렬화)
            JSONObject obj = null;
            for (int i = 0; i < array.length(); i++) {
                obj = array.getJSONObject(i);
            }

            result = obj.toString();

        } catch (Exception e) {
            e.printStackTrace();
            // result = e.toString();
        }

        return result;

    }
}
