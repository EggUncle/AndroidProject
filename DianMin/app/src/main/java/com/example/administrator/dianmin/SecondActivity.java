package com.example.administrator.dianmin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.w3c.dom.Text;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {


    //点名功能选择界面

    @ViewInject(R.id.call_all_btn)
    Button call_all_btn;
    @ViewInject(R.id.call_random_btn)
    Button call_random_btn;
    @ViewInject(R.id.history_btn)
    Button history_btn;
    @ViewInject(R.id.spinner)
    Spinner spinner;

//    //自定义View的 dialog中的控件
//    Button position_btn;
//    Button negative_btn;
//    SeekBar my_seek_bar;

    String[] my_class = new String[]{"一班", "二班", "全部"};

    MyDatabaseHelper myDatabaseHelper;
    ArrayAdapter<String> spinner_adapter;
    Cursor cursor;
    String now_class = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ViewUtils.inject(this);

        //建立数据库  学生
        myDatabaseHelper = new MyDatabaseHelper(this, this.getFilesDir().toString() + "/mydb_student.db3", 1);
        spinner_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, my_class);
        spinner.setAdapter(spinner_adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SecondActivity.this, my_class[position], Toast.LENGTH_LONG).show();
                now_class = my_class[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick({R.id.call_all_btn, R.id.call_random_btn, R.id.history_btn,/*R.id.test_btn*/})
    public void btnOnClick(View v) {
        switch (v.getId()) {
            case R.id.call_all_btn: {
                //全点
//                Cursor cursor = myDatabaseHelper.getReadableDatabase().rawQuery("select * from *",null);
//                Bundle data = new Bundle();
                //  data.putSerializable("data",    );
                Intent intent = new Intent(SecondActivity.this, CallActivity.class);
                intent.putExtra("now_class", now_class);
                startActivity(intent);
            }
            break;
            case R.id.call_random_btn: {
                //抽点
                setMyDialog();
            }
            break;
            case R.id.history_btn: {
                //历史记录   （查看数据库信息）
                Intent intent = new Intent(SecondActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
            break;
//            case R.id.test_btn:{
//                //测试选项
//                Intent intent = new Intent(SecondActivity.this, CallActivity2.class);
//                startActivity(intent);
//            }break;
        }
    }

    public void setMyDialog() {
        //设置自定义view对话框
        final TextView textView;

        final int[] random_num = null;

        SeekBar seekBar;

        switch (now_class) {
            case "一班":
                cursor = myDatabaseHelper.getReadableDatabase().rawQuery(
                        "select count(_id) from student where class='一班'", null);
                break;
            case "二班":
                cursor = myDatabaseHelper.getReadableDatabase().rawQuery(
                        "select count(_id) from student where class='二班'", null);
                break;
            case "全部":
                cursor = myDatabaseHelper.getReadableDatabase().rawQuery(
                        "select count(_id) from student ", null);
                break;
        }

        cursor.moveToNext();
        int student_num = Integer.parseInt(cursor.getString(0).toString());
        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_dialog, null);
        seekBar = (SeekBar) linearLayout.findViewById(R.id.my_seek_bar);
        textView = (TextView) linearLayout.findViewById(R.id.num_txt);

        seekBar.setMax(student_num);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(progress + "");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new AlertDialog.Builder(this).setView(linearLayout)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Integer.parseInt(textView.getText().toString()) == 0) {
                          Toast.makeText(SecondActivity.this,"抽点人数不能为0!",Toast.LENGTH_LONG).show();

                        } else {
                            Intent intent = new Intent(SecondActivity.this, CallActivity.class);
                            int random_num = Integer.parseInt(textView.getText().toString());
                            intent.putExtra("now_class", now_class);
                            intent.putExtra("random_num", random_num);
                            startActivity(intent);
                        }
                    }
                })
                .create().show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myDatabaseHelper != null) {
            myDatabaseHelper.close();
        }
    }
}
