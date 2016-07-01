package uncle.egg.studysystem3.myFragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uncle.egg.studysystem3.R;

/**
 * Created by 西域战神阿凡提 on 2016/1/19.
 */
public class ThridFragment extends Fragment {

    private ListView lsCommunicate;
    private View view;

    private String[] strCourseNames = {"数据结构", "数据库", "移动网络", "软件工程", "软件测试", "计算机系统", "前端开发", "移动应用"};
    private String[] strName = {"明同学", "张同学", "刘同学", "周同学", "李同学"};
    private String[] strTime = {"10:10", "11:12", "11:30", "11:50", "12:16"};
    private String[] strCommunicate = {"交流分享内容1", "交流分享内容2", "交流分享内容3", "交流分享内容4", "交流分享内容5"};
    private int[] imgIcon = {R.drawable.batmen, R.drawable.black, R.drawable.blue, R.drawable.cat, R.drawable.double_girl};
    private List<Map<String, Object>> listData;
    private Map map;

    private MyListAdapter myListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.thrid_layout, null);
        init();
        return view;
    }

    private void init() {
        lsCommunicate = (ListView) view.findViewById(R.id.ls_communicate);
        listData = new ArrayList<>();
        myListAdapter = new MyListAdapter();

        for (int i = 0; i < strName.length; i++) {
            map = null;
            map = new HashMap();
            map.put("strName", strName[i]);
            map.put("strTime", strTime[i]);
            map.put("strCommunicate", strCommunicate[i]);
            map.put("imgIcon", imgIcon[i]);
            listData.add(map);
        }

        lsCommunicate.setAdapter(myListAdapter);

    }


    private class MyListAdapter extends BaseAdapter {

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
                convertView = getActivity().getLayoutInflater().inflate(R.layout.item_list_communicate, null);
                viewHolder.imgIcon = (ImageView) convertView.findViewById(R.id.img_icon);
                viewHolder.txtOtherName = (TextView) convertView.findViewById(R.id.txt_other_name);
                viewHolder.txtOtherTime = (TextView) convertView.findViewById(R.id.txt_other_time);
                viewHolder.txtOtherCommunicate = (TextView) convertView.findViewById(R.id.txt_other_communicate);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.imgIcon.setImageResource((Integer) listData.get(position).get("imgIcon"));
            viewHolder.txtOtherCommunicate.setText(listData.get(position).get("strCommunicate").toString());
            viewHolder.txtOtherName.setText(listData.get(position).get("strName").toString());
            viewHolder.txtOtherTime.setText(listData.get(position).get("strTime").toString());

            return convertView;
        }

        private class ViewHolder {
            ImageView imgIcon;
            TextView txtOtherName;
            TextView txtOtherTime;
            TextView txtOtherCommunicate;
        }
    }

}
