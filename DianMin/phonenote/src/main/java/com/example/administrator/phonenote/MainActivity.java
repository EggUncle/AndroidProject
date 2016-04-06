package com.example.administrator.phonenote;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.auto)
    AutoCompleteTextView auto;
    @ViewInject(R.id.my_list)
    ListView list;

    ArrayList<String> listData;
    ArrayAdapter<String> adapter, listAdapter;
    ArrayList<ArrayList<String>> details;
    ArrayList<String> detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        listData = new ArrayList<>();
        detail = new ArrayList<>();


        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)).toString();
            detail.add(name);
            // listData.add(contactId);
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)).toString();
            Cursor phones = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);
            while (phones.moveToNext()) {
                String phonenumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                listData.add(phonenumber);
            }
            phones.close();
        }
        //  details.add(detail);
        cursor.close();
        adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.list_item
                , R.id.name_txt, detail);
        auto.setAdapter(adapter);

        listAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.list_item, R.id.name_txt, detail);
        list.setAdapter(listAdapter);
    }
}
