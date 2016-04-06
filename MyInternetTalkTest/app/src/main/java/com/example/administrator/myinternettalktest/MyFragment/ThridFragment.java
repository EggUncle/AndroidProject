package com.example.administrator.myinternettalktest.MyFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myinternettalktest.R;

/**
 * Created by shadow on 16.2.13.
 */
public class ThridFragment extends Fragment {
    View thridFragmentLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thridFragmentLayout = getActivity().getLayoutInflater().inflate(R.layout.fragment_thrid, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //firstFragmentLayout = inflater.inflate(R.layout.fragment_first, null);
        return thridFragmentLayout;
    }
}
