package com.example.myapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    EditText edit;
    SensorManager sensorManager;
    Sensor sensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText)findViewById(R.id.edit);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    protected void onStop() {
        sensorManager.unregisterListener(this);
        super.onStop();

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float [] values = event.values;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("X向上的加速度:");
        stringBuilder.append(values[0]);
        stringBuilder.append("\nY向上的加速度:");
        stringBuilder.append(values[1]);
        stringBuilder.append("\nZ向上的加速度:");
        stringBuilder.append(values[2]);
        edit.setText(stringBuilder);


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
