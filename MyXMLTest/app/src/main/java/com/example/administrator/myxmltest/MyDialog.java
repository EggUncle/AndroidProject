package com.example.administrator.myxmltest;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * Created by egguncle on 16.3.13.
 */
public class MyDialog extends Dialog {

    TableLayout mydialog;
    TextView txt_ask_leave_time;
    TextView txt_late_time;
    TextView txt_not_arrive_time;
    int ask_leave ;
    int not_arrive;
    int late ;

    public MyDialog(Context context) {
        super(context);
        mydialog = (TableLayout) getLayoutInflater().inflate(R.layout.dialog_layout, null);
        init();
        setContentView(mydialog);
    }

    private void init() {
        Button btn_ask_leave_cut_back = (Button) mydialog.findViewById(R.id.ask_leave_cut_back);
        Button btn_ask_leave_increase = (Button) mydialog.findViewById(R.id.ask_leave_increase);
        Button btn_not_arrive_cut_down = (Button) mydialog.findViewById(R.id.not_arrive_cut_down);
        Button btn_not_arrive_increase = (Button) mydialog.findViewById(R.id.not_arrive_increase);
        Button btn_late_cut_down = (Button) mydialog.findViewById(R.id.late_cut_down);
        Button btn_late_increase = (Button) mydialog.findViewById(R.id.late_increase);
        txt_ask_leave_time = (TextView) mydialog.findViewById(R.id.ask_leave_time);
        txt_late_time = (TextView) mydialog.findViewById(R.id.late_time);
        txt_not_arrive_time = (TextView) mydialog.findViewById(R.id.not_arrive_time);
        btn_ask_leave_cut_back.setOnClickListener(new MyClickListener());
        btn_ask_leave_increase.setOnClickListener(new MyClickListener());
        btn_not_arrive_cut_down.setOnClickListener(new MyClickListener());
        btn_not_arrive_increase.setOnClickListener(new MyClickListener());
        btn_late_cut_down.setOnClickListener(new MyClickListener());
        btn_late_increase.setOnClickListener(new MyClickListener());
        ask_leave = 0;
        not_arrive = 0;
        late = 0;
    }

    private class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.v("MY_TAG", "123");

            switch (v.getId()) {
                case R.id.ask_leave_cut_back:
                    txt_ask_leave_time.setText((--ask_leave)+"");
                    break;
                case R.id.ask_leave_increase:
                    txt_ask_leave_time.setText((++ask_leave)+"");
                    break;
                case R.id.not_arrive_cut_down:
                    txt_not_arrive_time.setText((--not_arrive)+"");
                    break;
                case R.id.not_arrive_increase:
                    txt_not_arrive_time.setText(++not_arrive+"");
                    break;
                case R.id.late_cut_down:
                    txt_late_time.setText(--late+"");
                    break;
                case R.id.late_increase:
                    txt_late_time.setText(++late+"");
                    break;

            }
        }
    }
}
