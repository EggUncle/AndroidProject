package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 傻明也有春天 on 2016/4/6.
 */
public class ShakeActivity extends Activity{
    ShakeListener shakeListener;
    EditText editText;
    int count = 0;
    Button btn_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.edit);
        shakeListener = new ShakeListener(this);

        btn_1 = (Button)findViewById(R.id.btn_1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                editText.setText(count+"");
            }
        });
        shakeListener.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake() {
                count = count+1;
                editText.setText(count+"");


            }
        });

    }
}
