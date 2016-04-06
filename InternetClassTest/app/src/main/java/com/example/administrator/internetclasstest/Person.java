package com.example.administrator.internetclasstest;

import java.io.Serializable;

/**
 * Created by shadow on 16.2.21.
 */
public class Person implements Serializable {
    String name;
    String my_msg;

    public String getMy_msg() {
        return my_msg;
    }

    public void setMy_msg(String my_msg) {
        this.my_msg = my_msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
