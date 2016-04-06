package com.example.administrator.mynotificationtest;

import android.app.Notification;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static final int NOTIFICATION_ID = 0x123;
    Notification nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nm = (Notification) getSystemService(NOTIFICATION_SERVICE);
    }




}
