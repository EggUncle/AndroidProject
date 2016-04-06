package com.example.administrator.myphonenote;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class SecondActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    @ViewInject(R.id.call_tel_btn)
    Button call_tel_btn;

    @ViewInject(R.id.send_message_btn)
    Button send_message_btn;

    @ViewInject(R.id.edit_text)
    EditText editText;

    GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        detector = new GestureDetector(this,this);
        final Intent intent = new Intent(this,SmsReceiver.class);
        startService(intent);
        ViewUtils.inject(this);
    }

    @OnClick({R.id.call_tel_btn, R.id.send_message_btn})
    public void my_OnClick(View v) {
        switch (v.getId()) {
            case R.id.call_tel_btn: {
                Intent intent = getIntent();
                Uri uri = Uri.parse("tel:" + intent.getStringExtra("tel"));
                //   Intent intent_tel = new Intent(Intent.ACTION_CALL, uri);
                //  startActivity(intent_tel);
                Intent intent_tel = new Intent();
                intent_tel.setAction(Intent.ACTION_CALL);
                intent_tel.setData(uri);
                SecondActivity.this.startActivity(intent_tel);
            }
            break;

            case R.id.send_message_btn: {

                //  Uri uri = Uri.parse("tel:" +intent.getStringExtra("tel"));
                //     Intent intent_msg = new Intent(Intent.ACTION_VIEW);
                //          intent.setType("vnd.android-dir/mms-sms");
                //  intent.setData(Uri.parse("content://mms-sms/conversations/"));//此为号码
                //      startActivity(intent_msg);

//                Intent intent_msg = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+tel_num));
//                intent.putExtra("sms_body", "test");
//                startActivity(intent_msg);


                //发送短信部分
            /*

                String sms_text = editText.getText().toString();
                if (sms_text == "") {
                    Toast.makeText(SecondActivity.this, "号码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
                    // 拆分短信内容（手机短信长度限制）
                    smsManager.sendTextMessage(tel_num, null, sms_text, null, null);
                }
*/              Intent intent = getIntent();
                String tel_num = intent.getStringExtra("tel");
                intent = new Intent(SecondActivity.this,TalkActivity.class);
                intent.putExtra("tel",tel_num);
                startActivity(intent);
            }
            break;
        }

    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        if((e1.getX()-e2.getX())<-100&&(Math.abs(e1.getY()-e2.getY())<60)) {
            Toast.makeText(SecondActivity.this, "BACK", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SecondActivity.this,UserActivity.class));
            finish();
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

}
