package com.example.administrator.finddoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorActivity extends AppCompatActivity {

    ListView list_evaluation_doctor;
    ScrollView scrollView;
    List<Map<String,Object>>  listData;
    MyListAdapter myListAdapter;
    Button btn_evaluation;
    ImageButton btn_doctor_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        getSupportActionBar().hide();
        init();

    }

    private void init(){
        btn_doctor_back= (ImageButton) findViewById(R.id.btn_doctor_back);
        scrollView = (ScrollView) findViewById(R.id.ScrollView);
        btn_evaluation = (Button) findViewById(R.id.btn_evaluation);
        scrollView.setSmoothScrollingEnabled(true);
        scrollView.smoothScrollTo(0,0);
        myListAdapter = new MyListAdapter();
        list_evaluation_doctor = (ListView) findViewById(R.id.list_evaluation_doctor);
        listData = new ArrayList<>();
        Map map;
        for(int i = 0;i<5;i++){
            map = new HashMap();
            map.put("name","用户"+i);
            listData.add(map);
        }
        list_evaluation_doctor.setAdapter(myListAdapter);
   //     ArrayAdapter<String> adapter  = new ArrayAdapter<String>(
//                this,android.R.layout.simple_list_item_1,new String[]{"评论1","评论2","评论3"});
//        list_evaluation_doctor.setAdapter(adapter);

        btn_evaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(DoctorActivity.this,PinLunActivity.class);
                startActivity(intent);
            }
        });
        btn_doctor_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorActivity.this,HospitalActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private class MyListAdapter extends BaseAdapter{

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
            ViewHolder viewHolder;
            if (convertView==null){
                viewHolder= new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.pinlun_item,null);
                viewHolder.txt_user_name = (TextView) convertView.findViewById(R.id.user_name);
                viewHolder.txt_user_pinlun = (TextView) convertView.findViewById(R.id.user_pinlun);
                viewHolder.ratingBar = (RatingBar) convertView.findViewById(R.id.rat_bar_doctor);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }

        private class ViewHolder{
            TextView txt_user_name;
            TextView txt_user_pinlun;
            RatingBar ratingBar;
        }
    }
}
