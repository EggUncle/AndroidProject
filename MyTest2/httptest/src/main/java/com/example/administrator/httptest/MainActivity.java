package com.example.administrator.httptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class MainActivity extends AppCompatActivity {

    private Button btn_http;
    private HttpUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        utils = new HttpUtils();
        Button btn_http = (Button) findViewById(R.id.btn_http);

        btn_http.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = "zhang";
                String strPsw = "123";


                utils.send(HttpRequest.HttpMethod.GET, "http://192.168.1.129:8080/HttpTest/login.jsp" , new RequestCallBack<String>() {
                            @Override
                            public void onSuccess(ResponseInfo<String> responseInfo) {
                                Log.v("MY_TAG", "连接成功");
                            }

                            @Override
                            public void onFailure(HttpException e, String s) {
                                Log.v("MY_TAG", "连接失败");
                            }
                        });
            }
        });
    }
}
