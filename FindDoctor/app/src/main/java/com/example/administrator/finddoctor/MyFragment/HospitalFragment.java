package com.example.administrator.finddoctor.MyFragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.finddoctor.HospitalActivity;
import com.example.administrator.finddoctor.MyClass.NormalRecyclerViewAdapter;
import com.example.administrator.finddoctor.MyClass.XCRecyclerView;
import com.example.administrator.finddoctor.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HospitalFragment extends Fragment {

    private RecyclerView recyclerView;
    private XCRecyclerView xcRecyclerView;
    private NormalRecyclerViewAdapter mAdapter;
    List<Map<String,Object>>  mListData;

    View view;
    Context context;

    ViewPager img_viewpager;
    ImageFragment imageFragment;
    ImageFragmentAdapter imageFragmentAdapter;

    public  HospitalFragment(){
    }

    public  HospitalFragment(Context context){
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hospital, null);
        init();
        return view;
    }

    private void init() {
        View head_layout = View.inflate(getActivity(),R.layout.head_layout,null);
        img_viewpager = (ViewPager) head_layout.findViewById(R.id.img_viewpager);
        FragmentManager fm = getChildFragmentManager();
        imageFragmentAdapter = new ImageFragmentAdapter(fm);
        img_viewpager.setAdapter(imageFragmentAdapter);

        mListData = new ArrayList<>();
        Map map;
        for(int i=0; i<5;i++){
            map = new HashMap();
            map.put("hospital_name","医院 "+i);
            mListData.add(map);
        }

        xcRecyclerView = (XCRecyclerView) view.findViewById(R.id.recycler_view);

        //recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mAdapter = new NormalRecyclerViewAdapter(getActivity(),mListData);
        xcRecyclerView.setLayoutManager(new LinearLayoutManager(context));
       //
        xcRecyclerView.addHeaderView(head_layout);
        xcRecyclerView.setAdapter(mAdapter);
//        recyclerView.addView(img_viewpager);

    }

}
