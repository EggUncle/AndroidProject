package com.example.administrator.viewpagerindicator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by egguncle on 16.3.16.
 */
public class VpSimpleFragment extends Fragment {
    private String mTitle;
    public static final String BUNDLE_TITLE = "title";
    static int i=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mTitle = bundle.getString(BUNDLE_TITLE);
        }
        TextView tv = new TextView(getActivity());
        tv.setText(mTitle);
        tv.setGravity(Gravity.CENTER);


        return tv;
    }

    public static VpSimpleFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(BUNDLE_TITLE, title);
        VpSimpleFragment fragment = new VpSimpleFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
