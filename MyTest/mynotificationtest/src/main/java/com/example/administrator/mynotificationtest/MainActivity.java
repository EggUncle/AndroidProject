package com.example.administrator.mynotificationtest;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;


public class MainActivity extends AppCompatActivity {

    static final int NOTIFICATION_ID = 0x123;
    NotificationManager nm;
    NotificationCompat.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void test(View source) {


     //   Notification notify = null;
        Intent intent = new Intent(MainActivity.this
                , OtherActivity.class);
        PendingIntent pi = PendingIntent.getActivity(
                MainActivity.this, 0, intent, 0);
//        notify = new Notification.Builder(this)
//                .setContentTitle("Test").setContentText("it is a test")
//                .setContentIntent(pi)
//                .setTicker("new message!")
//                .setWhen(System.currentTimeMillis())
//                .setOngoing(false)
//                .setDefaults(Notification.DEFAULT_VIBRATE)
//                .setSmallIcon(R.drawable.iconfont_bofangqibofang)
//                .build();
//        nm.notify(NOTIFICATION_ID, notify);

        //Intent buttonIntent = new Intent(ACTION_BUTON);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        RemoteViews mRemoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);
        mRemoteViews.setImageViewResource(R.id.btn_notfi_next, R.drawable.iconfont_bofangqixiayiqu);
        mRemoteViews.setImageViewResource(R.id.btn_notfi_start_or_stop, R.drawable.iconfont_bofangqibofang);

        mRemoteViews.setOnClickPendingIntent(R.id.btn_notfi_next, pi);
        mBuilder.setContent(mRemoteViews)
                //.setContentIntent()
                .setWhen(System.currentTimeMillis())
                .setTicker("Playing")
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setOngoing(true).setSmallIcon(R.drawable.notify);
        Notification notify = mBuilder.build();
        notify.flags=Notification.FLAG_ONGOING_EVENT;
        nm.notify(0,notify);
    }

    public void show(View v){
       // Toast.makeText(this,"play",Toast.LENGTH_SHORT).show();
        Log.v("MY_TAG","play");
    }

    public void send(View source) {
        // 创建一个启动其他Activity的Intent
        Intent intent = new Intent(MainActivity.this
                , OtherActivity.class);
        PendingIntent pi = PendingIntent.getActivity(
                MainActivity.this, 0, intent, 0);
        Notification notify = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notify = new Notification.Builder(this)
                    // 设置打开该通知，该通知自动消失
                    .setAutoCancel(true)
                            // 设置显示在状态栏的通知提示信息
                    .setTicker("有新消息")
                            // 设置通知的图标
                    .setSmallIcon(R.drawable.notify)
                            // 设置通知内容的标题
                    .setContentTitle("一条新通知")
                            // 设置通知内容
                    .setContentText("恭喜你，您加薪了，工资增加20%!")
                            // 设置使用系统默认的声音、默认LED灯
                    .setDefaults(Notification.DEFAULT_SOUND
                            | Notification.DEFAULT_LIGHTS)
                            // 设置通知的自定义声音
                            //        .setSound(Uri.parse("android.resource://org.crazyit.ui/"
                            //                + R.raw.msg))
                    .setWhen(System.currentTimeMillis())
                            // 设改通知将要启动程序的Intent
                    .setContentIntent(pi)  // ①
                    .build();
        }
        // 发送通知
        nm.notify(NOTIFICATION_ID, notify);
    }

    // 为删除通知的按钮的点击事件定义事件处理方法
    public void del(View v) {
        // 取消通知
        nm.cancel(NOTIFICATION_ID);
    }


}
