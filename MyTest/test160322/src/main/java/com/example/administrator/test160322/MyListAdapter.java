package com.example.administrator.test160322;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by egguncle on 16.3.26.
 */
public class MyListAdapter extends BaseAdapter {
    List<Map<String, Object>> listdata;
    Context context;

    public void setListdata(Context context, List<Map<String, Object>> listdata) {
        this.listdata = listdata;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listdata.size();
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
            convertView = View.inflate(context, R.layout.list_item_layout, null);
            viewHolder.txt_date = (TextView) convertView.findViewById(R.id.txt_date);
            viewHolder.txt_weather = (TextView) convertView.findViewById(R.id.txt_weather);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txt_date.setText(listdata.get(position).get("date").toString());
        viewHolder.txt_weather.setText(listdata.get(position).get("weather").toString());

        return convertView;
    }

    private class ViewHolder {
        TextView txt_date;
        TextView txt_weather;
    }
}
