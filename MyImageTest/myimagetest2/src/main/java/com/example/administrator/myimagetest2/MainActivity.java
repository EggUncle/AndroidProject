package com.example.administrator.myimagetest2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity implements Runnable {

    int img[] = {R.drawable.batmen, R.drawable.black};

    SurfaceHolder surfaceHolder;
    SurfaceView surfaceView;
    Thread thread;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                thread = new Thread(MainActivity.this);
                thread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    private void init() {
        surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                i++;

                synchronized (surfaceHolder) {
                    Draw(i);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.v("msg", "s");
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void Draw(int num) {
        //枷锁
        Canvas canvas = surfaceHolder.lockCanvas();
        if (surfaceHolder == null || canvas == null)
            return;

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), img[num % img.length]);
        canvas.drawBitmap(bitmap, 20, 20, paint);
        //canvas.drawText("aa",0,0,paint);
        //解锁
        surfaceHolder.unlockCanvasAndPost(canvas);
        surfaceHolder.lockCanvas();
        surfaceHolder.unlockCanvasAndPost(canvas);
    }
}
