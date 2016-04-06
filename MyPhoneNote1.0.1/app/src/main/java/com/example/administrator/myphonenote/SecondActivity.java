package com.example.administrator.myphonenote;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecondActivity extends Activity implements GestureDetector.OnGestureListener {

    ListView num_list;
    TextView second_activity_name;
    GestureDetector detector;
    Button back_btn;
    List<ArrayList<String>> sc_list_data;
    ArrayList<String> arrayList;
    MyNumListAdapter myNumListAdapter;

    String tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
        setMyClick();
    }

    private void init() {
        sc_list_data = new ArrayList<>();
        detector = new GestureDetector(this, this);
        num_list = (ListView) findViewById(R.id.num_list);
        second_activity_name = (TextView) findViewById(R.id.second_activity_name);
        second_activity_name.setText(getIntent().getStringExtra("name").toString());
        back_btn = (Button) findViewById(R.id.bcak_btn);
        arrayList = (ArrayList<String>) getIntent().getSerializableExtra("phone_number");
        myNumListAdapter = new MyNumListAdapter();
        num_list.setAdapter(myNumListAdapter);
    }


    private void setMyClick() {
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBack();
            }
        });
    }

    private void Call(String tel) {
        //  Toast.makeText(SecondActivity.this, "call", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        //    Uri uri = Uri.parse("tel:" + intent.getStringExtra("tel"));
        Uri uri = Uri.parse("tel:" + tel);
        //   Intent intent_tel = new Intent(Intent.ACTION_CALL, uri);
        //  startActivity(intent_tel);
        Intent intent_tel = new Intent();
        intent_tel.setAction(Intent.ACTION_CALL);
        intent_tel.setData(uri);
        SecondActivity.this.startActivity(intent_tel);
    }

    private void SendSms(String tel) {

        Toast.makeText(SecondActivity.this, "SendSms", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        //  String tel_num = intent.getStringExtra("tel");
        String tel_num = tel;
        intent = new Intent(SecondActivity.this, TalkActivity.class);
        intent.putExtra("tel", tel_num);
        startActivity(intent);
    }

    private void myBack() {
        startActivity(new Intent(SecondActivity.this, UserActivity.class));
        overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);
        finish();
    }

    private class MyNumListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.sec_ac_item, null);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.phone_number);
                viewHolder.call_btn = (Button) convertView.findViewById(R.id.call_btn);
                viewHolder.send_btn = (Button) convertView.findViewById(R.id.send_sms_btn);


                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            tel = arrayList.get(position).toString();
            viewHolder.textView.setText(tel);
            viewHolder.call_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Call(tel);
                }
            });
            viewHolder.send_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SendSms(tel);
                }
            });

            return convertView;
        }

        private class ViewHolder {
            TextView textView;
            Button call_btn;
            Button send_btn;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            myBack();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
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

        if ((e1.getX() - e2.getX()) < -100 && (Math.abs(e1.getY() - e2.getY()) < 60)) {
            Toast.makeText(SecondActivity.this, "BACK", Toast.LENGTH_SHORT).show();
            myBack();
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
