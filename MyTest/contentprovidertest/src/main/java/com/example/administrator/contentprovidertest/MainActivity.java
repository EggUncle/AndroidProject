package com.example.administrator.contentprovidertest;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn,btn2;
    ContentResolver resolver;
    final static String MYURI = "com.android.mycontentprovider";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Uri uri = Uri.parse("content://"+"com.android.mycontentprovider");
        btn  = (Button) findViewById(R.id.btn);
        btn2= (Button) findViewById(R.id.btn2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolver = getContentResolver();
                resolver.query(uri,null,null,null,null);
                ContentValues values = new ContentValues();
                values.put("s_name","张珊");
                values.put("time","5");
                resolver.insert(Uri.parse("content://" + MYURI + "/test/aa"), values);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                resolver.delete(uri,null,null);
            }
        });
    }
}
