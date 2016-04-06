package com.example.administrator.new_start;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private LinearLayout line1;
    private LinearLayout line2;
    private LinearLayout line3;
    private ListView listView;

    List<Map<String, Object>> listData;
    private  MyAdapter myAdapter;
    private int[] imgs =  {
            R.drawable.mimg

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        line1 = (LinearLayout) findViewById(R.id.line1);
        line2 = (LinearLayout) findViewById(R.id.line2);
        line3 = (LinearLayout) findViewById(R.id.line3);
        listView = (ListView) findViewById(R.id.mylist);

        listData = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("first","西域战神阿凡提");
        listData.add(map);

        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
    }


    public void ImgClick(View v) {
        switch (v.getId()) {
            case R.id.img1:
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.GONE);
                line3.setVisibility(View.GONE);
                break;
            case R.id.img2:
                line1.setVisibility(View.GONE);
                line2.setVisibility(View.VISIBLE);
                line3.setVisibility(View.GONE);
                break;
            case R.id.img3:
                line1.setVisibility(View.GONE);
                line2.setVisibility(View.GONE);
                line3.setVisibility(View.VISIBLE);
                break;
        }
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.list_item, null);
                viewHolder.imageView = (CircleImageView) convertView.findViewById(R.id.list_img);
                viewHolder.textView1 = (TextView) convertView.findViewById(R.id.my_text1);
                viewHolder.textView2 = (TextView) convertView.findViewById(R.id.mt_text2);

                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView1.setText(listData.get(position).get("first").toString());
            viewHolder.imageView.setImageResource(imgs[position]);
            return convertView;
        }


        private class ViewHolder {
            CircleImageView imageView;
            TextView textView1;
            TextView textView2;
        }
    }

}
