package com.example.administrator.sms_adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by egguncle on 16.3.19.
 */
public class SmsReceiver extends BroadcastReceiver {
    ClientThread clientThread;
    Message msg;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            StringBuilder sb = new StringBuilder();
            Bundle bundle = intent.getExtras();
            clientThread = new ClientThread();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                SmsMessage[] smsMessage = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    smsMessage[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }

                new Thread(clientThread).start();
                for (SmsMessage message : smsMessage) {
                try{
                    sb.append("短信来源：");
                    sb.append(message.getDisplayOriginatingAddress());
                    sb.append("\n-------短信内容-------\n");
                    sb.append(message.getDisplayMessageBody());
                    msg = new Message();
                    msg.what=0x345;
                    msg.obj=sb.toString();
                    clientThread.revHandler.sendMessage(msg);
                }
                catch (Exception e){
                    Log.v("MY_TAG","error!");
                    e.printStackTrace();
                }
                }
            }

            Toast.makeText(context,sb.toString(),Toast.LENGTH_LONG).show();
            Log.v("MY_TAG",sb.toString());
        }
    }
}
