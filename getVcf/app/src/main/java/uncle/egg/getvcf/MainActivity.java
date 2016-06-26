package uncle.egg.getvcf;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ListView list;
    private Button btn;
    private GetVcf getVcf;
    private ArrayList<String> listData;
    private ArrayList<ArrayList<String>> listDataNumber;
    private MyListAdapter myListAdapter;

    private   ClientThread clientThread;
    private FileUnit fileUnit;

    private Handler handler;

    private Socket s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    s = new Socket("192.168.1.105", 30000);
//                    BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
//                    OutputStream os = s.getOutputStream();
//                    os.write("test".getBytes("utf-8"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();
        clientThread   = new ClientThread();
        new Thread(clientThread).start();



        init();

    }

    private void init() {
        fileUnit = new FileUnit();

        btn = (Button) findViewById(R.id.btn);
        //   fileUnit.WirteFile("test3");

//        list = (ListView) findViewById(R.id.mylist);
//        getVcf = new GetVcf(this);
//        listData = new ArrayList<>();
//        listDataNumber = new ArrayList<>();
//        listData = getVcf.getListDate();
//        listDataNumber = getVcf.getNumberDate();
//        myListAdapter = new MyListAdapter();
//        list.setAdapter(myListAdapter);

//        for (String str : listData) {
//            fileUnit.WirteFile(str+"\n");
//        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message();

                msg.what = 0x123;
                msg.obj="test666";
            clientThread.revHnadler.sendMessage(msg);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                }).start();


            }
        });


//        for(int i =0;i<listData.size();i++){
//            fileUnit.WirteFile(listData.get(i).toString()+"\n");
//            for(String str:listDataNumber.get(i)){
//                fileUnit.WirteFile("tel: "+str+"\n");
//            }
//        }

    }


    private class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.list_item, null);
                viewHolder.txtName = (TextView) convertView.findViewById(R.id.txt_name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.txtName.setText(listData.get(position).toString());
            //  fileUnit.WirteFile(viewHolder.txtName.getText().toString()+"\n");
            return convertView;
        }

        private class ViewHolder {
            TextView txtName;
        }
    }

}
