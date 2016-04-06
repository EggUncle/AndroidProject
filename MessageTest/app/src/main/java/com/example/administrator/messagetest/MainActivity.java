package com.example.administrator.messagetest;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void click(View v){
        Thread t = new Thread(){
            @Override
            public void run() {
                ContentResolver cr = getContentResolver();
                ContentValues values = new ContentValues();
                values.put("address", 95533);
                values.put("type", 1);
                values.put("date", System.currentTimeMillis());
                values.put("body", "您尾号为9999的信用卡收到1,000,000RMB转账，请注意查收");
                cr.insert(Uri.parse("content://sms"), values);
            }
        };
        t.start();
    }

}
