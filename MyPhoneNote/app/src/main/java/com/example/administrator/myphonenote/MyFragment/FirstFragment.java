package com.example.administrator.myphonenote.MyFragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.mtp.MtpConstants;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myphonenote.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by 西域战神阿凡提 on 2016/1/19.
 */
public class FirstFragment extends Fragment {

    public static String ACTION_INTENT_TEST = "com.example.mytest";
    public static String ACTION_INTENT_RECEIVER = "com.example.receiver";

    ListView first_list;
    List<Map<String, Object>> first_list_data;
    View view2;
    Button btn_refresh;
    MyFirstAdapter myFirstAdapter;
    public MessageReceiver mMessageReceiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText("first");
        view2 = inflater.inflate(R.layout.first_layout, null);
        init();
        setMyClickListener();
        return view2;
    }

    private void init() {
        first_list = (ListView) view2.findViewById(R.id.first_list);
        btn_refresh = (Button) view2.findViewById(R.id.btn_refresh);
        first_list_data = new ArrayList<>();
        myFirstAdapter = new MyFirstAdapter();
        registerMessageReceiver();
    }

    private void setMyClickListener() {
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                String num = intent.getStringExtra("num");
//                String msg = intent.getStringExtra("msg");
//                Map<String, Object> map = new HashMap<String, Object>();
//                Toast.makeText(getActivity(), num, Toast.LENGTH_SHORT).show();
//                map.put("name", "name");
//                map.put("msg", "msg");
//                first_list_data.add(map);
//                first_list.setAdapter(myFirstAdapter);
//                myFirstAdapter.notifyDataSetChanged();
                //模拟收到短信
//                Intent mIntent = new Intent(ACTION_INTENT_TEST);
//                getActivity().sendBroadcast(mIntent);

                Log.v("MY_TAG", "ssssssss");
            }
        });
  //      registerMessageReceiver();
    }


    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }


    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();

        filter.addAction(ACTION_INTENT_RECEIVER);
        getActivity().registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            if (intent.getAction().equals(ACTION_INTENT_RECEIVER)) {
                // mTextView.setText(intent.getStringExtra("message"));
             //   Toast.makeText(context,intent.getStringExtra("m"),Toast.LENGTH_LONG).show();
                Map<String, Object> map = new HashMap<>();
                map.put("name", intent.getStringExtra("num"));
                map.put("msg",intent.getStringExtra("msg"));
                first_list_data.add(map);
                first_list.setAdapter(myFirstAdapter);
                myFirstAdapter.notifyDataSetChanged();
            }
        }

    }

    private class MyFirstAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return first_list_data.size();
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
                convertView = getLayoutInflater(null).inflate(R.layout.first_layout_item, null);
                viewHolder.img = (ImageView) convertView.findViewById(R.id.first_img);
                viewHolder.name_txt = (TextView) convertView.findViewById(R.id.first_name);
                viewHolder.msg_txt = (TextView) convertView.findViewById(R.id.first_msg);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //暂时使用默认头像
            viewHolder.img.setImageResource(R.mipmap.ic_launcher);

            viewHolder.name_txt.setText(first_list_data.get(position).get("name").toString());
            viewHolder.msg_txt.setText(first_list_data.get(position).get("msg").toString());

//                viewHolder.msg_txt.setText("123");
//                viewHolder.name_txt.setText("456");

            return convertView;
        }

        private class ViewHolder {
            ImageView img;
            TextView name_txt;
            TextView msg_txt;
        }
    }
}
