package uncle.egg.studysystem4.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import uncle.egg.studysystem4.R;

public class ExamActivity extends AppCompatActivity {

    private TextView txtNowPoint;
    private TextView txtQuestion;
    private RadioGroup rgAbcd;
    private RadioButton btnA;
    private RadioButton btnB;
    private RadioButton btnC;
    private RadioButton btnD;
    private Button btnPrevious;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        setTitle("模拟考试");
        txtNowPoint = (TextView) findViewById(R.id.txt_now_point);
        txtQuestion = (TextView) findViewById(R.id.txt_question);
        rgAbcd = (RadioGroup) findViewById(R.id.rg_abcd);
        btnA = (RadioButton) findViewById(R.id.btn_a);
        btnB = (RadioButton) findViewById(R.id.btn_b);
        btnC = (RadioButton) findViewById(R.id.btn_c);
        btnD = (RadioButton) findViewById(R.id.btn_d);
        btnPrevious = (Button) findViewById(R.id.btn_previous);
        btnNext = (Button) findViewById(R.id.btn_next);
        setTitle("练习");

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExamActivity.this,ExamActivity.class));
                finish();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExamActivity.this,ExamActivity.class));
                finish();
            }
        });
    }
}