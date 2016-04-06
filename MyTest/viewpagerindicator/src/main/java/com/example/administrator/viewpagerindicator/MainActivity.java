package com.example.administrator.viewpagerindicator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private ViewPagerIndicator mViewPagerIndicator;

    private List<String> mTitles = Arrays.asList("短信", "收藏", "推荐");
    private List<VpSimpleFragment> mContents = new ArrayList<VpSimpleFragment>();
    private FragmentPagerAdapter fragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initDatas();

        mViewPager.setAdapter(fragmentPagerAdapter);
    }

    private void initViews() {

        mViewPager = (ViewPager) findViewById(R.id.my_viewpager);
        mViewPagerIndicator = (ViewPagerIndicator) findViewById(R.id.my_indicator);
    }

    private void initDatas() {
        for (String title : mTitles) {
            VpSimpleFragment fragment = VpSimpleFragment.newInstance(title);

            mContents.add(fragment);
        }


        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mContents.get(position);
            }

            @Override
            public int getCount() {
                return mContents.size();
            }
        };
    }
}
