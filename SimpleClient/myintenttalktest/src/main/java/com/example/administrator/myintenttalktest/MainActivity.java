package com.example.administrator.myintenttalktest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button send;
    ListView list;
    EditText edit;
    TextView text;

    Handler handler;
    ClientThread clientThread;

    ArrayList<Map<String, Object>> data;
    MyAdapter myAdapter;
    Map map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
     //   handler = new Handler();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    map = new HashMap();
          //          text.append("\n"+msg.obj.toString());
                 //   Toast.makeText(MainActivity.this,msg.obj.toString(),Toast.LENGTH_SHORT).show();
                    map.put("msg", msg.obj.toString());
                    data.add(map);
                    list.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                }
            }
        };
        clientThread = new ClientThread(handler);
        new Thread(clientThread).start();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    map = new HashMap();
                    Message msg = new Message();
                    msg.what = 0x345;
                    msg.obj = edit.getText().toString();
                    clientThread.revHandler.sendMessage(msg);
//                    map.put("msg", msg.obj.toString());
//                    data.add(map);
//                    list.setAdapter(myAdapter);
                    edit.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void init() {

        //   text = (TextView) findViewById(R.id.show);
        send = (Button) findViewById(R.id.send);
      list = (ListView) findViewById(R.id.list);
        edit = (EditText) findViewById(R.id.edit);
        data = new ArrayList<>();
        myAdapter = new MyAdapter();
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
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
                viewHolder.textView = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(data.get(position).get("msg").toString());
            return convertView;
        }

        private class ViewHolder {
            TextView textView;
        }
    }
}
