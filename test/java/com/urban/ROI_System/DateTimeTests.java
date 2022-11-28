package com.urban.ROI_System;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class DateTimeTests {
    public static void main(String[] args) throws IOException {
        String url = "https://bizno.net/?query=%EC%95%84%EC%9D%B4%EB%B9%84%EC%94%A8%EC%A5%AC%EC%96%BC%EB%A6%AC";
        Document doc = Jsoup.connect(url).get();
        System.out.println(doc.body().text());
    }
}