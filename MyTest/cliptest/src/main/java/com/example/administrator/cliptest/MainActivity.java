package com.example.administrator.cliptest;

import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    Button btn;
    ImageView imageView;
    ClipDrawable clipDrawable;

    int i = 20;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x123) {
                    Toast.makeText(MainActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (true) {

                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        i += 300;
                                        clipDrawable.setLevel(i);
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }
        };

        btn = (Button) findViewById(R.id.btn);
        imageView = (ImageView) findViewById(R.id.img);
        clipDrawable = (ClipDrawable) imageView.getDrawable();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                i+=300;
//                clipDrawable.setLevel(i);
                Message msg = new Message();
                msg.what = 0x123;
                String str = "bac";
                msg.obj = str;
                handler.sendMessage(msg);


            }
        });
    }
}
