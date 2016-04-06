package com.example.administrator.myinternettalktest.MyFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myinternettalktest.MyActivity.TalkActivity;
import com.example.administrator.myinternettalktest.R;

/**
 * Created by shadow on 16.2.13.
 */
public class SecondFragment extends Fragment {
    View secondfragmentLayout;
    ExpandableListView expandableListView;
    MyExpandableListViewAdapter myExpandableListViewAdapter;

    private String[] groups = new String[]{"1", "2", "3"};
    private String[][] childs = new String[][]{
            {"1.1", "1.2", "1.3"},
            {"2.1", "2.2", "2.3"},
            {"3.1", "3.2", "3.3"}};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        secondfragmentLayout = getActivity().getLayoutInflater().inflate(R.layout.fragment_second, null);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //firstFragmentLayout = inflater.inflate(R.layout.fragment_first, null);
        return secondfragmentLayout;
    }

    private void init() {
        expandableListView = (ExpandableListView) secondfragmentLayout.findViewById(R.id.expand_listview);
        myExpandableListViewAdapter = new MyExpandableListViewAdapter();
        expandableListView.setAdapter(myExpandableListViewAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String name = childs[groupPosition][childPosition];
                Intent intent = new Intent(getActivity(), TalkActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
                return true;
            }
        });
    }

    private class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return childs[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childs[groupPosition][childPosition];
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
            TextView textView;
            LinearLayout ll;
            ll = new LinearLayout(getActivity());
            textView = new TextView(getActivity());
            textView.setText(getGroup(groupPosition).toString());
            textView.setTextSize(30);
            textView.setPadding(100, 0, 0, 0);
            ll.addView(textView);
            return ll;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ChildViewHolder();
                convertView = getActivity().getLayoutInflater().inflate(R.layout.layout_child, null);
                viewHolder.icon = (ImageView) convertView.findViewById(R.id.person_icon);
                viewHolder.name = (TextView) convertView.findViewById(R.id.person_name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ChildViewHolder) convertView.getTag();
            }
            //设置头像
            //
            //设置文字
            viewHolder.name.setText(getChild(groupPosition, childPosition).toString());
            return convertView;
        }

        private class ChildViewHolder {
            ImageView icon;
            TextView name;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
