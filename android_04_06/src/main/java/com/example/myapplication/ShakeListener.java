package com.example.myapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Created by 傻明也有春天 on 2016/4/6.
 */
public class ShakeListener implements SensorEventListener{
    private final static int Speed_Max = 300;
    private final static int Update_time = 50;
    private float lastX;
    private float lastY;
    private float lastZ;
    private long lastUpdateTime;
    SensorManager sensorManager;
    Sensor sensor;
    OnShakeListener onShakeListener;
    Context context;
    public ShakeListener(Context context){
        this.context = context;
        start();


    }
    public void start(){
        sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager!=null)
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensor!=null){
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

        }


    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        long currentUpdateTime = System.currentTimeMillis();
        long timeInterval = currentUpdateTime - lastUpdateTime;
        if (timeInterval < Update_time) return;
        Log.v("msg",currentUpdateTime+"");
        lastUpdateTime = currentUpdateTime;
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        float deltaX = x - lastX;
        float deltaY = y - lastY;
        float deltaZ = z - lastZ;
        lastX = x;
        lastY = y;
        lastZ = z;
        double speed = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ
                * deltaZ)
                / timeInterval * 10000;
        Log.v("msg","速度为:"+speed);
        if (speed >= Speed_Max){
            onShakeListener.onShake();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public interface  OnShakeListener{
            public void onShake();

        }
    public void setOnShakeListener(OnShakeListener listener){
        onShakeListener = listener;

    }
}
