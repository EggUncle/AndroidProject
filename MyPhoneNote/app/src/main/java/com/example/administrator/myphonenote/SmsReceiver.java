package com.example.administrator.myphonenote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsMessage;
import android.util.Log;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 16.1.15.
 */
public class SmsReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage msg = null;
        if (null != bundle) {
            Object[] smsObj = (Object[]) bundle.get("pdus");
            for (Object object : smsObj) {
                msg = SmsMessage.createFromPdu((byte[]) object);
                Date date = new Date(msg.getTimestampMillis());//时间
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String receiveTime = format.format(date);
//                System.out.println("number:" + msg.getOriginatingAddress()
//                        + "   body:" + msg.getDisplayMessageBody() + "  time:"
//                        + msg.getTimestampMillis());

                //在这里写自己的逻辑
//                if (msg.getOriginatingAddress().equals("+8618397916717")) {
//                    //TODO
//                    Log.v("dan_ge_msg","receive massage!");
//                }

            }
        }
    }



}
