package com.example.administrator.saxtest;

import java.io.Serializable;

/**
 * Created by Administrator on 16.1.16.
 */
public class Book implements Serializable{
    String bookName;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
