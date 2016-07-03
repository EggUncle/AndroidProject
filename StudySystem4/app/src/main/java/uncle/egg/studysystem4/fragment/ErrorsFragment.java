package uncle.egg.studysystem4.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uncle.egg.studysystem4.R;

/**
 * Created by egguncle on 16.7.2.
 */
public class ErrorsFragment extends Fragment {
    View view;
    private ListView lsError;


    private String[] strCourseNames = {"数据结构", "数据库", "移动网络", "软件工程", "软件测试", "计算机系统", "前端开发", "移动应用"};
    private List<Map<String, Object>> listData;
    private Map map;

  //  private MyListAdapter myListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText("first");
        view = inflater.inflate(R.layout.activity_unity_test, null);
        init();


        return view;
    }

    private void init() {
//        lsError = (ListView) view.findViewById(R.id.ls_error);
//        listData = new ArrayList<>();
//        myListAdapter = new MyListAdapter();
//
//        for (int i = 0; i < strCourseNames.length; i++) {
//            map = null;
//            map = new HashMap();
//            map.put("courseName", strCourseNames[i]);
//            listData.add(map);
//        }
//
//        lsError.setAdapter(myListAdapter);

    }

//    private class MyListAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            return listData.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder viewHolder;
//            if (convertView == null) {
//                viewHolder = new ViewHolder();
//                convertView = getActivity().getLayoutInflater().inflate(R.layout.error_list_item, null);
//                viewHolder.txtItemName = (TextView) convertView.findViewById(R.id.txt_item_name);
//                viewHolder.txtNumberError = (TextView) convertView.findViewById(R.id.txt_number_error);
//                convertView.setTag(viewHolder);
//            } else {
//                viewHolder = (ViewHolder) convertView.getTag();
//            }
//            viewHolder.txtItemName.setText(listData.get(position).get("courseName").toString());
//
//            return convertView;
//        }
//
//        private class ViewHolder {
//            TextView txtItemName;
//            TextView txtNumberError;
//        }
//    }
}
