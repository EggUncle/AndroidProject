package com.example.administrator.myimagetest;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String TAG = "sensor";
    private SensorManager sm;

    // ImageView img;
    MyCircle myCircle;
    Button btn;
    int i = 0;
    Boolean key = false;
    //
    float X_lateral;
    float Y_longitudinal;
    float Z_vertical;
    Display display;
    float myWidth;
    float myHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
        //   setListener();
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //选取加速度感应器
        int sensorType = Sensor.TYPE_ACCELEROMETER;

        /*
         * 最常用的一个方法 注册事件
         * 参数1 ：SensorEventListener监听器
         * 参数2 ：Sensor 一个服务可能有多个Sensor实现，此处调用getDefaultSensor获取默认的Sensor
         * 参数3 ：模式 可选数据变化的刷新频率
         * */
        sm.registerListener(myAccelerometerListener, sm.getDefaultSensor(sensorType), SensorManager.SENSOR_DELAY_NORMAL);

        MyThread myThread = new MyThread();
        myThread.start();
    }

    private class MyThread extends Thread {
        public void run() {
            while (true) {
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "heading " + X_lateral);
                        Log.i(TAG, "pitch " + Y_longitudinal);
                        Log.i(TAG, "roll " + Z_vertical);
                        Log.i(TAG, "___________");
                        float nowX = myCircle.getX();
                        float nowY = myCircle.getY();

                        if (nowX <= 0) {
                            nowX = 2;
                        }
                        if (nowX >= myWidth-60) {
                            nowX = myWidth - 61;
                        } else setMyCirclePostion(nowX, nowY);

                        if (nowY <= 0) {
                            nowY = 2;
                        }
                        if (nowY >= myHeight-60) {
                            nowY = myHeight - 61;
                        }
                        setMyCirclePostion(nowX, nowY);

                        Log.v("msg", nowX + " " + nowY);
                    }
                });
            }
        }

    }

    private void setMyCirclePostion(float nowX, float nowY) {
        myCircle.setX(nowX - X_lateral);
        myCircle.setY(nowY + Y_longitudinal);
        myCircle.invalidate();
    }

    private void init() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();
        //img = (ImageView) findViewById(R.id.img);
        //   btn = (Button) findViewById(R.id.btn);
        myCircle = (MyCircle) findViewById(R.id.my_circle);
        myCircle.setX(0);
        myCircle.setY(0);
        myWidth = display.getWidth();
      //  myHeight = display.getHeight();
        myHeight=display.getHeight();
        Toast.makeText(MainActivity.this,myWidth+" "+myHeight,Toast.LENGTH_SHORT).show();
    }

//    private void setListener() {
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                myCircle.setY(i);
////                i += 30;
//                if (key) {
//                    key = false;
//                } else {
//                    key = true;
//                }
//
//
////                new Thread(new Runnable() {
////                    @Override
////                    public void run() {
////                        while (key){
////                            myCircle.setY(Y_longitudinal);
////                            myCircle.setX(X_lateral);
////                        }
////                      //  img.setImageLevel(i);
////                      //  i += 30;
////                    }
////                }).start();
//////                i += 30;
//////                img.setImageLevel(i);
//            }
//        });
//    }


    final SensorEventListener myAccelerometerListener = new SensorEventListener() {

        //复写onSensorChanged方法
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                Log.i(TAG, "onSensorChanged");
                //图解中已经解释三个值的含义
                X_lateral = sensorEvent.values[0];
                Y_longitudinal = sensorEvent.values[1];
                Z_vertical = sensorEvent.values[2];
//                Log.i(TAG, "heading " + X_lateral);
//                Log.i(TAG, "pitch " + Y_longitudinal);
//                Log.i(TAG, "roll " + Z_vertical);
//                Log.i(TAG, "___________");
            }
        }

        //复写onAccuracyChanged方法
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            Log.i(TAG, "onAccuracyChanged");
        }
    };

    public void onPause() {
        /*
         * 很关键的部分：注意，说明文档中提到，即使activity不可见的时候，感应器依然会继续的工作，测试的时候可以发现，没有正常的刷新频率
         * 也会非常高，所以一定要在onPause方法中关闭触发器，否则讲耗费用户大量电量，很不负责。
         * */
        sm.unregisterListener(myAccelerometerListener);
        super.onPause();
    }

}