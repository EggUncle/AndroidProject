package com.example.administrator.mymusictest;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button start_btn;
    Button stop_btn;
    Button pause_btn;
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setMyClick();
    }

    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.start:
                    mPlayer.start();
                    break;
                case R.id.stop:
                    mPlayer.stop();
                    break;
                case R.id.pause:
                    mPlayer.pause();
                    break;
            }
        }
    }

    private void init() {
        start_btn = (Button) findViewById(R.id.start);
        stop_btn = (Button) findViewById(R.id.stop);
        pause_btn = (Button) findViewById(R.id.pause);
        // mPlayer = MediaPlayer.create(this, R.raw.song);
        mPlayer = new MediaPlayer();
//        try {
//            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//                File sdCardDir = Environment.getExternalStorageDirectory();
//
//             //   FileInputStream fis = new FileInputStream(sdCardDir.getCanonicalPath() + "/MyTest/MyTestMusic/Test1.mp3");
//                mPlayer.setDataSource(sdCardDir.getCanonicalPath() + "/MyTest/MyTestMusic/Test1.mp3");
//            }
//        } catch (IOException e) {
//            Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
        try {
            mPlayer.setDataSource("/mnt/sdcard/test.mp3");
            mPlayer.prepare();
        } catch (IOException e) {
            Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void setMyClick() {
        start_btn.setOnClickListener(new MyClickListener());
        stop_btn.setOnClickListener(new MyClickListener());
        pause_btn.setOnClickListener(new MyClickListener());
    }
}
