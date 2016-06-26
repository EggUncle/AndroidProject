package com.example.administrator.smartcommunity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by egguncle on 16.4.24.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Map<String, Object>> listData;
    private Context context;

    public MyAdapter(Context context, List<Map<String, Object>> listData) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txt_sensor_name.setText(listData.get(position).get("sensor_name").toString());
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txt_sensor_name;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txt_sensor_name = (TextView) itemView.findViewById(R.id.sensor_name);

        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, SensorActivity.class);
            context.startActivity(intent);
        }
    }
}
