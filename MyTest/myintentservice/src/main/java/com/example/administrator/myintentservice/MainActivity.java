package com.example.administrator.myintentservice;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
    }

    class MyClickLisnter implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_1:
                    Intent intent = new Intent(MainActivity.this,MyIntentService.class);
                    startService(intent);
                    break;
                case R.id.btn_2:
                    break;
                case R.id.btn_3:
                    break;
                case R.id.btn_4:
                    break;
            }
        }
    }
}
