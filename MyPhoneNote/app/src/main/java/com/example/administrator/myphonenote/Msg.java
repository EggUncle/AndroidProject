package com.example.administrator.myphonenote;

/**
 * Created by Administrator on 16.1.15.
 */
public class Msg {
    String msg;
    int key_msg;
    static  final  int MSG_SEND = 1;
    static   final  int MSG_RECVICE = 0;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getKey_msg() {
        return key_msg;
    }

    public void setKey_msg(int key_msg) {
        this.key_msg = key_msg;
    }
}
