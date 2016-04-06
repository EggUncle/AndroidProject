package com.example.administrator.sqlitetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.createDatabase)
    Button createDatebase_btn;

    @ViewInject(R.id.insert)
    Button insert_btn;

    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        myDatabaseHelper = new MyDatabaseHelper(this, this.getFilesDir().toString() + "/my2db.db3", 2);
      //  myDatabaseHelper.getReadableDatabase().execSQL("insert into student values(1,'a','b',2)");
    }

    @OnClick({R.id.createDatabase, R.id.insert})
    public void btnOnClick(View v) {
        switch (v.getId()) {
            case R.id.createDatabase:

                Toast.makeText(MainActivity.this, "数据库已创建", Toast.LENGTH_SHORT).show();
                break;
            case R.id.insert:

                break;
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(myDatabaseHelper!=null){
            myDatabaseHelper.close();
        }
    }


}
