package com.example.administrator.httptest;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by egguncle on 16.5.6.
 */
public class HttpClientThread extends Thread {

    private String url;
    private String name;
    private String age;

    public HttpClientThread(String url) {
        this.url = url;
    }

    public HttpClientThread(String url, String name, String age) {
        this.url = url;
        this.name = name;
        this.age = age;
    }

    private void doHttpClientPost() {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("name", name));
        list.add(new BasicNameValuePair("age", age));
        try {
            post.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse response = client.execute(post);
            if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
                String content = EntityUtils.toString(response.getEntity());
                Log.v("MY_TAG", "content--____>" + content);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dohttpClientGet() {
        HttpGet httpGet = new HttpGet(url);
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        try {
            response = client.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String content = EntityUtils.toString(response.getEntity());

                Log.v("MY_TAG", "content--____>" + content);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        super.run();
     //   dohttpClientGet();
        doHttpClientPost();
    }
}
