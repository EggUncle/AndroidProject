package uncle.egg.studysystem4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import uncle.egg.studysystem4.R;
import uncle.egg.studysystem4.model.MyButton;

public class LearnActivity extends AppCompatActivity {

    private MyButton btnMy1, btnMy2, btnMy3, btnMy4, btnMy5, btnMy6, btnMy7, btnMy8, btnMy9;
    private MyButton[] btnMyButtons;

    private String[] strCouresNames = {"数据结构", "数据库", "移动网络", "软件工程", "软件测试", "计算机系统", "前端开发", "移动应用", "更多"};
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_layout);
        setTitle("学习篇");
        type = getIntent().getStringExtra("type");
        init();
    }

    private void init() {


        btnMy1 = (MyButton) findViewById(R.id.mybutton1);
        btnMy2 = (MyButton) findViewById(R.id.mybutton2);
        btnMy3 = (MyButton) findViewById(R.id.mybutton3);
        btnMy4 = (MyButton) findViewById(R.id.mybutton4);
        btnMy5 = (MyButton) findViewById(R.id.mybutton5);
        btnMy6 = (MyButton) findViewById(R.id.mybutton6);
        btnMy7 = (MyButton) findViewById(R.id.mybutton7);
        btnMy8 = (MyButton) findViewById(R.id.mybutton8);
        btnMy9 = (MyButton) findViewById(R.id.mybutton9);

        btnMyButtons = new MyButton[]{
                btnMy1, btnMy2, btnMy3, btnMy4, btnMy5, btnMy6, btnMy7, btnMy8, btnMy9
        };

        for (int i = 0; i < 9; i++) {
            btnMyButtons[i].setMyButtonTxt(strCouresNames[i]);

        }

        btnMy1.getBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ("video".equals(type)) {
                    Intent intent = new Intent(LearnActivity.this, VideoPlayerActivity.class);
                    intent.putExtra("type", type);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(LearnActivity.this, CourseListActivity.class);
                    intent.putExtra("type", type);
                    startActivity(intent);
                }
            }
        });

    }
}
