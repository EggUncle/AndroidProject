package com.example.administrator.dianmin;

import android.app.AlertDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity {

    //查看出勤情况 （浏览数据库）
    @ViewInject(R.id.list_history)
    ListView list_history;

    MyDatabaseHelper myDatabaseHelper;
    List<Map<String, Object>> history_data;
    MyHistoryAdapter myHistoryAdapter;
    Cursor cursor;
    String student_id;
    String student_name;
    String student_class;
    String student_time;
    String student_not_arrive_time;
    String student_late_time;
    String student_sick_time;
    final String SQL_SELECT = "select _id,s_name,class,time,time_not_arrive,time_late,time_sick " +
            "from student";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ViewUtils.inject(this);
        myDatabaseHelper = new MyDatabaseHelper(this, this.getFilesDir().toString() + "/mydb_student.db3", 1);
        history_data = new ArrayList<>();
        myHistoryAdapter = new MyHistoryAdapter();
        cursor = myDatabaseHelper.getReadableDatabase().rawQuery(SQL_SELECT, null);
        while (cursor.moveToNext()) {
            Map<String, Object> map = new HashMap<>();
            //获取查询结果中对应的列中的值
            map.put("_id", cursor.getString(0));
            map.put("s_name", cursor.getString(1));
            map.put("class", cursor.getString(2));
            map.put("miss_time", cursor.getString(3));
            history_data.add(map);
        }
        list_history.setAdapter(myHistoryAdapter);

        //单击列表项的时候，弹出一个对话框，显示学生的详细出勤信息
        list_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //讲cursor移动到对应位置取出数据库中其对应的内容
                cursor.moveToPosition(position);
                simpleDialog(cursor, view);
            }
        });
    }

    //创建简单对话框的方法  显示学生详细出勤信息
    public void simpleDialog(Cursor cursor, View source) {

        student_id = cursor.getString(0).toString();
        student_name = cursor.getString(1).toString();
        student_class = cursor.getString(2).toString();
        student_time = cursor.getString(3).toString();
        student_not_arrive_time = cursor.getString(4).toString();
        student_late_time = cursor.getString(5).toString();
        student_sick_time = cursor.getString(6).toString();
        String[] items = new String[]{
                "学号：" + student_id,
                "班级：" + student_class,
                "缺勤次数：" + student_time,
                "旷课：" + student_not_arrive_time,
                "迟到：" + student_late_time,
                "病假：" + student_sick_time
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(student_name)
                .setItems(items, null);
        builder.setNegativeButton("确定", null).create().show();

    }

    //建立适配器类 显示历史消息 list     可改simpleAdapter

    private class MyHistoryAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return history_data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.list_item_history, null);
                viewHolder.student_name = (TextView) convertView.findViewById(R.id.student_name_history);
                viewHolder.student_id = (TextView) convertView.findViewById(R.id.student_id_history);
                viewHolder.student_miss_time = (TextView) convertView.findViewById(R.id.student_time_histroy);
                viewHolder.student_class = (TextView) convertView.findViewById(R.id.student_class_history);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.student_name.setText(history_data.get(position).get("s_name").toString());
            viewHolder.student_id.setText(history_data.get(position).get("_id").toString());
            int miss_time = Integer.parseInt(history_data.get(position).get("miss_time").toString());
            if(miss_time>=3){
                viewHolder.student_miss_time.setTextColor(Color.RED);
            }
            viewHolder.student_miss_time.setText(miss_time+"");
            viewHolder.student_class.setText(history_data.get(position).get("class").toString());
            return convertView;
        }


        private class ViewHolder {
            TextView student_name;
            TextView student_id;
            TextView student_class;
            TextView student_miss_time;
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
