package com.example.administrator.sms_receiver;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    //接收另一个手机收到短信的内容

    ListView mList;
    MyAdapter myAdapter;
    List<Map<String, Object>> listData;
    Handler handler;
    Map map;
    ClientThread clientThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mList = (ListView) findViewById(R.id.my_list);
        myAdapter = new MyAdapter();
        listData = new ArrayList<>();
        map = new HashMap();
        map.put("sms", "start");
        listData.add(map);
        mList.setAdapter(myAdapter);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    map = new HashMap();
                    map.put("sms",msg.obj.toString());
                    listData.add(map);
                    mList.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                }

            }
        };
        clientThread = new ClientThread(handler);
        new Thread(clientThread).start();
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
                convertView = getLayoutInflater().inflate(R.layout.list_item_layout, null);
                viewHolder.sms_txt = (TextView) convertView.findViewById(R.id.sms_txt);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.sms_txt.setText(listData.get(position).get("sms").toString());
            return convertView;
        }

        private class ViewHolder {
            TextView sms_txt;
        }
    }

}
