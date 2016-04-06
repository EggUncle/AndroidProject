package com.example.administrator.mybroadreceivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyBroadCast extends BroadcastReceiver {
    public MyBroadCast() {
        Log.v("my_tag", "MyBroadCast");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //    throw new UnsupportedOperationException("Not yet implemented");
        Log.v("my_tag", "onReceive");
        Bundle bundle = intent.getExtras();
        if (intent.getAction().equals(MainActivity.ACTION_INTENT_TEST)) {

            Intent mIntent = new Intent(MainActivity.ACTION_INTENT_RECEIVER);
            mIntent.putExtra("message", "测试Broadcast与Activity之间的通信");
            context.sendBroadcast(mIntent);
        //   processCustomMessage(context, bundle);
        }
    }

//    private void processCustomMessage(Context context, Bundle bundle) {
//        Intent mIntent = new Intent(MainActivity.ACTION_INTENT_RECEIVER);
//        mIntent.putExtra("message", "测试Broadcast与Activity之间的通信");
//        context.sendBroadcast(mIntent);
//    }
}
