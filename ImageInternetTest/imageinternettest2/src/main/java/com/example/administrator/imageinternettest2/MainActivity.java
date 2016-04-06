package com.example.administrator.imageinternettest2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    Bitmap bitmap;

    String mURL = "http://pic37.nipic.com/20140111/6608733_174104189000_2.jpg";

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0x123){
                img.setImageBitmap(bitmap);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.img);
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url  = new URL("http://pic37.nipic.com/20140111/6608733_174104189000_2.jpg");
                    InputStream is = url.openStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    handler.sendEmptyMessage(0x123);
                    is.close();
                    is = url.openStream();
                    OutputStream os = openFileOutput("myImage.jpg",MODE_PRIVATE);
                    byte[] buff = new byte[1024];
                    int hasRead = 0;
                    while((hasRead = is.read(buff))>0){
                        os.write(buff,0,hasRead);
                    }
                    is.close();
                    os.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
