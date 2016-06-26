package com.example.administrator.myurltest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements OnClickListener {
    private Button urlConnectionBtn;
    private Button httpUrlConnectionBtn;
    private Button httpClientBtn;
    private TextView showTextView;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        urlConnectionBtn = (Button) findViewById(R.id.test_url_main_btn_urlconnection);
        httpUrlConnectionBtn = (Button) findViewById(R.id.test_url_main_btn_httpurlconnection);
        httpClientBtn = (Button) findViewById(R.id.test_url_main_btn_httpclient);
        showTextView = (TextView) findViewById(R.id.test_url_main_tv_show);
        webView = (WebView) findViewById(R.id.test_url_main_wv);
        urlConnectionBtn.setOnClickListener(this);
        httpUrlConnectionBtn.setOnClickListener(this);
        httpClientBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == urlConnectionBtn) {
            try {
                // 直接使用URLConnection对象进行连接
                URL url = new URL("http://192.168.168.102:8080/abc/a.jsp");
                // 得到URLConnection对象
                URLConnection connection = url.openConnection();
                InputStream is = connection.getInputStream();
                byte[] bs = new byte[1024];
                int len = 0;
                StringBuffer sb = new StringBuffer();
                while ((len = is.read(bs)) != -1) {
                    String str = new String(bs, 0, len);
                    sb.append(str);
                }
                showTextView.setText(sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (v == httpUrlConnectionBtn) {
            // 直接使用HttpURLConnection对象进行连接
            try {
                URL url = new URL(
                        "http://192.168.168.102:8080/myweb/hello.jsp?username=abc");
                // 得到HttpURLConnection对象
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                // 设置为GET方式
                connection.setRequestMethod("GET");
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    // 得到响应消息
                    String message = connection.getResponseMessage();
                    showTextView.setText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (v == httpClientBtn) {
            try {
                // 使用ApacheHttp客户端进行连接(重要方法)
                HttpClient client = new DefaultHttpClient();

                // 如果是Get提交则创建HttpGet对象，否则创建HttpPost对象
                // POST提交的方式
                HttpPost httpPost = new HttpPost(
                        "http://192.168.1.100:8080/myweb/hello.jsp");
                // 如果是Post提交可以将参数封装到集合中传递
                List dataList = new ArrayList();
                dataList.add(new BasicNameValuePair("username", "abc"));
                dataList.add(new BasicNameValuePair("pwd", "123"));
                // UrlEncodedFormEntity用于将集合转换为Entity对象
                httpPost.setEntity(new UrlEncodedFormEntity(dataList));

                // GET提交的方式
                // HttpGet httpGet = new
                // HttpGet("http://192.168.1.100:8080/myweb/hello.jsp?username=abc&pwd=321");

                // 获取相应消息
                HttpResponse httpResponse = client.execute(httpPost);
                // 获取消息内容
                HttpEntity entity = httpResponse.getEntity();
                // 把消息对象直接转换为字符串
                String content = EntityUtils.toString(entity);
                // 显示在TextView中
                // showTextView.setText(content);

                // 通过webview来解析网页
                webView.loadDataWithBaseURL(null, content, "text/html",
                        "utf-8", null);
                // 直接根据url来进行解析
                // webView.loadUrl(url);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}