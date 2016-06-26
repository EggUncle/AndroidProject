package com.example.administrator.sockettest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    Button btn;
    Button btn_udp;
    Button btn_img;
    EditText editText;
    Socket socket;
    ImageView imageView;
    DatagramSocket datagramSocket;
    DatagramPacket datagramPacket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }

    private void init(){
        imageView = (ImageView) findViewById(R.id.img);
        btn_img = (Button) findViewById(R.id.img_btn);
        btn = (Button) findViewById(R.id.btn);
        btn_udp = (Button) findViewById(R.id.btn);
        editText = (EditText) findViewById(R.id.input);

        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        URL url=null;
                        try {
                            url = new URL("http://192.168.1.120:8080/Login/1.jpg");
                            URLConnection conn = url.openConnection();
                            InputStream inputStream = conn.getInputStream();
                            final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    imageView.setImageBitmap(bitmap);
                                }
                            });
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            socket     = new Socket("115.159.124.100",8888);
                            OutputStream outputStream  = socket.getOutputStream();
                            outputStream.write(editText.getText().toString().getBytes());
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });

        btn_udp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InetAddress address = null;
                try {
                    address = Inet4Address.getByName("192.168.1.115");
                    datagramSocket = new DatagramSocket(10025);
                    byte[] temp = editText.getText().toString().getBytes();
                    datagramPacket = new DatagramPacket(temp,temp.length,address,10025);
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            try {
                                datagramSocket.send(datagramPacket);
                                datagramSocket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
