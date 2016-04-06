package com.example.administrator.myphonenote;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.myphonenote.MyFragment.FirstFragment;
import com.example.administrator.myphonenote.MyFragment.FourthFragment;
import com.example.administrator.myphonenote.MyFragment.MyFragmentAdapter;
import com.example.administrator.myphonenote.MyFragment.SecondFragment;
import com.example.administrator.myphonenote.MyFragment.ThridFragment;


public class UserActivity extends AppCompatActivity {

    ViewPager pager;
    MyFragmentAdapter fragmentAdapter;

    Button btn_1, btn_2, btn_3, btn_4;

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        init();

        pager.setAdapter(fragmentAdapter);
    }

    private void init() {
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        pager = (ViewPager) findViewById(R.id.my_viewpager);
        FragmentManager fm = getSupportFragmentManager();
        fragmentAdapter = new MyFragmentAdapter(fm);
        setMyLisnter();
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
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 3;
                Log.v("my_msg", i + "");
                pager.setCurrentItem(i, false);
            }
        });
    }


}

