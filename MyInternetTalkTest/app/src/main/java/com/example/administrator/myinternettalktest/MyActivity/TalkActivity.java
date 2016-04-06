package com.example.administrator.myinternettalktest.MyActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myinternettalktest.MainActivity;
import com.example.administrator.myinternettalktest.MyClass.ClientThread2;
import com.example.administrator.myinternettalktest.MyClass.Msg;
import com.example.administrator.myinternettalktest.Person;
import com.example.administrator.myinternettalktest.R;

import java.util.ArrayList;
import java.util.List;

public class TalkActivity extends Activity {

    EditText user_input;
    ListView talk_list;
    TextView friend_name;

    Button btn_send;
    Button btn_back;
    Button btn_menu;

    Handler handler;
    //  OutputStream os;
    ClientThread2 clientThread;

    List<Msg> listData;
    Msg myMsg_rec;
    Msg myMsg_send;
    MyTalkListAdapter myTalkListAdapter;
    Bitmap bitmap;

    LocalBroadcastManager localBroadcastManager;
    public Person person;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);
        init();
        setMyClickListener();
    }

    private void init() {
        person = new Person();
        String name = getIntent().getStringExtra("name");

        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        user_input = (EditText) findViewById(R.id.user_input);

        talk_list = (ListView) findViewById(R.id.talk_list);
        talk_list.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        friend_name = (TextView) findViewById(R.id.talk_name);


        friend_name.setText(name);

        btn_send = (Button) findViewById(R.id.send_btn);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_menu = (Button) findViewById(R.id.my_menu);

        //  myMsg_rec.setMsg_type(Msg.MSG_RECEIVE);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        listData = new ArrayList<>();
        myTalkListAdapter = new MyTalkListAdapter();
        talk_list.setAdapter(myTalkListAdapter);

        //模拟预先收到了一条消息
        Msg msg = new Msg();
        msg.setBitmap(bitmap);
        msg.setMsg_type(Msg.MSG_RECEIVE);
        msg.setMessage("Hello world");
        listData.add(msg);
        msg = null;


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    myMsg_rec = new Msg();
                    // person =(Person)msg.obj;
                 //   if(!friend_name.getText().toString().equals(((Person) msg.obj).getName().toString()))
                    {
                    myMsg_rec.setMessage(((Person) msg.obj).getMy_msg().toString());
                    myMsg_rec.setMsg_type(Msg.MSG_RECEIVE);
                    myMsg_rec.setBitmap(bitmap);
                    listData.add(myMsg_rec);
                    talk_list.setAdapter(myTalkListAdapter);
                    myTalkListAdapter.notifyDataSetChanged();}
                }
            }
        };

        clientThread = new ClientThread2(handler);

//        new Thread() {
//            public void run() {
//                Socket socket;
//                try {
//                    socket = new Socket("115.159.124.100", 29999);
//                    // 客户端启动ClientThread线程不断读取来自服务器的数据
//                    new Thread(new ClientThread(socket, handler)).start();
//                    os = socket.getOutputStream();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//
//        }.start();
        new Thread(clientThread).start();

    }


    private void setMyClickListener() {
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!user_input.getText().toString().equals("")) {
                    try {
                        person = new Person();
                        // 将用户在文本框内输入的内容写入网络
                        Message msg = new Message();
                        msg.what = 0x345;
                        //  msg.obj = user_input.getText().toString();
                        person.setMy_msg(user_input.getText().toString());
                        person.setName(friend_name.getText().toString());
                        msg.obj = person;
                        clientThread.revHandler.sendMessage(msg); // 空指针异常？
                        //    os.write((user_input.getText().toString()).getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.v("MY_TAG", "ERROR!");
                    }

                    myMsg_send = new Msg();
                    myMsg_send.setMessage(user_input.getText().toString());
                    myMsg_send.setMsg_type(Msg.MSG_SEND);
                    myMsg_send.setBitmap(bitmap);
                    listData.add(myMsg_send);
                    myTalkListAdapter.notifyDataSetChanged();
                    user_input.setText("");


                    myMsg_send = null;
                    person=null;
                    Intent intent = new Intent("com.example.my_boradcast");
                    intent.putExtra("name", friend_name.getText().toString());
                    intent.putExtra("msg", listData.get(listData.size() - 1).getMessage().toString());
                    localBroadcastManager.sendBroadcast(intent);
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(TalkActivity.this, MainActivity.class));
                finish();
            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TalkActivity.this, "菜单", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private class MyTalkListAdapter extends BaseAdapter {

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
            TalkViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new TalkViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.layout_talk_item, null);

                viewHolder.line_rec = (LinearLayout) convertView.findViewById(R.id.line_rec);
                viewHolder.line_send = (LinearLayout) convertView.findViewById(R.id.line_send);
                viewHolder.talk_left_icon = (ImageView) convertView.findViewById(R.id.talk_left_icon);
                viewHolder.talk_right_icon = (ImageView) convertView.findViewById(R.id.talk_right_icon);
                viewHolder.msg_rec = (TextView) convertView.findViewById(R.id.msg_rec);
                viewHolder.msg_send = (TextView) convertView.findViewById(R.id.msg_send);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (TalkViewHolder) convertView.getTag();
            }

            int msg_type = listData.get(position).getMsg_type();
            if (msg_type == Msg.MSG_RECEIVE) {
                viewHolder.line_rec.setVisibility(View.VISIBLE);
                viewHolder.line_send.setVisibility(View.GONE);
                viewHolder.talk_left_icon.setImageBitmap(listData.get(position).getBitmap());
                viewHolder.msg_rec.setText(listData.get(position).getMessage());
            } else {
                viewHolder.line_rec.setVisibility(View.GONE);
                viewHolder.line_send.setVisibility(View.VISIBLE);
                viewHolder.talk_right_icon.setImageBitmap(listData.get(position).getBitmap());
                viewHolder.msg_send.setText(listData.get(position).getMessage());
            }

            return convertView;
        }

        private class TalkViewHolder {
            LinearLayout line_rec;
            LinearLayout line_send;
            ImageView talk_left_icon;
            ImageView talk_right_icon;
            TextView msg_rec;
            TextView msg_send;
        }
    }
}
