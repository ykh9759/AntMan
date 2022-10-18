package com.example.AntMan.utils;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupCrawling {

  public static Document conJsoupCrawling(String url) {

    Connection conn = Jsoup.connect(url);

    Document document = null;
    try {
      document = conn.get();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return document;
  }
}
