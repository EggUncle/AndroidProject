package uncle.egg.studysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class LoginActivity extends AppCompatActivity {

    @ViewInject(R.id.edit_password)
    EditText editPassword;
    @ViewInject(R.id.edit_username)
    EditText editUsername;
    @ViewInject(R.id.btn_login)
    Button btnLogin;
    @ViewInject(R.id.txt_need_registered)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewUtils.inject(this);
    }

    @OnClick({R.id.btn_login, R.id.txt_need_registered})
    public void myOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login: {
                Intent intent = new Intent(this,UserActivity.class);
                startActivity(intent);
                finish();
            }
            break;
            case R.id.txt_need_registered: {
                Intent intent = new Intent(this, RegisteredActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
}
