package com.example.administrator.myfragmenttest3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements BookListFragment.Callbacks{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_twopane);
    }

    @Override
    public void onItemSelected(Integer id) {
        Bundle argments = new Bundle();
        argments.putInt(BookDetailFragment.ITEM_ID,id);
        BookDetailFragment fragment  = new BookDetailFragment();
        fragment.setArguments(argments);
        getFragmentManager().beginTransaction().replace(R.id.book_detail_container,fragment).commit();
    }
}
