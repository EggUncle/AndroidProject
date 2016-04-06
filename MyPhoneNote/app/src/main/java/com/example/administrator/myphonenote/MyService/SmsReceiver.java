package com.example.administrator.myphonenote.MyService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.myphonenote.MainActivity;
import com.example.administrator.myphonenote.MyFragment.FirstFragment;
import com.example.administrator.myphonenote.UserActivity;

public class SmsReceiver extends BroadcastReceiver {

    /*
    * 接收短信
    *
    * */

    public SmsReceiver() {
    }

    String name;
    String num;
    String msg;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //  throw new UnsupportedOperationException("Not yet implemented");
        // 如果是接收到短信
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            // 取消广播（这行代码将会让系统收不到短信）
            //   abortBroadcast();  // ①
            StringBuilder sb = new StringBuilder();
            // 接收由SMS传过来的数据
            Bundle bundle = intent.getExtras();
            // 判断是否有数据
            if (bundle != null) {
                // 通过pdus可以获得接收到的所有短信消息
                Object[] pdus = (Object[]) bundle.get("pdus");
                // 构建短信对象array,并依据收到的对象长度来创建array的大小
                SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage
                            .createFromPdu((byte[]) pdus[i]);
                }
                // 将发送来的短信合并自定义信息于StringBuilder当中
                for (SmsMessage message : messages) {
                    sb.append("短信来源:");
                    // 获得接收短信的电话号码
                    sb.append(message.getDisplayOriginatingAddress());
                    num = message.getDisplayOriginatingAddress();
                    sb.append("\n------短信内容------\n");
                    // 获得短信的内容
                    sb.append(message.getDisplayMessageBody());
                    msg = message.getDisplayMessageBody();
                }
            }

            //    Log.v("MY_TAG","ssssssss");
            Intent mIntent = new Intent(FirstFragment.ACTION_INTENT_RECEIVER);
            mIntent.putExtra("msg", msg);
            mIntent.putExtra("num",num);
            context.sendBroadcast(mIntent);
//            intent.putExtra("num",num);
//            intent.putExtra("msg",msg);
//            context.sendBroadcast(intent);
//            Toast.makeText(context, sb.toString()
//                    , Toast.LENGTH_LONG).show();

        }
//        intent.putExtra("num","123");
//        intent.putExtra("msg","456");
//        context.sendBroadcast(intent);
//        Toast.makeText(context, "888"
//                , Toast.LENGTH_LONG).show();
    }
}
