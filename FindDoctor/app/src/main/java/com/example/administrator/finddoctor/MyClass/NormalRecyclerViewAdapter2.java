package com.example.administrator.finddoctor.MyClass;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.finddoctor.DoctorActivity;
import com.example.administrator.finddoctor.HospitalActivity;
import com.example.administrator.finddoctor.R;

import java.util.List;
import java.util.Map;

/**
 * Created by egguncle on 16.4.23.
 */
public class NormalRecyclerViewAdapter2 extends RecyclerView.Adapter<NormalRecyclerViewAdapter2.ViewHolder> {

    private static Context context;
    private final LayoutInflater mLayoutInflater;
    private List<Map<String, Object>> mListData;

    public NormalRecyclerViewAdapter2(Context context, List<Map<String, Object>> mListData) {
        this.context = context;
        this.mListData = mListData;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //   View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(mLayoutInflater.inflate(R.layout.hospital_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mListData.get(position).get("hospital_name").toString());
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

//    @Override
//    public int getItemViewType(int position) {
//
//        if(position==0){
//
//            return -1;
//        }else{
//        return super.getItemViewType(position);}
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTextView;
        ImageView icon_doctor;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.hospital_name);
            icon_doctor = (ImageView) itemView.findViewById(R.id.hospital_icon);
            icon_doctor.setImageResource(R.drawable.user);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        //    Toast.makeText(context, getPosition() + "", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, DoctorActivity.class);
            context.startActivity(intent);
        }
    }
}

