package com.example.administrator.myinternettalktest.MyClass;

/**
 * Created by shadow on 16.2.20.
 */

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ClientThread2 implements Runnable {
    private Socket s;
    // 定义向UI线程发送消息的Handler对象
    private Handler handler;
    // 定义接收UI线程的消息的Handler对象
    public Handler revHandler;
    // 该线程所处理的Socket所对应的输入流
//    BufferedReader br = null;
//    OutputStream os = null;
    ObjectOutputStream os = null;
    ObjectInputStream is = null;

    public ClientThread2(Handler handler) {
        this.handler = handler;
    }

    public void run() {
        try {
            s = new Socket("115.159.124.100", 29999);
//            br = new BufferedReader(new InputStreamReader(
//                    s.getInputStream()));
//            os = s.getOutputStream();
            os = new ObjectOutputStream(s.getOutputStream());
            // 启动一条子线程来读取服务器响应的数据
            new Thread() {
                @Override
                public void run() {
                //    String content = null;
                     Object obj=null;
                    // 不断读取Socket输入流中的内容
                    try {
                        is = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
                       // Object obj = is.readObject();
                        while ((obj=is.readObject()) != null) {
                            // 每当读到来自服务器的数据之后，发送消息通知程序
                            // 界面显示该数据
                            Log.v("MY_TAG","通知程序");
                            Message msg = new Message();
                            msg.what = 0x123;
                            msg.obj = obj;
                            handler.sendMessage(msg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.v("MY_TAG", "通知程序出错");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        Log.v("MY_TAG", "通知程序出错");
                    }
                }
            }.start();
            // 为当前线程初始化Looper
            Looper.prepare();
            // 创建revHandler对象
            revHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    // 接收到UI线程中用户输入的数据
                    if (msg.what == 0x345) {
                        // 将用户在文本框内输入的内容写入网络
                        try {
                            Log.v("MY_TAG","写入网络");
                            os.writeObject(msg.obj);

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.v("MY_TAG","写入网络时出错");
                        }
                    }
                }
            };
            // 启动Looper
            Looper.loop();
        } catch (SocketTimeoutException e1) {
            System.out.println("网络连接超时！！");
            Log.v("MY_TAG", "网络连接超时！！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

