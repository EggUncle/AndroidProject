package uncle.egg.studysystem3.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import uncle.egg.studysystem3.R;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        setTitle("管理员设置");
    }
}
