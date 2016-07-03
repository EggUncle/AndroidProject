package uncle.egg.studysystem4.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import uncle.egg.studysystem4.R;
import uncle.egg.studysystem4.activity.LearnActivity;
import uncle.egg.studysystem4.model.MyButton;

/**
 * Created by 西域战神阿凡提 on 2016/6/19.
 */
public class StudyFragment extends Fragment {


    private View view;



    private Button btnLearnCourse;
    private Button btnLearnPPT;
    private Button btnLearnVideo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText("first");
        view = inflater.inflate(R.layout.study2_layout, null);
        init();

        MyClick myClick = new MyClick();
        btnLearnCourse.setOnClickListener(myClick);
        btnLearnPPT.setOnClickListener(myClick);
        btnLearnVideo.setOnClickListener(myClick);

        return view;
    }

    private void init() {

        btnLearnCourse = (Button) view.findViewById(R.id.btn_learn_course);
        btnLearnPPT = (Button) view.findViewById(R.id.btn_learn_ppt);
        btnLearnVideo = (Button) view.findViewById(R.id.btn_learn_video);

//        btnMy1 = (MyButton) view.findViewById(R.id.mybutton1);
//        btnMy2 = (MyButton) view.findViewById(R.id.mybutton2);
//        btnMy3 = (MyButton) view.findViewById(R.id.mybutton3);
//        btnMy4 = (MyButton) view.findViewById(R.id.mybutton4);
//        btnMy5 = (MyButton) view.findViewById(R.id.mybutton5);
//        btnMy6 = (MyButton) view.findViewById(R.id.mybutton6);
//        btnMy7 = (MyButton) view.findViewById(R.id.mybutton7);
//        btnMy8 = (MyButton) view.findViewById(R.id.mybutton8);
//        btnMy9 = (MyButton) view.findViewById(R.id.mybutton9);
//
//        btnMyButtons = new MyButton[] {
//                btnMy1, btnMy2, btnMy3, btnMy4, btnMy5, btnMy6, btnMy7, btnMy8, btnMy9
//        };
//
//        for(int i=0 ; i<9;i++){
//           btnMyButtons[i].setMyButtonTxt(strCouresNames[i]);
//
//        }


    }


    private class MyClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_learn_course: {
                }
                break;
                case R.id.btn_learn_ppt: {
                }
                break;
                case R.id.btn_learn_video: {
                }
                break;
            }

            Intent intent = new Intent(getActivity(), LearnActivity.class);
            startActivity(intent);
        }
    }


}
