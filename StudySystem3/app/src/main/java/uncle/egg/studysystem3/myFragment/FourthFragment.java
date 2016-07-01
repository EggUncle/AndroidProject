package uncle.egg.studysystem3.myFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import uncle.egg.studysystem3.R;
import uncle.egg.studysystem3.activity.LoginActivity;

/**
 * Created by 西域战神阿凡提 on 2016/1/19.
 */
public class FourthFragment extends Fragment {

    private View view;
    private LinearLayout lineLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fourth_layout, null);
        MyClick myClick = new MyClick();
        init();
        lineLogin.setOnClickListener(myClick);

        return view;
    }

    private void init() {
        lineLogin = (LinearLayout) view.findViewById(R.id.line_login);
    }

    private class MyClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.line_login: {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            }
        }
    }
}
