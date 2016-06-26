package com.example.administrator.finddoctor.MyFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by egguncle on 16.4.22.
 */
public class FragmentInHosAdapter extends FragmentPagerAdapter {
    Fragment[] fragments = new Fragment[2];
    //    fragments = new Fragment[4];
    Context context;


    public FragmentInHosAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        init();
    }

    private void init() {
  //      fragments[0] = new DoctorListFragment(context);
        fragments[0] = new DoctorListFragment(context);
        fragments[1] = new TypeListFragment(context);
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