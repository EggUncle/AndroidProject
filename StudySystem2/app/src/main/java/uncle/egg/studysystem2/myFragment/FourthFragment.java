package uncle.egg.studysystem2.myFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import uncle.egg.studysystem2.R;

/**
 * Created by 西域战神阿凡提 on 2016/1/19.
 */
public class FourthFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fourth_layout,null);


        return view;
    }
}