package uncle.egg.studysystem4.model;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import uncle.egg.studysystem4.R;
import uncle.egg.studysystem4.activity.CourseListActivity;

/**
 * Created by egguncle on 16.6.16.
 */
public class MyButton extends LinearLayout {

    private Button btn;
    private TextView txt;
    private View view;


    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.btn_layout, this);
        btn = (Button) findViewById(R.id.btn);
        txt = (TextView) findViewById(R.id.txt);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CourseListActivity.class);
                getContext().startActivity(intent);
            }
        });
    }

    public void setMyButtonTxt(String str) {
        txt.setText(str);

        if ("更多".equals(str)) {
            btn.setText("...");
        } else {
            btn.setText(str.substring(0, 1).toString());
        }
    }
}
