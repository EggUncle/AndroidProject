package com.example.administrator.myxmltest;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class HistoryActivity extends AppCompatActivity {

    ListView his_list;
    List<Map<String, Object>> hisListData;
    MyHisAdapter myHisAdapter;

    final String file_path = "mnt/sdcard/a/test2.xls";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        init();
        setMyClick();
        readExcel();
    }

    private void init() {
        his_list = (ListView) findViewById(R.id.his_list);
        hisListData = new ArrayList<>();
        myHisAdapter = new MyHisAdapter();
    }

    private void setMyClick() {
        his_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyDialog myDialog = new MyDialog(HistoryActivity.this);
                myDialog.setTitle(hisListData.get(position).get("name").toString());
                myDialog.show();

            }
        });
    }
    public void readExcel() {
        try {

            /**
             * 后续考虑问题,比如Excel里面的图片以及其他数据类型的读取
             **/
            InputStream is = new FileInputStream(file_path);

            Workbook book = Workbook
                    .getWorkbook(new File(file_path));
            book.getNumberOfSheets();
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int Rows = sheet.getRows();
            int Cols = sheet.getColumns();
            for (int j = 2; j < Rows; ++j) {
                int i = 0;
                String name = (sheet.getCell(++i, j)).getContents();
                i--;
                if (!name.equals("")) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("stu_num", (sheet.getCell(i, j)).getContents());
                    map.put("name", name);
                    int times = 0;
                    int times_not_arrive = 0;
                    int times_ask_leave = 0;
                    for (int n = 2; n < Cols; n++) {
                        if ((sheet.getCell(n, j).getContents()).equals("缺勤")) {
                            times_not_arrive++;
                        }
                        if ((sheet.getCell(n, j).getContents()).equals("请假")) {
                            times_ask_leave++;
                        }
                    }
                    times = times_ask_leave + times_not_arrive;
                    map.put("stu_times", times + "");
                    hisListData.add(map);
                } else {
                    Rows = j;
                    continue;
                }
                System.out
                        .print((sheet.getCell(i, j)).getContents() + "\t");
                Log.v("MY_TAG", (sheet.getCell(i, j)).getContents());
                //   }
            }
            // 得到第一列第一行的单元格
//            Cell cell1 = sheet.getCell(0, 0);
//            String result = cell1.getContents();
//            System.out.println(result);
            his_list.setAdapter(myHisAdapter);

            book.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private class MyHisAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return hisListData.size();
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
                convertView = getLayoutInflater().inflate(R.layout.his_item, null);
                viewHolder.txt_name = (TextView) convertView.findViewById(R.id.stu_name);
                viewHolder.txt_num = (TextView) convertView.findViewById(R.id.num);
                viewHolder.txt_times = (TextView) convertView.findViewById(R.id.times);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.txt_num.setText(hisListData.get(position).get("stu_num").toString());
            viewHolder.txt_name.setText(hisListData.get(position).get("name").toString());
            viewHolder.txt_times.setText(hisListData.get(position).get("stu_times").toString());

//            viewHolder.txt_name.setBackgroundColor((Integer) listdata.get(position).get("color"));
//            viewHolder.txt_stu_id.setBackgroundColor((Integer) listdata.get(position).get("color"));
//            viewHolder.txt_stu_arrive.setBackgroundColor((Integer) listdata.get(position).get("color"));
            return convertView;
        }

        private class ViewHolder {
            TextView txt_num;
            TextView txt_name;
            TextView txt_times;
        }
    }
}
