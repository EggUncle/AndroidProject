package com.example.administrator.messagetest2;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText editText_phone_num;
    EditText editText;

    String num;
    String txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        editText = (EditText) findViewById(R.id.edit_num);
        editText_phone_num = (EditText) findViewById(R.id.edit_phone_num);
    }




    public void click(View v) {
        txt = editText.getText().toString();
        num = editText_phone_num.getText().toString();

        Thread t = new Thread() {
            @Override
            public void run() {
                ContentResolver cr = getContentResolver();
                ContentValues values = new ContentValues();
                values.put("address", num);
                values.put("type", 1);
                values.put("date", System.currentTimeMillis());
                values.put("body",txt );
               // "您尾号为034" +"4的储蓄卡账户1月18日17时35分消费支出人名币9999999.00元,活期余额999999999999.00元。[建设银行]"
                cr.insert(Uri.parse("content://sms"), values);
            }
        };
        t.start();

        Toast.makeText(MainActivity.this,"生成成功",Toast.LENGTH_SHORT).show();
    }

}
