package com.example.administrator.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class FirstService extends Service {

    public MyBinder myBinder;

    public class MyBinder extends Binder{
        
    }

    public FirstService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
     //   throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.v("msg","Serviec is Create");
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        Log.v("msg","Serviec is Started");
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.v("msg","Serviec is Deatroy");
    }
}
