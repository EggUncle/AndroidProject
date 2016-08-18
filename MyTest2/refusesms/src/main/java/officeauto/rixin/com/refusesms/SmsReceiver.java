package officeauto.rixin.com.refusesms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SmsReceiver extends BroadcastReceiver {
    //  private static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";


    public SmsReceiver() {
    }


    // 当接收到短信时被触发
    @Override
    public void onReceive(Context context, Intent intent) {
        // 如果是接收到短信
        if (intent.getAction().equals(
                "android.provider.Telephony.SMS_RECEIVED")) {


        }
    }
}