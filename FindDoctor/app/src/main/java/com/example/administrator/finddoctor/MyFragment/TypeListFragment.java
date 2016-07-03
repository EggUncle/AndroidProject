package com.example.administrator.finddoctor.MyFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.administrator.finddoctor.R;
import com.example.administrator.finddoctor.TypeActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by egguncle on 16.4.22.
 */
public class TypeListFragment extends Fragment {

    View view;
    ListView list_type;
    Context context;
    List<Map<String, Object>> listData;
    MyDoctorListAdapter myDoctorListAdapter;


    public  TypeListFragment(){
    }

    public  TypeListFragment(Context context){
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.doctor_list_fragment, null);
        init();
        return view;
    }

    private void init(){
        list_type = (ListView) view.findViewById(R.id.list_doctor);
        listData = new ArrayList<>();
        myDoctorListAdapter = new MyDoctorListAdapter();
        Map map;
        for (int i = 0; i < 5; i++) {
            map = new HashMap();
            map.put("type_name", "科室 " + i);
            listData.add(map);
        }
        list_type.setAdapter(myDoctorListAdapter);
        list_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, TypeActivity.class);
                startActivity(intent);
            }
        });
    }

    private class MyDoctorListAdapter extends BaseAdapter {

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
                convertView = View.inflate(context, R.layout.doctor_list_item, null);
                viewHolder.icon_docter = (ImageView) convertView.findViewById(R.id.icon_docter);
                viewHolder.docter_name = (TextView) convertView.findViewById(R.id.doctor_name);
                viewHolder.type_name = (TextView) convertView.findViewById(R.id.type_name);
                viewHolder.ratingBar = (RatingBar) convertView.findViewById(R.id.rat_bar_doctor);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.docter_name.setText(listData.get(position).get("type_name").toString());
            viewHolder.icon_docter.setImageResource(R.drawable.type);
            // viewHolder.docter_name.setText("123");
            return convertView;
        }

        private class ViewHolder {
            ImageView icon_docter;
            TextView docter_name;
            TextView type_name;
            RatingBar ratingBar;
        }
    }
}