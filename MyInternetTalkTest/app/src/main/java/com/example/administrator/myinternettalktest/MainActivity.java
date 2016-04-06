package com.example.administrator.myinternettalktest;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.myinternettalktest.MyFragment.MyFragmentAdapter;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //主界面
    ViewPager pager;
    MyFragmentAdapter fragmentAdapter;
    Button btn_1, btn_2, btn_3;


    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setMyLisnter();
    }

    private void init() {
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);

        pager = (ViewPager) findViewById(R.id.my_viewpager);
        FragmentManager fm = getSupportFragmentManager();
        fragmentAdapter = new MyFragmentAdapter(fm);
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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem  = menu.add(0,0X111,0,"添加朋友");
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0x111:
                Toast.makeText(this,"添加朋友",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
