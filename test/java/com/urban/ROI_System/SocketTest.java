package com.urban.ROI_System;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketTest {
    public static void main(String[] args) throws IOException {
        Socket sk = new Socket("192.168.0.132" , 8000) ;
        PrintWriter pr = new PrintWriter(new OutputStreamWriter((sk.getOutputStream()), StandardCharsets.UTF_8),true);
        pr.println("asdasd");
        pr.println("asdfasdf");
        Socket socket;

        JsonObject preJsonObject = new JsonObject();
        preJsonObject.addProperty("comment", "ddddd");
//        JSONObject jsonObject = new JSONObject(preJsonObject.toString());
//        socket.emit("reqMsg", jsonObject);

    }
}