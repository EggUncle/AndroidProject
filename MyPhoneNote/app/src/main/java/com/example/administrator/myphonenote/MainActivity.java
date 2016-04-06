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
    @ViewInject(R.id.my_expandable_list)
    ExpandableListView my_expandable_list;

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
        init();
        setListData();
        expandableListAdapter = setExpandableListAdapter();
        my_expandable_list.setAdapter(expandableListAdapter);
        my_expandable_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //   startActivity(new Intent(MainActivity.this,SecondActivity.class));
                String my_tel = expandableListAdapter.getChild(groupPosition, childPosition).toString();
//                Uri uri = Uri.parse("tel:" + my_tel);
//                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("tel", my_tel);
                startActivity(intent);
                return false;
            }
        });

    }



    //初始化
    public void init() {
        list_name_data = new ArrayList<>();
        list_datas = new ArrayList<>();
        //   myBaseAdapter = new MyBaseAdapter();
    }

    public void setListData() {
        Map map = new HashMap();
        Cursor cursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            //id
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            // map.put("id", contactId);
            //姓名
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            // map.put("name",name);
            list_name_data.add(name);
            //电话号码
            Cursor phones_number = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                    , null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);
            //获取一个联系人的多个号码
            ArrayList<String> list_child_data = new ArrayList<>();
            while (phones_number.moveToNext()) {
                String number = phones_number.getString(
                        phones_number.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                list_child_data.add(number);
            }
            list_datas.add(list_child_data);
            phones_number.close();
        }
        cursor.close();
    }

    public ExpandableListAdapter setExpandableListAdapter() {
        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {

            @Override
            public int getGroupCount() {
                return list_name_data.size();
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return list_datas.get(groupPosition).size();
            }

            @Override
            public Object getGroup(int groupPosition) {
                return list_name_data.get(groupPosition);
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return list_datas.get(groupPosition).get(childPosition);
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                ViewHolder viewHolder;
                if (convertView == null) {
                    viewHolder = new ViewHolder();
                    convertView = getLayoutInflater().inflate(R.layout.list_item_layout, null);
                    viewHolder.name_text = (TextView) convertView.findViewById(R.id.name);
                    viewHolder.icon_img = (ImageView) convertView.findViewById(R.id.my_icon);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                //    convertView.setTag(name_text);
                //     LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.list_item_layout, null);
                //          TextView textView = (TextView) linearLayout.findViewById(R.id.name);
//                TextView textView =  new TextView(MainActivity.this);
                viewHolder.name_text.setText(getGroup(groupPosition).toString());
                viewHolder.name_text.setPadding(50, 0, 0, 0);
//                viewHolder.icon_img.setMaxHeight(10);
//                viewHolder.icon_img.setMaxWidth(10);
                viewHolder.icon_img.setImageResource(my_icon[groupPosition % my_icon.length]);

                //   linearLayout.setTag(textView);
                return convertView;
            }

            class ViewHolder {
                TextView name_text;
                ImageView icon_img;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                if (convertView == null)
                    convertView = getLayoutInflater().inflate(R.layout.list_child_item_layout, null);
                TextView textView = (TextView) convertView.findViewById(R.id.phone_number);
                //     convertView.setTag(name_text);
                //     LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.list_child_item_layout, null);
                //       TextView textView = (TextView) linearLayout.findViewById(R.id.phone_number);
//                TextView textView =  new TextView(MainActivity.this);
                textView.setText(getChild(groupPosition, childPosition).toString());
                textView.setPadding(30, 50, 0, 50);
                //     linearLayout.setTag(textView);
                return convertView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };
        return adapter;
    }

}
