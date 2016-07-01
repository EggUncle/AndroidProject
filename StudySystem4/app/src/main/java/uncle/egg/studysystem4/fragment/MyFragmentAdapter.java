package uncle.egg.studysystem4.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by 西域战神阿凡提 on 2016/1/19.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    Fragment[] fragments = new Fragment[4];
    //    fragments = new Fragment[4];




    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
        init();
    }

    private void init() {
        fragments[0] = new FirstFragment();
        fragments[1] = new SecondFragment();
        fragments[2] = new CommunicateFragment();
        fragments[3] = new FourthFragment();
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
