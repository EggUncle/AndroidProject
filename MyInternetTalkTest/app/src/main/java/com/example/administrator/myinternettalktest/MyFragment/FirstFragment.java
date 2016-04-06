package com.example.administrator.myinternettalktest.MyFragment;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myinternettalktest.ClientThread;
import com.example.administrator.myinternettalktest.MyActivity.TalkActivity;
import com.example.administrator.myinternettalktest.Person;
import com.example.administrator.myinternettalktest.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by shadow on 16.2.13.
 */

//聊天列表部分

public class FirstFragment extends Fragment {

    View firstFragmentLayout;
    MyAdapter myAdapter;
    ListView list;
    String[] friend = new String[]{"1.1", "2.2", "3.3"};
    List<Map<String, Object>> listData;

    LocalReceiver localReceiver;
    LocalBroadcastManager localBroadcastManager;
    IntentFilter intentFilter;

    Handler handler;
    ClientThread clientThread;

    Person person;

    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String name = intent.getStringExtra("name");
            String msg = intent.getStringExtra("msg");
            Map map = new HashMap();
            if (!findInMyListData(name, listData)) {
                map.put("name", name);
              //  listData.add(map);
                map.put("msg", msg);
                listData.add(map);
            //    listData.add(getPostionInMyListData(name), map);
                list.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            } else {
                listData.remove(getPostionInMyListData(name));
                map.put("name", name);
                map.put("msg", msg);
                listData.add(map);
               // listData.add(getPostionInMyListData(name), map);
                list.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }
        }
    }

    // public final static String ACTION_RECEIVER = "MY_RECEIVER";

    //当listData中存在和str一样的内容时，返回true，否则返回false
    private boolean findInMyListData(String str, List<Map<String, Object>> listData) {
        for (int postion = 0; postion < listData.size(); postion++) {
            if (listData.get(postion).get("name").equals(str)) {
                return true;
            }
        }

        return false;
    }

    private int getPostionInMyListData(String name) {
        for (int postion = 0; postion < listData.size(); postion++) {
            if (listData.get(postion).get("name").equals(name)) {
                return postion;
            }
        }

        return -1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firstFragmentLayout = getActivity().getLayoutInflater().inflate(R.layout.fragment_first, null);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //firstFragmentLayout = inflater.inflate(R.layout.fragment_first, null);
        return firstFragmentLayout;
    }

    private void init() {
        person  = new Person();

        list = (ListView) firstFragmentLayout.findViewById(R.id.list_first);
        localBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.my_boradcast");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);

        listData = new ArrayList<>();
        myAdapter = new MyAdapter();
        for (String s : friend) {
            Map map = new HashMap();
            map.put("name", s);
            listData.add(map);
        }
        list.setAdapter(myAdapter);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    Intent intent = new Intent("com.example.my_boradcast");
//                    intent.putExtra("name", friend_name.getText().toString());
//                    intent.putExtra("msg", listData.get(listData.size() - 1).getMessage().toString());
                    intent.putExtra("name","3.4");
                    intent.putExtra("msg",((Person)msg.obj).getName().toString());
                    localBroadcastManager.sendBroadcast(intent);

                }
            }
        };

        clientThread = new ClientThread(handler);
        new Thread(clientThread).start();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = listData.get(position).get("name").toString();
                Intent intent = new Intent(getActivity(), TalkActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder my_dialog = new AlertDialog.Builder(getActivity());
                my_dialog.setNegativeButton("detele", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "detele", Toast.LENGTH_SHORT).show();
                        listData.remove(position);
                        list.setAdapter(myAdapter);
                        myAdapter.notifyDataSetChanged();
                    }
                }).create().show();
                return true;
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
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
                convertView = getActivity().getLayoutInflater().inflate(R.layout.layout_firstfragment_item, null);
                viewHolder.icon = (ImageView) convertView.findViewById(R.id.firstfragment_icon);
                viewHolder.name = (TextView) convertView.findViewById(R.id.firstfragment_name);
                viewHolder.message = (TextView) convertView.findViewById(R.id.firstfragment_message);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.name.setText(listData.get(position).get("name").toString());
            if (listData.get(position).get("msg") != null) {
                viewHolder.message.setText(listData.get(position).get("msg").toString());
            }
            return convertView;
        }

        private class ViewHolder {
            ImageView icon;
            TextView name;
            TextView message;
        }
    }
}
