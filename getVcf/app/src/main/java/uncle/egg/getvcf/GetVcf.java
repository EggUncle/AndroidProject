package uncle.egg.getvcf;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by egguncle on 16.6.19.
 */
public class GetVcf {

    private Context context;
    private ArrayList<ArrayList<String>> list_datas;  //该名字下的号码
    private ArrayList<String> list_name_data;  //姓名

    private MyAyncTask myAyncTask;


    public GetVcf(Context context) {
        this.context = context;
        list_datas = new ArrayList<>();
        list_name_data = new ArrayList<>();
    }

    public ArrayList<String> getListDate(){

//        myAyncTask = new MyAyncTask();
//        myAyncTask.execute();
//     list_name_data=   myAyncTask.doInBackground();
        return   setListData();
    }

    public ArrayList<ArrayList<String>> getNumberDate(){

        return list_datas;
    }

    private ArrayList<String> setListData() {

        Map map = new HashMap();
        //实现姓名排序

        Cursor cursor = context.getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.Contacts.DISPLAY_NAME);
        while (cursor.moveToNext()) {
            //id
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            // map.put("id", contactId);
            //姓名
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            // map.put("name",name);
            list_name_data.add(name);
            //电话号码
            Cursor phones_number = context.getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                    , null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);
            //获取一个联系人的多个号码
            ArrayList<String> list_child_data = new ArrayList<>();
            while (phones_number.moveToNext()) {
                String number = phones_number.getString(
                        phones_number.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                list_child_data.add(number);
            }
            list_datas.add(list_child_data);
            phones_number.close();
        }
        cursor.close();
        return list_name_data;
    }

    //使用异步加载来加载联系人列表
    //第一个参数：启动任务时输入的参数类型.
    //第二个参数：后台任务执行中返回进度值的类型.
    //第三个参数：后台任务执行完成后返回结果的类型.
    private class MyAyncTask extends AsyncTask<Void, Integer, ArrayList<String>> {

     //   ArrayList<String> list_name_data;

        public MyAyncTask() {
            super();
        }


        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            //执行后返回值
            super.onPostExecute(strings);
            //       mProgressBar.setVisibility(View.GONE);
            //     my_list.setAdapter(myListAdapter);
        }

        @Override
        protected void onPreExecute() {
            //执行前的初始化操作
            super.onPreExecute();
            //       mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //更新时调用的操作
            list_name_data = setListData();
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            //后台加载时的操作
         //   setListData();
            setListData();
            return  null;
        }
    }

}
