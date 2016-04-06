package com.example.administrator.myfragmenttest3;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileDescriptor;
import java.io.PrintWriter;

import javax.security.auth.callback.Callback;

/**
 * Created by 西域战神阿凡提 on 16.1.22.
 */
public class BookListFragment extends ListFragment {
    private Callbacks mCallbacks;

    public  interface  Callbacks{
        public  void onItemSelected(Integer id);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<BookContent.Book>(getActivity(),android.R.layout.simple_list_item_activated_1,android.R.id.text1,BookContent.ITEMS));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(!(context instanceof  Callbacks)){
            throw new IllegalStateException("Callbacks接口未实现");
        }
        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks=null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mCallbacks.onItemSelected(BookContent.ITEMS.get(position).id);
    }

    public void setActivateOnItemClick(boolean activateOnItemClick){
        getListView().setChoiceMode(activateOnItemClick?ListView.CHOICE_MODE_SINGLE:ListView.CHOICE_MODE_NONE);
    }
}
