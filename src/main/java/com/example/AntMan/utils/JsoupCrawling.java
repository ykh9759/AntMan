package com.example.AntMan.utils;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupCrawling {

  public static Document conJsoupCrawling(String url) throws IOException {

    final String stockList = url;
    Connection conn = Jsoup.connect(stockList);

    Document document = conn.get();

    return document;
  }
}
