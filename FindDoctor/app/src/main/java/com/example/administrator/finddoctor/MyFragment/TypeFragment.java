package com.example.administrator.finddoctor.MyFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.finddoctor.R;

/**
 * Created by egguncle on 16.4.9.
 */
public class TypeFragment extends Fragment {

    View view;
    Button btn_test;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.type_layout, null);
        init();
        return view;
    }

    private void init(){
        btn_test = (Button) view.findViewById(R.id.btn_test);


    }
}
