package com.example.administrator.finddoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PinLunActivity extends AppCompatActivity {

    Button btn_pinlun;
    ImageButton btn_pinlun_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_lun);
        getSupportActionBar().hide();
        btn_pinlun = (Button) findViewById(R.id.btn_pinlun);
        btn_pinlun_back = (ImageButton) findViewById(R.id.btn_pinlun_back);
        btn_pinlun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =  new Intent(PinLunActivity.this,DoctorActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_pinlun_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =  new Intent(PinLunActivity.this,DoctorActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
