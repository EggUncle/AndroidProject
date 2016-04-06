package com.example.administrator.myphonenote;


import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myphonenote.MyFragment.MyFragmentAdapter;


public class UserActivity extends AppCompatActivity {

    ViewPager pager;

    MyFragmentAdapter fragmentAdapter;
    Button btn_1, btn_2, btn_3;//btn_4;
    public

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
          init();

//        new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message arg0) {
//                if (arg0.what == 1) {
//                    init();//初始化ViewPager
//                }
//                return false;
//            }
//        }).sendEmptyMessage(1);
    }

    private void init() {
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        //  btn_4 = (Button) findViewById(R.id.btn_4);
        pager = (ViewPager) findViewById(R.id.my_viewpager);

        //   new FragmentPagerAdapter(getFragmentManager());

        FragmentManager fm = getSupportFragmentManager();
        fragmentAdapter = new MyFragmentAdapter(fm);
        setMyLisnter();
        pager.setAdapter(fragmentAdapter);
    }

    private void setMyLisnter() {
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 0;
                pager.setCurrentItem(i, false);
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 1;
                Log.v("my_msg", i + "");
                pager.setCurrentItem(i, false);
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 2;
                Log.v("my_msg", i + "");
                pager.setCurrentItem(i, false);
            }
        });
//        btn_4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                i = 3;
//                Log.v("my_msg", i + "");
//                pager.setCurrentItem(i, false);
//            }
//        });
    }


}