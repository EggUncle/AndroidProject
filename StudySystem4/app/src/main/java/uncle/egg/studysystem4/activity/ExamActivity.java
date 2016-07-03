package uncle.egg.studysystem4.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import uncle.egg.studysystem4.R;

public class ExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        setTitle("模拟考试");
    }
}
