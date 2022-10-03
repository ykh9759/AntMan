package com.example.AntMan.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class Api {

    public static Object getStockMarketIndex() {

        HttpURLConnection conn = null;
        JSONObject result = null;

        StringBuilder query = new StringBuilder();
        query.append(
                "?serviceKey=ZSQ5tFt%2BI3mMbrSETJGSYuuiYkCCg3Djc5AFceQcedwdmP32HOZg3%2B9LFSRkmYMhhpl1YN0eaphylK%2BakSraIg%3D%3D");
        query.append("&numOfRows=22");
        query.append("&idxNm=%EC%BD%94%EC%8A%A4%ED%94%BC");
        query.append("&resultType=json");

        try {
            // URL 설정
            URL url = new URL(
                    "http://apis.data.go.kr/1160100/service/GetMarketIndexInfoService/getStockMarketIndex"
                            + query.toString());

            conn = (HttpURLConnection) url.openConnection();

            // type의 경우 POST, GET, PUT, DELETE 가능
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // 보내고 결과값 받기
            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                result = new JSONObject(sb.toString());

                // 응답 데이터
                // System.out.println("responseJson :: " + result);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;

    }
}
