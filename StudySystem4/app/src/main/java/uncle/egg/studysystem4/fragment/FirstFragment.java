package uncle.egg.studysystem4.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uncle.egg.studysystem4.R;
import uncle.egg.studysystem4.model.MyButton;

/**
 * Created by 西域战神阿凡提 on 2016/6/19.
 */
public class FirstFragment extends Fragment {


    private View view;

    private MyButton btnMy1, btnMy2, btnMy3, btnMy4, btnMy5, btnMy6, btnMy7, btnMy8, btnMy9;

    private MyButton[] btnMyButtons;

    private String[] strCouresNames={"数据结构", "数据库", "移动网络", "软件工程", "软件测试", "计算机系统", "前端开发", "移动应用","更多"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText("first");
        view = inflater.inflate(R.layout.first_layout, null);
        init();


        return view;
    }

    private void init() {
        btnMy1 = (MyButton) view.findViewById(R.id.mybutton1);
        btnMy2 = (MyButton) view.findViewById(R.id.mybutton2);
        btnMy3 = (MyButton) view.findViewById(R.id.mybutton3);
        btnMy4 = (MyButton) view.findViewById(R.id.mybutton4);
        btnMy5 = (MyButton) view.findViewById(R.id.mybutton5);
        btnMy6 = (MyButton) view.findViewById(R.id.mybutton6);
        btnMy7 = (MyButton) view.findViewById(R.id.mybutton7);
        btnMy8 = (MyButton) view.findViewById(R.id.mybutton8);
        btnMy9 = (MyButton) view.findViewById(R.id.mybutton9);

        btnMyButtons = new MyButton[] {
                btnMy1, btnMy2, btnMy3, btnMy4, btnMy5, btnMy6, btnMy7, btnMy8, btnMy9
        };

        for(int i=0 ; i<9;i++){
           btnMyButtons[i].setMyButtonTxt(strCouresNames[i]);

        }


    }


}
