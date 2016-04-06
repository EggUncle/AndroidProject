package com.example.administrator.internetclasstest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button user1, user2;
    Button send;
    EditText input;
    ListView mylist;
    List<String> listData;
    ArrayAdapter adapter;

    Person person;
    String my_msg;



    Handler handler;
    ClientThread clientThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setMyClick();
    }

    private void init() {
        person = new Person();
        person.setName("user1");
        user1 = (Button) findViewById(R.id.btn_user1);
        user2 = (Button) findViewById(R.id.btn_user2);
        send = (Button) findViewById(R.id.send);
        input = (EditText) findViewById(R.id.input);
        mylist = (ListView) findViewById(R.id.my_list);
        listData = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listData);
        mylist.setAdapter(adapter);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {

                 //   my_msg = ((Person)msg.obj).getMy_msg();
                    my_msg = msg.obj.toString();
                    Log.v("MY_TAG",my_msg);
                    listData.add(my_msg);
                    adapter.notifyDataSetChanged();
                    mylist.setAdapter(adapter);

                }
            }
        };
        clientThread = new ClientThread(handler);
        new Thread(clientThread).start();
    }

    private void setMyClick() {
        user1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.setName("uesr1");
                Toast.makeText(MainActivity.this, "当前用户：1", Toast.LENGTH_SHORT).show();
            }
        });
        user2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.setName("user2");
                Toast.makeText(MainActivity.this, "当前用户：2", Toast.LENGTH_SHORT).show();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_msg = input.getText().toString();
                if (!my_msg.equals("")) {
                    person.setMy_msg(my_msg);
                  //  person.setName();
                    try {
                        Message msg = new Message();
                        msg.what = 0x345;
                        msg.obj = my_msg;
                    //    msg.obj = person;
                        clientThread.revHandler.sendMessage(msg);
                        input.setText("");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
