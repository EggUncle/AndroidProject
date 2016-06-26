package com.example.administrator.recivertest;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button send_btn;
    private Button reg_btn;
    private Button send_btn_2;
    private MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setClick();
    }

    private void init() {
        send_btn = (Button) findViewById(R.id.send_btn);
        reg_btn = (Button) findViewById(R.id.reg_btn);
        send_btn_2 = (Button) findViewById(R.id.send_btn_2);
    }

    private void setClick() {
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.egg.test");
                sendBroadcast(intent);
            }
        });

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myReceiver = new MyReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("com.egg.test_2");
                registerReceiver(myReceiver, intentFilter);
            }
        });

        send_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.egg.test_2");
                sendBroadcast(intent);
            }
        });
    }
}
