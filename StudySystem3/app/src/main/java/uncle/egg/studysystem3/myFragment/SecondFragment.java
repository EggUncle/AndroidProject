package uncle.egg.studysystem3.myFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import uncle.egg.studysystem3.R;
import uncle.egg.studysystem3.activity.ErrorCollectionActivity;
import uncle.egg.studysystem3.activity.UnityTestActivity;

/**
 * Created by 西域战神阿凡提 on 2016/6/19.
 */
public class SecondFragment extends Fragment {
    private View view;
    private LinearLayout btnUnitTest;
    private LinearLayout btnCourseTest;
    private LinearLayout btnComplexTest;
    private LinearLayout btnErrorCollection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.second_layout, null);
        init();
        MyClickListener myClickListener = new MyClickListener();
        btnUnitTest.setOnClickListener(myClickListener);
        btnCourseTest.setOnClickListener(myClickListener);
        btnComplexTest.setOnClickListener(myClickListener);
        btnErrorCollection.setOnClickListener(myClickListener);
        return view;
    }

    //初始化
    public void init() {
        btnUnitTest = (LinearLayout) view.findViewById(R.id.unit_test);
        btnCourseTest = (LinearLayout) view.findViewById(R.id.course_test);
        btnComplexTest = (LinearLayout) view.findViewById(R.id.complex_test);
        btnErrorCollection = (LinearLayout) view.findViewById(R.id.error_collection);
        //    my_list = (ListView) view.findViewById(R.id.my_list);
        //    mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);

    }

    private class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.unit_test: {
                    Intent intent = new Intent(getContext(), UnityTestActivity.class);
                    startActivity(intent);
                }
                break;
                case R.id.course_test: {
                    Intent intent = new Intent(getContext(), UnityTestActivity.class);
                    startActivity(intent);
                }
                break;
                case R.id.complex_test: {
                    Intent intent = new Intent(getContext(), UnityTestActivity.class);
                    startActivity(intent);
                }
                break;
                case R.id.error_collection: {
                    Intent intent = new Intent(getContext(), ErrorCollectionActivity.class);
                    startActivity(intent);
                }
                break;
            }
        }
    }


}
