package com.example.administrator.finddoctor.MyFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by 西域战神阿凡提 on 2016/1/19.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    Fragment[] fragments = new Fragment[1];
    //    fragments = new Fragment[4];
    Context context;


    public MyFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        init();
    }

    private void init() {
        fragments[0] = new HospitalFragment(context);
   //     fragments[1] = new TypeFragment();
//        fragments[1] = new SecondFragment();
//        fragments[2] = new ThridFragment();
//        fragments[3] = new FourthFragment();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
