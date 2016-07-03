package uncle.egg.studysystem4.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import uncle.egg.studysystem4.R;
import uncle.egg.studysystem4.activity.AdminActivity;


/**
 * Created by egguncle on 16.7.2.
 */
public class AdminFragment extends Fragment {
    private View view;

    private Button btnLogin;
    private MyClick myClick;
    private TextView txtAdminLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_login, null);
        init();
        return view;
    }

    private void init() {
        btnLogin = (Button) view.findViewById(R.id.btn_login);
        txtAdminLogin = (TextView) view.findViewById(R.id.txt_admin_login);
        myClick = new MyClick();
        btnLogin.setOnClickListener(myClick);
        txtAdminLogin.setOnClickListener(myClick);
    }

    private class MyClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_login: {
//                    Intent intent = new Intent(getActivity(), MainActivity.class);
//                    startActivity(intent);

                }
                break;
                case R.id.txt_admin_login: {
                    Intent intent = new Intent(getActivity(), AdminActivity.class);
                    startActivity(intent);

                }
            }
        }
    }
}
