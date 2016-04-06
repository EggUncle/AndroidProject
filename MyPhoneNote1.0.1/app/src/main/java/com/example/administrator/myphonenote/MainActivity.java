package com.example.administrator.myphonenote;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnTouch;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //    @ViewInject(R.id.auto)
//    AutoCompleteTextView autoCompleteTextView;
//    @ViewInject(R.id.my_expandable_list)
//    ExpandableListView my_expandable_list;

//    @ViewInject(R.id.call_tel_btn)
//    Button call_tel_btn;
//
//    @ViewInject(R.id.send_message_btn)
//    Button send_message_btn;

    int[] my_icon = {
            R.drawable.batmen, R.drawable.black, R.drawable.blue, R.drawable.cat, R.drawable.fast,
            R.drawable.fire, R.drawable.four, R.drawable.girl, R.drawable.gray, R.drawable.green
    };

    //  List<Map<String,Object>> list;   //放置姓名和id
    ArrayList<ArrayList<String>> list_datas;  //姓名
    ArrayList<String> list_name_data;  //该名字下的号码
    // MyBaseAdapter myBaseAdapter;
    ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
    }
}
