package com.example.administrator.aidltestserver;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button aidl;
    IMyAidlInterface myService;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myService = IMyAidlInterface.Stub.asInterface(service);// 获取服务对象
            //  aidlBtn.setEnabled(true);
        }// 连接服务

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent("com.rust.aidl.IMyService");
        // 既然没有办法构建有效的component,那么给它设置一个包名也可以生效的
        intent.setPackage("com.example.administrator.aidltestserver");// the service package
        // 绑定服务，可设置或触发一些特定的事件
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        aidl = (Button) findViewById(R.id.aidl);
        aidl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.v("MY_TAG", myService.print() + "");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
