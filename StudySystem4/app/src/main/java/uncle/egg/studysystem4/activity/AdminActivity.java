package uncle.egg.studysystem4.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import uncle.egg.studysystem4.R;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        setTitle("管理员设置");
    }
}
