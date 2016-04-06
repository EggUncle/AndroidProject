package com.example.administrator.dianmin;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CallActivity2 extends AppCompatActivity {

    //点名界面 改    修改点名中的ListView的显现方式，由逐条显示改为整个显示，点名项目背景颜色改变

    @ViewInject(R.id.mylist)
    ListView mylist;
    @ViewInject(R.id.arrive)
    Button arrive_btn;
    @ViewInject(R.id.not_arrive)
    Button not_arrive_btn;
    @ViewInject(R.id.late)
    Button late_btn;
    @ViewInject(R.id.sick_leave)
    Button sick_btn;
    @ViewInject(R.id.next_student)
    TextView text_next_student;

    final String SQL_SELECT =
            "select _id,s_name,time,time_not_arrive,time_late,time_sick " +
                    "from student " +
                    "where class=?";

    final String SQL_SELECT_2 =
            "select _id,s_name,time,time_not_arrive,time_late,time_sick " +
                    "from student";
    final String SQL_SELECT_RANDOM = "select _id,s_name,time,time_not_arrive,time_late,time_sick " +
            "from student " +
            "where ";
    final String SQL_SELECT_NUM = "select count(class) from student group by class ";

    String MY_SQL_SELECT = "";
    //    select _id,s_name,time from student
//    select _id,s_name,time,time_not_arrive,time_late,time_sick from student
    MyDatabaseHelper myDatabaseHelper;
    List<Map<String, Object>> listData;
    MyAdapter myAdapter = new MyAdapter();
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        ViewUtils.inject(this);
        listData = new ArrayList<>();
        myDatabaseHelper = new MyDatabaseHelper(this, this.getFilesDir().toString() + "/mydb_student.db3", 1);
        cursor = myDatabaseHelper.getReadableDatabase().rawQuery(SQL_SELECT_2, null);
        while (cursor.moveToNext()) {
            Map<String, Object> map = null;
            map = new HashMap<>();
            String _id = cursor.getString(0).toString();
            String s_name = cursor.getString(1).toString();
            String time = cursor.getString(2).toString();
            map.put("_id", _id);
            map.put("s_name", s_name);
            map.put("miss_time", time);
            listData.add(map);
        }
        mylist.setAdapter(myAdapter);
    }

    @OnClick({R.id.arrive, R.id.late, R.id.not_arrive, R.id.sick_leave})
    public void btnOnclick(View view) {
        switch (view.getId()) {
            case R.id.arrive: {    //到了

            }
            break;
            case R.id.late: {  //迟到

            }
            break;
            case R.id.not_arrive: {  //旷课

            }
            break;
            case R.id.sick_leave: {  //病假

            }
            break;
        }
    }

    public void CursorMove(){

    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return listData.size();
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
                convertView = getLayoutInflater().inflate(R.layout.list_item, null);
                viewHolder.student_name = (TextView) convertView.findViewById(R.id.student_name);
                viewHolder.student_id = (TextView) convertView.findViewById(R.id.student_id);
                viewHolder.student_miss_time = (TextView) convertView.findViewById(R.id.student_miss_time);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.student_name.setText(listData.get(position).get("s_name").toString());
            viewHolder.student_id.setText(listData.get(position).get("_id").toString());
            viewHolder.student_miss_time.setText(listData.get(position).get("miss_time").toString());
            return convertView;
        }

        private class ViewHolder {
            TextView student_name;
            TextView student_id;
            TextView student_miss_time;
        }
    }


    public void CursorMove_2() {
        cursor = myDatabaseHelper.getReadableDatabase().rawQuery(SQL_SELECT_2, null);
        while (cursor.moveToNext()) {
            Map<String, Object> map = new HashMap<>();
            map.put("_id", cursor.getString(0));
            map.put("s_name", cursor.getString(1));
            map.put("miss_time", cursor.getString(2));
            listData.add(map);
        }
        mylist.setAdapter(myAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myDatabaseHelper != null) {
            myDatabaseHelper.close();
        }
    }
}
