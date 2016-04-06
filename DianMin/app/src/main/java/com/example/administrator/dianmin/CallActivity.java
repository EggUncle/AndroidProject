package com.example.administrator.dianmin;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArraySet;
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

public class CallActivity extends AppCompatActivity {

    //点名界面

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
    int[] num_of_class=new int[2];
    int class_num = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        ViewUtils.inject(this);
        myDatabaseHelper = new MyDatabaseHelper(this, this.getFilesDir().toString() + "/mydb_student.db3", 1);
        listData = new ArrayList<>();
        Intent intent = getIntent();
        int random_num = intent.getIntExtra("random_num", -1);
        String now_class = intent.getStringExtra("now_class");

        cursor = myDatabaseHelper.getReadableDatabase().rawQuery(SQL_SELECT_NUM, null);
        while (cursor.moveToNext()) {
           num_of_class[class_num++] = Integer.parseInt(cursor.getString(0).toString());
        }


        //判断是否是抽点
        if (random_num == -1) {
            //判断点名范围  一班  二班  全点   用switch
            switch (now_class) {
                case "一班":
                    cursor = myDatabaseHelper.getReadableDatabase().rawQuery(SQL_SELECT, new String[]{"一班"});
                    break;
                case "二班":
                    cursor = myDatabaseHelper.getReadableDatabase().rawQuery(SQL_SELECT, new String[]{"二班"});
                    break;
                case "全部":
                    cursor = myDatabaseHelper.getReadableDatabase().rawQuery(SQL_SELECT_2, null);
                    //     cursor = myDatabaseHelper.getReadableDatabase().rawQuery(SQL_SELECT,new String[]{"' 一班 ' or '二班‘"});
                    break;
            }

        } else {
            // Toast.makeText(CallActivity.this, random_num + "", Toast.LENGTH_SHORT).show();
            //   设置随机数组点名
            int random_nums[] = null;

            Set<Integer> set_random = new HashSet<>();

            //直接控制随机范围来控制抽点的范围  先取出各个班的人数
            switch (now_class) {
                case "一班":
                    judgeRandom(set_random, random_num, num_of_class[0], 0);
                 //   judgeRandom(set_random, random_num, 10, 0);
                    break;
                case "二班":
                    judgeRandom(set_random, random_num, num_of_class[1], num_of_class[0]);
                  //  judgeRandom(set_random, random_num, 10, 10);
                    break;
                case "全部":
                    judgeRandom(set_random, random_num, num_of_class[0] + num_of_class[1], 0);
                 //   judgeRandom(set_random, random_num, 20, 0);
                    break;
            }

            int i = 0;
            String s = " ";
            for (int my_int : set_random) {
                s = "_id= " + my_int + " or " + s;

            }
            s = SQL_SELECT_RANDOM + s.substring(0, s.length() - 5);
            //    s =SQL_SELECT_RANDOM.substring(0,SQL_SELECT_RANDOM.length()-2)+ s.substring(0, s.length() - 5);
            cursor = myDatabaseHelper.getReadableDatabase().rawQuery(s, null);
            //    cursor = myDatabaseHelper.getReadableDatabase().rawQuery(SQL_SELECT_2, null);
            Toast.makeText(CallActivity.this, s, Toast.LENGTH_LONG).show();
            Log.v("msg", s);
        }


        //显示下一条记录的的学生的名字 先后移标记显示以后再移回来
        cursor.moveToNext();
        text_next_student.setText(cursor.getString(1).toString());
        cursor.moveToPrevious();
        //      initialize();
    }

    //封装判断随机范围的函数，后两个参数分别控制内部范围和外部范围
    public void judgeRandom(Set<Integer> set_random, int random_num, int random_limit, int other_num) {
        while (set_random.size() < random_num) {
            Random random = new Random();
            int my_random = random.nextInt(random_limit) + 1 + other_num;
            set_random.add(my_random);
        }
    }


    @OnClick({R.id.arrive, R.id.late, R.id.not_arrive, R.id.sick_leave})
    public void btnOnclick(View v) {
        switch (v.getId()) {
            case R.id.arrive: {   //到了
                CursorMove(0);
            }
            break;
            case R.id.not_arrive: {
                //旷课
                if (CursorMove(2)) {

                    String time = Integer.parseInt(cursor.getString(2)) + 1 + "";
                    String time_not_arrive = Integer.parseInt(cursor.getString(3)) + 1 + "";
                    myDatabaseHelper.getReadableDatabase()
                            .execSQL("update student set time = ? ,time_not_arrive = ? where _id=?",
                                    new String[]{time, time_not_arrive, cursor.getString(0)});
                } else {
                }
            }
            break;
            case R.id.late: {
                //迟到
                if (CursorMove(1)) {

                    String time = Integer.parseInt(cursor.getString(2)) + 1 + "";
                    String time_late = Integer.parseInt(cursor.getString(4)) + 1 + "";
                    myDatabaseHelper.getReadableDatabase()
                            .execSQL("update student set time = ? ,time_late = ? where _id=?",
                                    new String[]{time, time_late, cursor.getString(0)});
                } else {
                    //    Toast.makeText(CallActivity.this, "点名完成", Toast.LENGTH_SHORT).show();
                }
//                ContentValues values = new ContentValues();
//                values.put("time","100");
//                myDatabaseHelper.getReadableDatabase().update("student",values,null,null);
            }
            break;
            case R.id.sick_leave: {
                //病假
                if (CursorMove(3)) {

                    String time = Integer.parseInt(cursor.getString(2)) + 1 + "";
                    String time_late = Integer.parseInt(cursor.getString(5)) + 1 + "";
                    myDatabaseHelper.getReadableDatabase()
                            .execSQL("update student set time = ? ,time_sick = ? where _id=?",
                                    new String[]{time, time_late, cursor.getString(0)});
                } else {
                    // Toast.makeText(CallActivity.this, "点名完成", Toast.LENGTH_SHORT).show();
                }
//                myDatabaseHelper.getReadableDatabase()
//                        .rawQuery(SQL_UPDATE_LATE, new String[]{"time_sick", "time_sick", cursor.getString(0)});
            }
            break;
        }
    }

    //返回一个Bool判断能否继续点名

    private Boolean CursorMove(int i) {
        boolean next_key;
        if (cursor.moveToNext()) {
            Map<String, Object> map = new HashMap<>();
            //获取查询结果中对应的列中的值
            map.put("_id", cursor.getString(0));
            map.put("s_name", cursor.getString(1));
            if (i != 0) {
                map.put("miss_time", Integer.parseInt(cursor.getString(2)) + 1 + "");
            } else {
                map.put("miss_time", Integer.parseInt(cursor.getString(2)));
            }

            if (cursor.moveToNext()) {
                text_next_student.setText(cursor.getString(1).toString());
                cursor.moveToPrevious();
            } else {
                text_next_student.setText("点名完成");
                cursor.moveToPrevious();
            }
            listData.add(map);
            mylist.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();
            next_key = true;
        } else {
            Toast.makeText(CallActivity.this, "点名完成", Toast.LENGTH_SHORT).show();
            next_key = false;
        }

        return next_key;
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myDatabaseHelper != null) {
            myDatabaseHelper.close();
        }
    }

}
