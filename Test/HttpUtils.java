package com.example.xcy_m.test;

import android.net.http.HttpResponseCache;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by xcy_m on 2018/8/6.
 */

public class HttpUtils {
    private static void setConnHeader(HttpURLConnection conn, String method, HttpResponseCache req_data) throws ProtocolException {
        conn.setRequestMethod(method);
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(10000);
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("Accept-Language", "zh-CN");
        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
//        if(req_data.conn)

    }
    public static byte[] getImage(String path) throws Exception{
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() != 200){
            throw new RuntimeException("url error");
        }
        InputStream inStream = conn.getInputStream();
        byte[] bt = StreamTool.read(inStream);
        inStream.close();
        return bt;
    }
    public static String getHtml(String path) throws Exception{
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() == 200){
            InputStream in = conn.getInputStream();
            byte[] data = StreamTool.read(in);
            String html = new String(data,"UTF-8");
            return html;
        }
        return null;
    }
}
