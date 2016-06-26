package com.example.administrator.mymusicplayer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by egguncle on 16.3.26.
 */
public class MyListAdapter extends BaseAdapter {
    List<HashMap<String, String>> listdata;
    Context context;

    public void setListdata(Context context, List<HashMap<String, String>> listdata) {
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
            convertView = View.inflate(context, R.layout.layout_list_item, null);
            viewHolder.txt_singer_name = (TextView) convertView.findViewById(R.id.txt_singer_name);
            viewHolder.txt_songs_name = (TextView) convertView.findViewById(R.id.txt_songs_name);
            viewHolder.txt_songs_time = (TextView) convertView.findViewById(R.id.txt_songs_time);
          //  viewHolder.icon_song = (ImageView) convertView.findViewById(R.id.icon_songs);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txt_singer_name.setText(listdata.get(position).get("title").toString());
        viewHolder.txt_songs_name.setText(listdata.get(position).get("Artist").toString());
        viewHolder.txt_songs_time.setText(listdata.get(position).get("duration").toString());
//        viewHolder.icon_song.setImageBitmap(MediaUtil.getAlbumImage(
//                Integer.parseInt(listdata.get(position).get("albumId")), context));


        return convertView;
    }

    private class ViewHolder {
       // ImageView icon_song;
        TextView txt_singer_name;
        TextView txt_songs_name;
        TextView txt_songs_time;
    }


}
