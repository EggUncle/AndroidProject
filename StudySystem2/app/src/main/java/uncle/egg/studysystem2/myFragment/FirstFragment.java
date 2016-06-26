package uncle.egg.studysystem2.myFragment;




import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uncle.egg.studysystem2.R;

/**
 * Created by 西域战神阿凡提 on 2016/1/19.
 */
public class FirstFragment extends Fragment {


   private View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText("first");
        view = inflater.inflate(R.layout.firstlayout,null);
        init();


        return view;
    }

    private void init() {

    }





}
