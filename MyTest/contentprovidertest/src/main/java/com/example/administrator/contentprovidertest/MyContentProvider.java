package com.example.administrator.contentprovidertest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MyContentProvider extends ContentProvider {
    private MyDBHelper myDBHelper;
    private SQLiteDatabase db;
    private static final String AUTHORITY = "com.android.mycontentprovider";
    private static final Uri NOTIFY_URI = Uri.parse("content://" + AUTHORITY + "/test");
    private static final UriMatcher matcher;

    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, "test", 1);   //匹配记录集合
        matcher.addURI(AUTHORITY, "test/aa", 2); //匹配单条记录
    }

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        //   throw new UnsupportedOperationException("Not yet implemented");
        db.delete("student", "s_name = ?", new String[]{"张珊",});
        return 1;
    }

    @Override
    public String getType(Uri uri) {

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        db = myDBHelper.getWritableDatabase();
        switch (matcher.match(uri)) {
            case 1:
                Log.v("MY_TAG", 1 + "");
                break;
            case 2:
                Log.v("MY_TAG", 2 + "");
                break;
        }
        db.insert("student", null, values);
        return null;
    }

    @Override
    public boolean onCreate() {
        Log.v("MY_TAG", "onCreate");
        myDBHelper = new MyDBHelper(getContext(), "student", null, 1);

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        Log.v("MY_TAG", "query");
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
