package com.example.administrator.internetclasstest;

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
import java.util.List;

public class ClientThread implements Runnable
{
	private Socket s;
	// 定义向UI线程发送消息的Handler对象
	private Handler handler;
	// 定义接收UI线程的消息的Handler对象
	public Handler revHandler;
	// 该线程所处理的Socket所对应的输入流
	BufferedReader br = null;
	//OutputStream os = null;


	ObjectInputStream is;
	ObjectOutputStream os;

	List<ObjectInputStream> is_list;
//	Person person;

	public ClientThread(Handler handler)
	{
		this.handler = handler;
	}
	public void run()
	{
		try
		{
        //     person = new Person();
			s = new Socket("115.159.124.100", 29999);
			br = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
		//	os = s.getOutputStream();
			os = new ObjectOutputStream(s.getOutputStream());
			is= new ObjectInputStream(new BufferedInputStream(s.getInputStream()));

			// 启动一条子线程来读取服务器响应的数据
			new Thread()
			{
				@Override
				public void run()
				{
					String content = null;
					Person person = new Person();
					// 不断读取Socket输入流中的内容
					try
					{

						while ((person = (Person) is.readObject()) != null)
					//	while((content = br.readLine())!=null)
						{
						//	Log.v("MY_TAG","sssssssss");
							// 每当读到来自服务器的数据之后，发送消息通知程序
							// 界面显示该数据
							Message msg = new Message();
							msg.what = 0x123;
							msg.obj = person;
							Log.v("MY_TAG",((Person)msg.obj).getMy_msg()+"  接收");
							handler.sendMessage(msg);
						}
					}
					catch (IOException e)
					{
						Log.v("MY_TAG","123");
						e.printStackTrace();
					}
					catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}.start();
			// 为当前线程初始化Looper
			Looper.prepare();
			// 创建revHandler对象
			revHandler = new Handler()
			{
				@Override
				public void handleMessage(Message msg)
				{
					// 接收到UI线程中用户输入的数据
					if (msg.what == 0x345)
					{
						// 将用户在文本框内输入的内容写入网络
						try
						{
						//	os.write((msg.obj.toString() + "\r\n")
						//			.getBytes("utf-8"));
						 //	 os= new ObjectOutputStream(s.getOutputStream());
							Log.v("MY_TAG",((Person)msg.obj).getMy_msg()+"  写入");
                              os.writeObject(msg.obj);
						}
						catch (Exception e)
						{
							Log.v("MY_TAG","123");
							e.printStackTrace();
						}
					}
				}
			};
			// 启动Looper
			Looper.loop();
		}
		catch (SocketTimeoutException e1)
		{
			System.out.println("网络连接超时！！");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

