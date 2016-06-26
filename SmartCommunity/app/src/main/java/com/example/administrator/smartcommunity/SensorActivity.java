package com.example.administrator.smartcommunity;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.db.chart.Tools;
import com.db.chart.model.LineSet;
import com.db.chart.view.ChartView;
import com.db.chart.view.LineChartView;
import com.db.chart.view.animation.Animation;
import com.db.chart.view.animation.easing.CubicEase;

import java.text.DecimalFormat;
import java.util.Random;

public class SensorActivity extends AppCompatActivity {

    LineChartView chart;
    Button btn_random;
    float[] random_data = new float[100];
    Random random = new Random();
    Thread myThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //   Random random = new Random();
        random_data[0] = (random.nextFloat()) * 10;
        //生成随机的数据
        for (int i = 0; i < 99; i++) {

            float key = (random.nextFloat()) * 10;
            if (key > 5) {
                random_data[i + 1] = random_data[i] + 1;
            } else {
                random_data[i + 1] = random_data[i] - 1;
            }

            random_data[i] = (random.nextFloat()) * 10;
            //   float i = (random.nextFloat()) * 10;
        }
        btn_random = (Button) findViewById(R.id.btn_random);


        btn_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                for (int i = 1; i < 100; i++) {
                    //  random_data[i] = (random.nextFloat()) * 10;
                    // random_data2[i]=random_data[i+1];
                    random_data[i - 1] = random_data[i];
                    //   float i = (random.nextFloat()) * 10;
                }
                random_data[99] = (random.nextFloat()) * 10;
                Toast.makeText(SensorActivity.this, "刷新", Toast.LENGTH_SHORT).show();

                //     LineSet dataset = new LineSet(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}, random_data);
                //  chart.notifyDataUpdate();
//                chart.reset();
//                chart.addData(dataset);

//                Paint gridPaint = new Paint();
//                gridPaint.setColor(Color.parseColor("#19000c"));
//                gridPaint.setStyle(Paint.Style.STROKE);
//                gridPaint.setAntiAlias(true);
//                gridPaint.setStrokeWidth(Tools.fromDpToPx(3.0f));
//                gridPaint.setPathEffect(new DashPathEffect(new float[]{5.0f, 7.0f, 1.0f}, 0));
//                chart.setGrid(ChartView.GridType.HORIZONTAL, gridPaint);
//                chart.setLabelsFormat(new DecimalFormat("#" + "m"));
//
//// Animation customization
//                Animation anim = new Animation(500);
//                anim.setEasing(new CubicEase());
//                anim.setAlpha(1);

                chart.updateValues(0, random_data);
                chart.show();
            }
        });

        chart = (LineChartView) findViewById(R.id.my_line_chart_view);

        String[] data = new String[100];
        for (int i = 0; i < 100; i++) {
            data[i] = "";
        }


        // Line chart customization
        //    LineSet dataset = new LineSet(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}, random_data);
        LineSet dataset = new LineSet(data, random_data);
        dataset.setSmooth(true);
        dataset.setThickness(Tools.fromDpToPx(3.0f));
        dataset.setColor(Color.parseColor("#009cff"));
        // dataset.setDotsRadius(Tools.fromDpToPx(6.0f));
        //  dataset.setDotsColor(Color.parseColor("#0062ff"));

        chart.addData(dataset);


// Generic chart customization
        chart.setAxisColor(Color.parseColor("#00b6ce"));
        chart.setLabelsColor(Color.parseColor("#0092d0"));
// Paint object used to draw Grid
        Paint gridPaint = new Paint();
        gridPaint.setColor(Color.parseColor("#19000c"));

        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setAntiAlias(true);
        gridPaint.setStrokeWidth(Tools.fromDpToPx(3.0f));
        gridPaint.setPathEffect(new DashPathEffect(new float[]{5.0f, 7.0f, 1.0f}, 0));
        chart.setGrid(ChartView.GridType.HORIZONTAL, gridPaint);
        chart.setLabelsFormat(new DecimalFormat("#" + "m"));

// Animation customization
        Animation anim = new Animation(500);
        anim.setEasing(new CubicEase());
        anim.setAlpha(1);
        anim.setStartPoint(0.0f, -1.0f);

        // chart.show(anim);
        chart.show();


        myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (int i = 1; i < 100; i++) {
                        //  random_data[i] = (random.nextFloat()) * 10;
                        // random_data2[i]=random_data[i+1];
                        random_data[i - 1] = random_data[i];
                        //   float i = (random.nextFloat()) * 10;
                    }


                    float key = (random.nextFloat()) * 10;

                    if (key > 7) {
                        random_data[99]++;
                    } else {
                        if (key < 4 ) {
                            random_data[99]--;
                        } else {

                        }
                    }

                    if (random_data[99] >= 10) {
                        random_data[99]--;
                    }
                    if (random_data[99] <= 0) {
                        random_data[99]++;
                    }

//                    random_data[99] = (random.nextFloat()) * 10;
                    chart.updateValues(0, random_data);
                    chart.show();

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        myThread.start();
    }

    @Override
    protected void onDestroy() {
        //  myThread.destroy();
        super.onDestroy();
    }
}
