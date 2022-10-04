package com.example.AntMan.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class Api {

    public static JSONObject callApi(JSONObject param) {

        HttpURLConnection conn = null;
        JSONObject result = null;
        try {
            // URL 설정
            URL url = new URL(param.getString("url"));

            conn = (HttpURLConnection) url.openConnection();

            // type의 경우 POST, GET, PUT, DELETE 가능
            conn.setRequestMethod(param.getString("type"));
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
