package com.example.administrator.myintentservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;


public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
