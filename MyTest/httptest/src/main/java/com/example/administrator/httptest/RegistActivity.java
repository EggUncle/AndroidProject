package com.example.administrator.httptest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;

public class RegistActivity extends AppCompatActivity {

    EditText name;
    EditText age;
    Button regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        init();

       regist.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String url = "http://192.168.1.136:8080/web/MyServlet";
           //  new HttpThread(url,name.getText().toString(),age.getText().toString()).start();
              // url  = url+"?name="+name+"&age="+age;
               new HttpClientThread(url,name.getText().toString(),age.getText().toString()).start();
           }
       });
    }

    private void init(){
        name  = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        regist = (Button) findViewById(R.id.regist);
    }
}
