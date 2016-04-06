package com.example.administrator.sms_adapter;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

//实现手机短信的转接与电话来电后在另一台机器上提醒

public class MainActivity extends AppCompatActivity {
    Button btn;
    ClientThread clientThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this,SmsReceiver.class));

        btn = (Button) findViewById(R.id.btn);
        clientThread = new ClientThread();
        new Thread(clientThread).start();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Message msg= new Message();
                    msg.what = 0x345;
                    msg.obj="2134";
                    clientThread.revHandler.sendMessage(msg);
                }catch (Exception e){
                    Log.v("MY_TAG","error!");
                    e.printStackTrace();
                }
            }
        });
    }
}
