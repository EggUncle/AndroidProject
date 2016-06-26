package com.example.administrator.finddoctor.MyFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.finddoctor.R;

/**
 * Created by egguncle on 16.4.14.
 */
public class ImageFragmentAdapter extends FragmentPagerAdapter {

    Fragment[] img_fragment=new Fragment[3];

    public ImageFragmentAdapter(FragmentManager fm) {
        super(fm);
        img_fragment[0] = new ImageFragment(R.drawable.hospital1);
        img_fragment[1] = new ImageFragment(R.drawable.hospital2);
        img_fragment[2] =new ImageFragment(R.drawable.hospital3);
    }

    @Override
    public Fragment getItem(int position) {
        return img_fragment[position];
    }

    @Override
    public int getCount() {
        return img_fragment.length;
    }
}
