package uncle.egg.studysystem;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class UnitTestActivity extends AppCompatActivity {

    @ViewInject(R.id.list_expand)
    ExpandableListView listExpand;

    MyExpandAdapter myExpandAdapter;

    String[] courseName = {"C++", "JAVA", "SQL"};
    String[][] course = {{"基本语法", "函数", "指针"},
            {"基本语法", "接口", "方法"},
            {"SQL", "查找", "更新"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_test);
        setTitle("章节练习");
        ViewUtils.inject(this);

        myExpandAdapter = new MyExpandAdapter();
        listExpand.setAdapter(myExpandAdapter);

        listExpand.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                return false;
            }
        });
    }

    private class MyExpandAdapter implements ExpandableListAdapter {

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public int getGroupCount() {
            return courseName.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return course[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return courseName[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return course[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupViewHolder groupViewHolder = null;
            if (convertView == null) {
                groupViewHolder = new GroupViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.grouplayout, null);
                groupViewHolder.textView = (TextView) convertView.findViewById(R.id.text_group);
                convertView.setTag(groupViewHolder);
            } else {
                groupViewHolder = (GroupViewHolder) convertView.getTag();
            }
            groupViewHolder.textView.setText(courseName[groupPosition]);

            return convertView;
        }

        private class GroupViewHolder {
            TextView textView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildViewHolder childViewHolder;
            if (convertView == null) {
                childViewHolder = new ChildViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.childlayout, null);
                childViewHolder.textView = (TextView) convertView.findViewById(R.id.text_child);
                convertView.setTag(childViewHolder);
            } else {
                childViewHolder = (ChildViewHolder) convertView.getTag();
            }
            childViewHolder.textView.setText(course[groupPosition][childPosition]);
            return convertView;
        }

        private class ChildViewHolder {
            TextView textView;
        }


        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public void onGroupExpanded(int groupPosition) {

        }

        @Override
        public void onGroupCollapsed(int groupPosition) {

        }

        @Override
        public long getCombinedChildId(long groupId, long childId) {
            return 0;
        }

        @Override
        public long getCombinedGroupId(long groupId) {
            return 0;
        }
    }
}
