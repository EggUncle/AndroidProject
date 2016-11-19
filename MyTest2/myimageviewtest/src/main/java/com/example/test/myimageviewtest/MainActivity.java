package com.example.test.myimageviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MyImageView myImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myImageView = (MyImageView) findViewById(R.id.my_image_view);
        String url = "http://pic.anfensi.com/Uploads/Editor/2016-04-12/570cc1c22b5ca.jpg";
        myImageView.setImageUrl(url);
    }
}
