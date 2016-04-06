package com.example.administrator.sqlitetest;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 15.12.29.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    final String CREATE_MY_TABLE =
            "create table student(" +
                    "_id integer primary key autoincrement," +
                    "s_name varchar(20)," +
                    "class varchar(20)," +
                    "time integer)";

    public MyDatabaseHelper(Context context, String name , int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
           db.execSQL(CREATE_MY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
