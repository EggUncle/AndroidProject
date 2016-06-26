package com.example.administrator.smartcommunity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by egguncle on 16.4.29.
 */
public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {

    private List<Map<String, Object>> listData;
    private Context context;


    public CommunityAdapter(Context context, List<Map<String, Object>> listData) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_community_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txt_community.setText(listData.get(position).get("txt_community").toString());
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_community;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_community = (TextView) itemView.findViewById(R.id.txt_community);
        }
    }
}
