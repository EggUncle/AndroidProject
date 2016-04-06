package com.example.administrator.dianmin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class MainActivity extends AppCompatActivity {

    //登录界面

    @ViewInject(R.id.sign_in_btn)
    Button sign_in_btn;
    @ViewInject(R.id.username_edit)
    EditText usename_edit;
    @ViewInject(R.id.password_edit)
    EditText password_edit;

    MyDatabaseHelper myDatabaseHelper,myDatabaseHelper_2;
    Cursor cursor;
    Cursor cursor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        myDatabaseHelper = new MyDatabaseHelper(this, this.getFilesDir().toString() + "/mydb_student.db3", 1);
        cursor = myDatabaseHelper.getReadableDatabase().rawQuery("select * from teacher ", null);
    }

    @OnClick({R.id.sign_in_btn, R.id.registered_btn})
    public void btnOnClick(View v) {

        Boolean usename_key = false;
        Boolean password_key = false;
        switch (v.getId()) {
            case R.id.sign_in_btn: {
                cursor=null;
                cursor = myDatabaseHelper.getReadableDatabase().rawQuery("select * from teacher ", null);
//                myDatabaseHelper_2 = new MyDatabaseHelper(this, this.getFilesDir().toString() + "/mydb_student.db3", 1);
//                cursor = myDatabaseHelper_2.getReadableDatabase().rawQuery("select * from teacher ", null);
                String usename = usename_edit.getText().toString();
                String password = password_edit.getText().toString();
                while (cursor.moveToNext()) {
                    Log.v("msg", usename);
                    Log.v("msg", password);
                    Log.v("cursor msg", cursor.getString(2).toString());
                    Log.v("cursor msg", cursor.getString(3).toString());
                    if (usename.equals(cursor.getString(2).toString())) {
                        usename_key = true;
                        if (password.equals(cursor.getString(3).toString())) {
                            password_key = true;
                        }
                    }
                }
                if (usename_key && password_key) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case R.id.registered_btn: {

                LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.registered_layout, null);
                final EditText name_edit = (EditText) linearLayout.findViewById(R.id.name_edit);
                final EditText username_edit = (EditText) linearLayout.findViewById(R.id.username_edit);
                final EditText password_edit = (EditText) linearLayout.findViewById(R.id.password_edit);
                new AlertDialog.Builder(this).setTitle("注册")
                        .setView(linearLayout)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("注册", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name = name_edit.getText().toString();
                                String username = username_edit.getText().toString();
                                String password = password_edit.getText().toString();

                                //     Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
                                if (name.length() == 0 || username.length() == 0 || password.length() == 0) {
                                    Toast.makeText(MainActivity.this, "有资料为空！", Toast.LENGTH_LONG).show();
                                } else {
                                    //查询到最大ID，后移以备添加
                                    cursor = myDatabaseHelper.getReadableDatabase().rawQuery("select max(_id) from teacher ", null);
                                    cursor.moveToNext();
                                    int max_id = Integer.parseInt(cursor.getString(0).toString());
                                    max_id++;
                                    String max_id_str = max_id + "";

                                   myDatabaseHelper.getReadableDatabase().execSQL(
                                            "insert  into teacher values(?,?,?,?)"
                                            , new String[]{max_id + "", name, username, password});
                                    Toast.makeText(MainActivity.this, max_id_str, Toast.LENGTH_LONG).show();

                                 //   cursor = myDatabaseHelper.getReadableDatabase().rawQuery("select * from teacher ", null);
                                }
                            }
                        })
                        .create().show();
            }

            break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myDatabaseHelper != null) {
            myDatabaseHelper.close();
        }
    }
}
