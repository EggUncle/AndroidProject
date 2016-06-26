package com.example.administrator.finddoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;

import com.example.administrator.finddoctor.MyClass.NormalRecyclerViewAdapter;
import com.example.administrator.finddoctor.MyClass.NormalRecyclerViewAdapter2;
import com.example.administrator.finddoctor.MyClass.XCRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeActivity extends AppCompatActivity {

    private XCRecyclerView xcRecyclerView;
    private NormalRecyclerViewAdapter2 mAdapter;
    List<Map<String,Object>> mListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        getSupportActionBar().hide();
        mListData = new ArrayList<>();
        Map map;
        for(int i=0; i<5;i++){
            map = new HashMap();
            map.put("hospital_name","医生 "+i);
            mListData.add(map);
        }
        View head_layout = View.inflate(this,R.layout.type_head_layout,null);
        ImageButton btn_type_back = (ImageButton) head_layout.findViewById(R.id.btn_type_back);
        xcRecyclerView = (XCRecyclerView)findViewById(R.id.recycler_view_type);

        //recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mAdapter = new NormalRecyclerViewAdapter2(this,mListData);
        xcRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //
        xcRecyclerView.addHeaderView(head_layout);
        xcRecyclerView.setAdapter(mAdapter);

        btn_type_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =  new Intent(TypeActivity.this,HospitalActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
