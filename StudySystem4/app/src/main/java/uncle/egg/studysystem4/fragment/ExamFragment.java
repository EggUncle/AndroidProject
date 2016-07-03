package uncle.egg.studysystem4.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import uncle.egg.studysystem4.R;
import uncle.egg.studysystem4.activity.ExamActivity;
import uncle.egg.studysystem4.activity.UnityTestActivity;

/**
 * Created by egguncle on 16.7.2.
 */
public class ExamFragment extends Fragment {

    private View view;
    private Button btnStartExam;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText("first");
        view = inflater.inflate(R.layout.fragment_exam, null);

        init();
        btnStartExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ExamActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void init() {
        btnStartExam = (Button) view.findViewById(R.id.btn_start_btn);

    }
}
