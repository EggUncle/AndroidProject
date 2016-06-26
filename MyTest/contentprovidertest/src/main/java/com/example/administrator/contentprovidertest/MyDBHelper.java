package com.example.administrator.contentprovidertest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by egguncle on 16.4.24.
 */
public class MyDBHelper extends SQLiteOpenHelper {

    final String CREATE_STUDENT_TABLE =
            "create table student(" +
                    "_id integer primary key autoincrement," +
                    "s_name varchar(20)," +
                    "time integer)" ;

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
