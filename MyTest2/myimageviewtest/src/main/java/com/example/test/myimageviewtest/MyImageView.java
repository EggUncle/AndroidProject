package com.example.test.myimageviewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by egguncle on 16.10.8.
 */
public class MyImageView extends ImageView {

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context) {
        super(context);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bitmap bitmap = (Bitmap) msg.obj;
            MyImageView.this.setImageBitmap(bitmap);
        }
    };


    public void setImageUrl(final String urlStr) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //获取Url对应的图片资源，bitmap
                    URL url = new URL(urlStr);
                    HttpURLConnection openConntection = (HttpURLConnection) url.openConnection();

                    openConntection.setRequestMethod("GET");
                    openConntection.setConnectTimeout(10 * 1000);

                    int code = openConntection.getResponseCode();
                    if (code == 200) {
                        InputStream inputStream = openConntection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        Message msg = Message.obtain();
                        msg.obj = bitmap;
                        handler.sendMessage(msg);

                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
