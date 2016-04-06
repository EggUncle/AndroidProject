package com.example.administrator.student;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.my_list)
    ListView my_list;

    List<Map<String, Object>> listData;
    MyDatabaseHelper myDatabaseHelper;
    Cursor cursor, cursor2;

    final String SQL_SELECT = "select * from student";
    final String SQL_SELECT_ID = "select _id from student";
    int final_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        myDatabaseHelper = new MyDatabaseHelper(this, "mydb_student.db3", 1);
//        listData = new ArrayList<>();
//        cursor = myDatabaseHelper.getReadableDatabase().rawQuery(SQL_SELECT, null);
//        cursor2 = myDatabaseHelper.getReadableDatabase().rawQuery(SQL_SELECT_ID, null);
//        cursor2.moveToLast();
//        if (cursor2.getString(0).toString() == null) {
//        } else {
//             final_id = Integer.parseInt(cursor2.getString(0).toString());
//        }
//        while (cursor.moveToNext()) {
//            final_id++;
//            Map map = new HashMap();
//            map.put("my_ID", final_id);
//            map.put("my_Name", cursor.getString(0).toString());
//            map.put("my_Number", cursor.getString(1).toString());
//            map.put("my_Grade", cursor.getString(2).toString());
//        }
    }

    public void init() {
        myDatabaseHelper = new MyDatabaseHelper(this, this.getFilesDir().toString() + "/mydb.db3", 1);
        listData = new ArrayList<>();
    }

    private class MyAdapter extends BaseAdapter {

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
                convertView = getLayoutInflater().inflate(R.layout.my_list_item_layout, null);
                viewHolder.ID_text = (TextView) convertView.findViewById(R.id.id);
                viewHolder.Name_text = (TextView) convertView.findViewById(R.id.name);
                viewHolder.Number_text = (TextView) convertView.findViewById(R.id.number);
                viewHolder.Grade_text = (TextView) convertView.findViewById(R.id.grade);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.ID_text.setText(listData.get(position).get("my_ID").toString());
            viewHolder.Name_text.setText(listData.get(position).get("my_Name").toString());
            viewHolder.Number_text.setText(listData.get(position).get("my_Number").toString());
            viewHolder.Grade_text.setText(listData.get(position).get("my_grade").toString());
            return convertView;
        }

        private class ViewHolder {
            TextView ID_text;
            TextView Name_text;
            TextView Number_text;
            TextView Grade_text;
        }
    }
}
