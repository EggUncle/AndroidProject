package com.example.administrator.myfragmenttest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
    Button btn_1;
    Button btn_2;
    Fragment[] fragments = new Fragment[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setMyListener();
    }

    private void init() {
        btn_1 = (Button) findViewById(R.id.one_btn);
        btn_2 = (Button) findViewById(R.id.two_btn);
        fragments[0] = getSupportFragmentManager().findFragmentById(R.id.first_fragment);
        fragments[1] = getSupportFragmentManager().findFragmentById(R.id.second_fragment);
        setMyFragment(0);
    }

    private void setMyListener() {
      btn_1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              setMyFragment(0);
          }
      });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMyFragment(1);
            }
        });

    }

    private void setMyFragment(int whichIsDefault) {
        getSupportFragmentManager().beginTransaction().hide(fragments[0]).hide(fragments[1]).show(fragments[whichIsDefault]).commit();
    }
}
