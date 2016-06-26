package uncle.egg.studysystem2.myFragment;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import uncle.egg.studysystem2.R;
import uncle.egg.studysystem2.UnityTestActivity;

/**
 * Created by 西域战神阿凡提 on 2016/1/19.
 */
public class SecondFragment extends Fragment {
    private View view;
    private Button btnUnitTest;
    private Button btnCourseTest;
    private Button btnComplexTest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.second_layout, null);
        init();
        MyClickListener myClickListener = new MyClickListener();
        btnUnitTest.setOnClickListener(myClickListener);
        btnCourseTest.setOnClickListener(myClickListener);
        btnComplexTest.setOnClickListener(myClickListener);
        return view;
    }

    //初始化
    public void init() {
        btnUnitTest = (Button) view.findViewById(R.id.unit_test);
        btnCourseTest = (Button) view.findViewById(R.id.course_test);
        btnComplexTest = (Button) view.findViewById(R.id.comlpex_test);

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
                }
                break;
                case R.id.comlpex_test: {
                }
                break;
            }
        }
    }


}
