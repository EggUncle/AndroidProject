package uncle.egg.studysystem4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import uncle.egg.studysystem4.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private MyClick myClick;
    private TextView txtAdminLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        btnLogin = (Button) findViewById(R.id.btn_login);
        txtAdminLogin = (TextView) findViewById(R.id.txt_admin_login);
        myClick = new MyClick();
        btnLogin.setOnClickListener(myClick);
        txtAdminLogin.setOnClickListener(myClick);
    }

    private class MyClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_login: {
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
                case R.id.txt_admin_login:{
                    Intent intent = new Intent(LoginActivity.this,AdminActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }
}