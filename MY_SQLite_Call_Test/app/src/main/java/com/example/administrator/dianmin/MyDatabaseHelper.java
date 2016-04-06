package com.example.administrator.dianmin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 15.12.29.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    final String CREATE_STUDENT_TABLE =
            "create table student(" +
                    "_id integer primary key autoincrement," +
                    "s_name varchar(20)," +
                    "class varchar(20)," +
                    "time integer," +
                    "time_not_arrive integer," +
                    "time_late integer," +
                    "time_sick integer)";

    final String CREATE_TEACHER_TABLE =
            "create table teacher(" +
                    "_id integer primary key autoincrement," +
                    "t_name varchar(20)," +
                    "username varchar(20)," +
                    "password vatchar(20))";

    public MyDatabaseHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENT_TABLE);
        db.execSQL(CREATE_TEACHER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
