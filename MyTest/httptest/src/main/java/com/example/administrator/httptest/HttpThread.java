package com.example.administrator.httptest;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by egguncle on 16.5.3.
 */
public class HttpThread extends  Thread {

    String url;
    String name;
    String age;

    public HttpThread(String url ,String name,String age){
        this.url = url;
        this.name= name;
        this.age = age;
    }

    private void doGet(){

        try {
            url = url+"?name="+ URLEncoder.encode(name,"utf-8")+"&age="+age;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            StringBuffer sb = new StringBuffer();
            while((str=reader.readLine())!=null){
                       sb.append(str);
            }

            Log.v("MY_TAG",sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        doGet();
    }
}
