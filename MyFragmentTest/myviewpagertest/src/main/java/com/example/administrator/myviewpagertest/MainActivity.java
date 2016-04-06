package com.example.administrator.myviewpagertest;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager pager;
    MyAdapter adapter;
    int imgID[]= {
            R.drawable.batmen, R.drawable.black, R.drawable.blue, R.drawable.cat, R.drawable.fast,
            R.drawable.fire, R.drawable.four, R.drawable.girl, R.drawable.gray, R.drawable.green
    };
    ImageView imgs[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.my_viewPager);
        adapter = new MyAdapter();
        //创建imageview数组
        imgs = new ImageView[imgID.length];
        for (int i = 0;i < imgID.length;i++){
            ImageView img = new ImageView(MainActivity.this);
            img.setImageResource(imgID[i]);
            imgs[i] = img;
            pager.setAdapter(adapter);
        }


    }
    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager)container).addView(imgs[position % imgID.length], 0);
            return imgs[position % imgID.length];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(imgs[position % imgID.length]);

        }
    }

}
