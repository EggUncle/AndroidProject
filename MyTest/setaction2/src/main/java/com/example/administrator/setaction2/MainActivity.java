package com.example.administrator.setaction2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView =(TextView) findViewById(R.id.txt);
        Button btn = (Button) findViewById(R.id.btn);
        textView.setText(this.getTaskId()+"");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent();
                 intent.setAction("chadan");
            }
        });
    }
}
