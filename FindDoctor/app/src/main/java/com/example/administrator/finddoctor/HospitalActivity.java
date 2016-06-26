package com.example.administrator.finddoctor;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.finddoctor.MyFragment.FragmentInHosAdapter;
import com.example.administrator.finddoctor.MyFragment.MyFragmentAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalActivity extends AppCompatActivity {

    ViewPager doctor_viewpager;
    FragmentInHosAdapter fragmentInHosAdapter;

    ScrollView scrollView;
    Button btn_doctor;
    Button btn_type;
    ImageButton btn_hos_back;

    List<Map<String, Object>> listData;
    //    List<Map<String,Object>> listData_type;
//    List<Map<String,Object>>  listData_doctor;
    String[][] listData_type = {
            {"科室1", "医生1.1", "医生1.2", "医生1.3"},
            {"科室2", "医生2.1", "医生2.2", "医生2.3"},
            {"科室3", "医生3.1", "医生3.2", "医生3.3"},
    };

    Map map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_hospital);
        getSupportActionBar().hide();
        init();
        setMyClick();

    }

    private void init() {
        btn_hos_back = (ImageButton) findViewById(R.id.btn_hos_back);
        btn_doctor = (Button) findViewById(R.id.fragment_doctor);
        btn_type = (Button) findViewById(R.id.fragment_type);
        doctor_viewpager = (ViewPager) findViewById(R.id.doctor_viewpager);
        listData = new ArrayList<>();
//        listData_type = new ArrayList<>();
//        listData_doctor = new ArrayList<>();
        scrollView = (ScrollView) findViewById(R.id.ScrollView);
        scrollView.setSmoothScrollingEnabled(true);
        scrollView.smoothScrollTo(0,0);

        for (int i = 0; i < 5; i++) {
            map = new HashMap();
            map.put("type_name", "评论  " + i);
            listData.add(map);
        }
        //  list_type.setAdapter(myExpandableListAdapter);

        //  list_evaluation.setAdapter(myListAdapter);

        FragmentManager fm = getSupportFragmentManager();
        fragmentInHosAdapter = new FragmentInHosAdapter(fm, this);
        doctor_viewpager.setAdapter(fragmentInHosAdapter);
    }

    private void setMyClick(){
        btn_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctor_viewpager.setCurrentItem(0);
            }
        });

        btn_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctor_viewpager.setCurrentItem(1);
            }
        });

        btn_hos_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =  new Intent(HospitalActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
