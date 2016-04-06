package com.example.administrator.sharedpreferencestest;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView time_txt;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String string_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        sharedPreferences  = getSharedPreferences("userInfo", Context.MODE_APPEND);
        string_time = sharedPreferences.getString("time", "1");
        time_txt.setText("打开过 "+string_time+" 次");

        try {
            FileOutputStream out = openFileOutput("1.txt",Context.MODE_APPEND);
            out.write("hello".getBytes());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void init(){
        time_txt = (TextView) findViewById(R.id.time);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
       //  String string_time = sharedPreferences.getString("time","1");
        int int_time = Integer.parseInt(string_time);
        int_time++;
        editor = sharedPreferences.edit();
        editor.putString("time",int_time+"");
        editor.commit();
        Log.v("msg",int_time+"");
    }
}
