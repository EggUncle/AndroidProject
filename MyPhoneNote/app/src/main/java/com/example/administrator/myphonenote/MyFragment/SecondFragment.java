package com.example.administrator.myphonenote.MyFragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myphonenote.R;
import com.example.administrator.myphonenote.SecondActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnChildClick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 西域战神阿凡提 on 2016/1/19.
 */
public class SecondFragment extends Fragment {

    ExpandableListView expandableListView;
    AutoCompleteTextView autoCompleteTextView;

    int i=0;//设置人数为10人

    int[] my_icon = {
            R.drawable.batmen, R.drawable.black, R.drawable.blue, R.drawable.cat, R.drawable.fast,
            R.drawable.fire, R.drawable.four, R.drawable.girl, R.drawable.gray, R.drawable.green
    };

    //  List<Map<String,Object>> list;   //放置姓名和id
    ArrayList<ArrayList<String>> list_datas;  //该名字下的号码
    ArrayList<String> list_name_data;  //姓名
    // MyBaseAdapter myBaseAdapter;
    ExpandableListAdapter expandableListAdapter;
    ArrayAdapter arrayAdapter;

  //  String[] books = new String[]{"android","java","c++" };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText("second");
        init();
        setListData();
        View view = inflater.inflate(R.layout.activity_main, null);
        expandableListView = (ExpandableListView) view.findViewById(R.id.my_expandable_list);
        autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.auto);
        arrayAdapter  = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,list_name_data.toArray());
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        expandableListAdapter = setExpandableListAdapter();
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //   startActivity(new Intent(MainActivity.this,SecondActivity.class));
                String my_tel = expandableListAdapter.getChild(groupPosition, childPosition).toString();
//                Uri uri = Uri.parse("tel:" + my_tel);
//                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra("tel", my_tel);
                startActivity(intent);
               getActivity().finish();
                return false;
            }
        });

        return view;
    }

    //初始化
    public void init() {
        list_name_data = new ArrayList<>();
        list_datas = new ArrayList<>();
        //   myBaseAdapter = new MyBaseAdapter();
    }

    public void setListData() {
        Map map = new HashMap();
        //实现姓名排序
        Cursor cursor = getActivity().getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null,ContactsContract.Contacts.DISPLAY_NAME);
        while (cursor.moveToNext()) {
            //id
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            // map.put("id", contactId);
            //姓名
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            // map.put("name",name);
            list_name_data.add(name);
            //电话号码
            Cursor phones_number = getActivity().getContentResolver().query(
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
            i++;
            if(i>10){
                break;
            }
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
                    convertView = getLayoutInflater(null).inflate(R.layout.list_item_layout, null);
                    viewHolder.name_text = (TextView) convertView.findViewById(R.id.name);
                    viewHolder.icon_img = (ImageView) convertView.findViewById(R.id.my_icon);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                viewHolder.name_text.setText(getGroup(groupPosition).toString());
                viewHolder.name_text.setPadding(50, 0, 0, 0);
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
                    convertView = getLayoutInflater(null).inflate(R.layout.list_child_item_layout, null);
                TextView textView = (TextView) convertView.findViewById(R.id.phone_number);
                textView.setText(getChild(groupPosition, childPosition).toString());
                textView.setPadding(30, 50, 0, 50);
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
