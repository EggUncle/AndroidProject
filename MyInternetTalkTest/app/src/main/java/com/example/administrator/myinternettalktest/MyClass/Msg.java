package com.example.administrator.myinternettalktest.MyClass;

import android.graphics.Bitmap;

/**
 * Created by shadow on 16.2.14.
 */
public class Msg {
    public final static int MSG_RECEIVE = 1;
    public final static int MSG_SEND = 0;

    private Bitmap bitmap;
    private String message;
    private int msg_type;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(int msg_type) {
        this.msg_type = msg_type;
    }
}
