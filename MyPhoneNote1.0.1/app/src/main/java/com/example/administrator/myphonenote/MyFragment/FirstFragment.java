package com.example.administrator.myphonenote.MyFragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.mtp.MtpConstants;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myphonenote.MainActivity;
import com.example.administrator.myphonenote.R;
import com.example.administrator.myphonenote.TalkActivity;
import com.example.administrator.myphonenote.UserActivity;

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
    MyFirstAdapter myFirstAdapter;
    public MessageReceiver mMessageReceiver;
    String name;
    String num;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText("first");
        view2 = inflater.inflate(R.layout.first_layout, null);
        init();
        first_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             //   String tel_num = first_list_data.get(position).get("num").toString();
                Intent intent = new Intent(getActivity(), TalkActivity.class);
                intent.putExtra("tel", num);
                startActivity(intent);
            }
        });

        return view2;
    }

    private void init() {
        first_list = (ListView) view2.findViewById(R.id.first_list);
        myFirstAdapter = new MyFirstAdapter();
        first_list_data = new ArrayList<>();
        registerMessageReceiver();
        //   getChildFragmentManager();
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
                name = intent.getStringExtra("name");
                num = intent.getStringExtra("num");
                //若发短信的号码在联系人中，显示联系人姓名，否则直接显示号码
                if (intent.getStringExtra("name").equals("")) {
                    map.put("name", num);
                } else {
                    map.put("name", name);
                }
                map.put("msg", intent.getStringExtra("msg"));
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
                //   viewHolder.img = (ImageView) convertView.findViewById(R.id.first_img);
                viewHolder.name_txt = (TextView) convertView.findViewById(R.id.first_name);
                viewHolder.msg_txt = (TextView) convertView.findViewById(R.id.first_msg);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //暂时使用默认头像
            //    viewHolder.img.setImageResource(R.mipmap.ic_launcher);

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
