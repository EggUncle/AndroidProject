package com.example.administrator.myphonenote.MyFragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
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

    ListView my_list;
    ProgressBar mProgressBar;
    View view;

    AutoCompleteTextView autoCompleteTextView;



    int[] my_icon = {
            R.drawable.batmen, R.drawable.black, R.drawable.blue, R.drawable.cat, R.drawable.fast,
            R.drawable.fire, R.drawable.four, R.drawable.girl, R.drawable.gray, R.drawable.green
    };

    //  List<Map<String,Object>> list;   //放置姓名和id
    ArrayList<ArrayList<String>> list_datas;  //该名字下的号码
    ArrayList<String> list_name_data;  //姓名
    ArrayAdapter arrayAdapter;
    MyListAdapter myListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_main, null);
        init();
     //   setListData();
       // expandableListView = (ExpandableListView) view.findViewById(R.id.my_expandable_list);
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
        my_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                Bundle data = new Bundle();
                data.putSerializable("phone_number", list_datas.get(position));
                intent.putExtras(data);
                intent.putExtra("name", list_name_data.get(position).toString());

                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });

        MyAyncTask myAyncTask = new MyAyncTask();
        myAyncTask.execute();

        return view;
    }

    //初始化
    public void init() {
        list_name_data = new ArrayList<>();
        list_datas = new ArrayList<>();
        myListAdapter  = new MyListAdapter();
        my_list = (ListView) view.findViewById(R.id.my_list);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
    }

    public ArrayList<String> setListData() {

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
        }
        cursor.close();
        return list_name_data;
    }
    //使用异步加载来加载联系人列表
    //第一个参数：启动任务时输入的参数类型.
    //第二个参数：后台任务执行中返回进度值的类型.
    //第三个参数：后台任务执行完成后返回结果的类型.
    class MyAyncTask extends AsyncTask<Void,Integer,ArrayList<String>>{

        ArrayList<String> list_name_data;

        public MyAyncTask() {
            super();
        }


        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            //执行后返回值
            super.onPostExecute(strings);
            mProgressBar.setVisibility(View.GONE);
            my_list.setAdapter(myListAdapter);
        }

        @Override
        protected void onPreExecute() {
            //执行前的初始化操作
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //更新时调用的操作
           list_name_data= setListData();
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            //后台加载时的操作
            setListData();
            return null;
        }
    }

    private class MyListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list_name_data.size();
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
                    convertView = getLayoutInflater(null).inflate(R.layout.list_item_layout, null);
                    viewHolder.name_text = (TextView) convertView.findViewById(R.id.name);
              //      viewHolder.icon_img = (ImageView) convertView.findViewById(R.id.my_icon);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                viewHolder.name_text.setText(list_name_data.get(position).toString());
                viewHolder.name_text.setPadding(50, 0, 0, 0);
           //     viewHolder.icon_img.setImageResource(my_icon[position % my_icon.length]);

                //   linearLayout.setTag(textView);
                return convertView;
            }

            class ViewHolder {
                TextView name_text;
              //  ImageView icon_img;
            }
    }



}
