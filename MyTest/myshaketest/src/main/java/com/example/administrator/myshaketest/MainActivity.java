package com.example.administrator.myshaketest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

 //  SensorManagerHelper sensorManagerHelper;
    TextView textView;
    int i=0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView  = (TextView) findViewById(R.id.txt);

        SensorManagerHelper sensorHelper = new SensorManagerHelper(this);
        sensorHelper.setOnShakeListener(new SensorManagerHelper.OnShakeListener() {

            @Override
            public void onShake() {
                // TODO Auto-generated method stub
                Log.v("MY_TAG","shake");
                i++;
                textView.setText(i+"");
                Toast.makeText(MainActivity.this, "你在摇哦", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
