package com.example.administrator.myphonenote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TalkActivity extends AppCompatActivity  {

    ListView mylist;
    EditText editText;
    String sms_text;
    Button send_btn;
    Button listen_change_btn;
    List<Map<String, Object>> listData;
    Map<String, Object> map;
    MyAdapter adapter;
    Msg msg;
    public MessageReceiver mMessageReceiver;
    public static String ACTION_INTENT_RECEIVER = "com.example.receiver";
    String name;
    String num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);
        listData = new ArrayList<>();
          init();

//        final Intent intent = new Intent(this, SmsReceiver.class);
//        startService(intent);
        adapter = new MyAdapter();

        setMyListener();

    }

    private void init() {

        mylist = (ListView) findViewById(R.id.my_list);
        editText = (EditText) findViewById(R.id.edit_text_send);
        send_btn = (Button) findViewById(R.id.btn_send);
        listen_change_btn = (Button) findViewById(R.id.listen_change);
        registerMessageReceiver();

    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();

        filter.addAction(ACTION_INTENT_RECEIVER);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            if (intent.getAction().equals(ACTION_INTENT_RECEIVER)) {
                // mTextView.setText(intent.getStringExtra("message"));
                //   Toast.makeText(context,intent.getStringExtra("m"),Toast.LENGTH_LONG).show();
                map   = new HashMap<>();
                name = intent.getStringExtra("name");
                num = intent.getStringExtra("num");
                //若发短信的号码在联系人中，显示联系人姓名，否则直接显示号码
                if (intent.getStringExtra("name").equals("")) {
                    map.put("name", num);
                } else {
                    map.put("name", name);
                }
                map.put("msg", intent.getStringExtra("msg"));
                map.put("type",Msg.MSG_RECVICE);
              //  msg = new Msg();
            //     msg.setKey_msg(Msg.MSG_RECVICE);
            //    msg.setMsg(intent.getStringExtra("msg"));
                listData.add(map);
                mylist.setAdapter(adapter);
                adapter.notifyDataSetChanged();
             //   map=null;
//                first_list_data.add(map);
//                first_list.setAdapter(myFirstAdapter);
//                myFirstAdapter.notifyDataSetChanged();
            }
        }

    }

    private void setMyListener() {
        listen_change_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                msg = new Msg();
//                msg.setKey_msg(Msg.MSG_SEND);
           //     msg.setMsg(editText.getText().toString());
                map = new HashMap();
                map.put("msg", editText.getText().toString());
                map.put("type",Msg.MSG_SEND);
                //发送短信
                Intent intent = getIntent();
                String tel_num = intent.getStringExtra("tel");
                String sms_text = editText.getText().toString();
                if (sms_text == "") {
                    Toast.makeText(TalkActivity.this, "号码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
                    // 拆分短信内容（手机短信长度限制）
                    smsManager.sendTextMessage(tel_num, null, sms_text, null, null);
                }
                listData.add(map);
                mylist.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                editText.setText("");
            }
        });
    }



    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return listData.size();
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
                convertView = getLayoutInflater().inflate(R.layout.talk_item_layout, null);
                viewHolder.textView_left = (TextView) convertView.findViewById(R.id.left_text);
                viewHolder.textView_right = (TextView) convertView.findViewById(R.id.right_text);
                viewHolder.linearLayout_left = (LinearLayout) convertView.findViewById(R.id.left_line);
                viewHolder.linearLayout_right = (LinearLayout) convertView.findViewById(R.id.right_line);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //  viewHolder.textView.setText(listData.get(position).toString());
            if (listData.get(position).get("type").toString().equals(Msg.MSG_RECVICE+"")) {
                viewHolder.linearLayout_left.setVisibility(View.VISIBLE);
                viewHolder.linearLayout_right.setVisibility(View.GONE);
                viewHolder.textView_left.setText(listData.get(position).get("msg").toString());
            }
            if (listData.get(position).get("type").toString().equals(Msg.MSG_SEND+"")) {
                viewHolder.linearLayout_right.setVisibility(View.VISIBLE);
                viewHolder.linearLayout_left.setVisibility(View.GONE);
                viewHolder.textView_right.setText(listData.get(position).get("msg").toString());
            }
            return convertView;
        }

        private class ViewHolder {
            TextView textView_right;
            TextView textView_left;
            LinearLayout linearLayout_left;
            LinearLayout linearLayout_right;
        }
    }

//    public void init() {
//
//
//    }
}


