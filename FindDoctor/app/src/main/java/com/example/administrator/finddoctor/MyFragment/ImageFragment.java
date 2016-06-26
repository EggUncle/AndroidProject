package com.example.administrator.finddoctor.MyFragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.finddoctor.R;

/**
 * Created by egguncle on 16.4.14.
 */
public class ImageFragment extends Fragment {
    int resources;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // view = inflater.inflate(R.layout.type_layout, null);
      //  init();
        ImageView img = new ImageView(getContext());
        img.setImageResource(resources);
        return img;
    }

   public ImageFragment(int resources){
       this.resources=resources;
   }
}
