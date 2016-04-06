package com.example.administrator.myxmltest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.inputmethodservice.Keyboard;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class MainActivity extends AppCompatActivity {

    Button btn_arrive;
    Button btn_not_arrive;
    Button btn_ask_leave;
    ListView mylist;

    Myadapter myadapter;
    List<Map<String, Object>> listdata;

    final String file_path = "mnt/sdcard/a/test2.xls";
    int nowPostition = 0;

    Sheet sheet = null;
    Workbook book = null;
    Label label = null;
    int count;
    int Rows;

    List<Label> labelList;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0x123: {
//                    Workbook rwb = null;
//                    try {
//                        rwb = Workbook.getWorkbook(new File(file_path));
//                        WritableWorkbook wwb = Workbook.createWorkbook(new File(
//                                file_path), rwb);// copy
//                        WritableSheet ws = wwb.getSheet(0);
//
//                        ws.addCell((Label) msg.obj);
//
//                        wwb.write();
//                        wwb.close();
//                        rwb.close();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }
                break;
                case 0x345: {
                    updateExcel((Label) msg.obj);
                }
                break;
                case 0x456: {
                    updateExcel((Label) msg.obj);
                }
                break;
            }

        }
    };

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setMyClick();
        readExcel();
        listdata.get(nowPostition).put("color", Color.parseColor("#bfc7cf"));
    }


    private void init() {
        btn_arrive = (Button) findViewById(R.id.arrive);
        btn_not_arrive = (Button) findViewById(R.id.not_arrive);
        btn_ask_leave = (Button) findViewById(R.id.ask_leave);
        mylist = (ListView) findViewById(R.id.mylist);

        myadapter = new Myadapter();
        listdata = new ArrayList<>();
        labelList = new ArrayList<>();

        preferences = getSharedPreferences("count", MODE_PRIVATE);
        count = preferences.getInt("count", 0);
        Toast.makeText(this, count + "", Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("count", ++count);
        editor.commit();

        try {
            book = Workbook
                    .getWorkbook(new File(file_path));
            book.getNumberOfSheets();
            // 获得第一个工作表对象
            sheet = book.getSheet(0);
            //获得总行数
            Rows = sheet.getRows();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }

    }


    private void setMyClick() {
        btn_arrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveNext();
            }
        });
        btn_not_arrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listdata.get(nowPostition).put("stu_arrive", "缺勤");
                label = new Label(count + 2, nowPostition + 2, "缺勤");

//                new Timer().schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        Message msg = new Message();
//                        msg.what = 0x345;
//                        msg.obj = label;
//                        handler.sendMessage(msg);
//                    }
//                }, 0, 10);

                Message msg = new Message();
                msg.what = 0x345;
                msg.obj = label;
                handler.sendMessage(msg);
                moveNext();

            }
        });

        btn_ask_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listdata.get(nowPostition).put("stu_arrive", "请假");
                label = new Label(count + 2, nowPostition + 2, "请假");

                Message msg = new Message();
                msg.what = 0x456;
                msg.obj = label;
                handler.sendMessage(msg);

//                new Timer().schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        Message msg = new Message();
//                        msg.what = 0x456;
//                        msg.obj = label;
//                        handler.sendMessage(msg);
//                    }
//                }, 0, 10);

                moveNext();
            }
        });

        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("修改 " + listdata.get(position).get("name").toString() + " 的出勤情况").setNegativeButton("请假", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                changeType("请假", position);
                            }
                        }).setPositiveButton("已到", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                changeType(" ", position);
                            }
                        }).setNeutralButton("缺勤", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                changeType("缺勤", position);
                            }
                        });
                builder.create().show();
            }
        });
    }

    public void updateExcel(Label label) {
        try {
            Workbook rwb = Workbook.getWorkbook(new File(file_path));
            WritableWorkbook wwb = Workbook.createWorkbook(new File(
                    file_path), rwb);// copy
            WritableSheet ws = wwb.getSheet(0);

            ws.addCell(label);

            wwb.write();
            wwb.close();
            rwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeType(String str, int position) {
        //  listdata.get(position + 1).put("stu_arrive", str);
        //    nowPostition++;
        label = new Label(count + 2, position + 2, str);
        // sheet.addCell(label);
        updateExcel(label);
        //  labelList.add(label);
        //    nowPostition--;
        listdata.get(position).put("stu_arrive", str);
//        Message msg = new Message();
//        msg.what=0x345;
//        handler.sendMessage(msg);
        mylist.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();
    }

    private void moveNext() {
        if (nowPostition != (Rows - 3)) {
            if (nowPostition > (Rows - 3) / 2) {
                mylist.setStackFromBottom(true);
            }

            listdata.get(++nowPostition).put("color", Color.parseColor("#bfc7cf"));
            if (nowPostition - 1 >= 0) {
                listdata.get(nowPostition - 1).put("color", Color.WHITE);
            }
            mylist.setAdapter(myadapter);
            myadapter.notifyDataSetChanged();
        } else {
            mylist.setAdapter(myadapter);
            myadapter.notifyDataSetChanged();
//            MyAyncTask myAyncTask = new MyAyncTask();
//            myAyncTask.execute();
            Toast.makeText(this, "点名完成", Toast.LENGTH_SHORT).show();
        }
    }


    public void readExcel() {
        try {

            /**
             * 后续考虑问题,比如Excel里面的图片以及其他数据类型的读取
             **/
            //   InputStream is = new FileInputStream("mnt/sdcard/test.xls");
            InputStream is = new FileInputStream(file_path);
//            Workbook book = Workbook
//                    .getWorkbook(new File("mnt/sdcard/test.xls"));


            int Cols = sheet.getColumns();
            if (count > Cols) {
                Toast.makeText(MainActivity.this, "点名表已满！", Toast.LENGTH_SHORT);
            }
            Log.v("MY_TAG", "当前工作表的名字:" + sheet.getName());
            Log.v("MY_TAG", "总行数:" + Rows);
            Log.v("MY_TAG", "当前工作表的名字:" + Cols);
            for (int j = 2; j < Rows; ++j) {
                int i = 0;
                String name = (sheet.getCell(++i, j)).getContents();
                i--;
                if (!name.equals("")) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("stu_id", (sheet.getCell(i, j)).getContents());
                    map.put("name", name);
                    map.put("stu_arrive", " ");
                    map.put("color", Color.WHITE);
                    listdata.add(map);
                } else {
                    Rows = j;
                    continue;
                }
                System.out
                        .print((sheet.getCell(i, j)).getContents() + "\t");
                Log.v("MY_TAG", (sheet.getCell(i, j)).getContents());
                //   }
            }
            System.out.print("\n");
            //   }
            mylist.setAdapter(myadapter);
            // 得到第一列第一行的单元格
            Cell cell1 = sheet.getCell(0, 0);
            String result = cell1.getContents();
            System.out.println(result);
            book.close();
        } catch (Exception e) {
            System.out.println(e);
            Log.v("MY_TAG", "Error read");
            Log.v("MY_TAG", e + "");
        }
    }


//    public void createExcel() {
//        try {
//            // 创建或打开Excel文件
//            WritableWorkbook book = Workbook.createWorkbook(new File(
//                    file_path));
//
//            // 生成名为“第一页”的工作表,参数0表示这是第一页
////            WritableSheet sheet1 = book.createSheet("第一页", 0);
////            WritableSheet sheet2 = book.createSheet("第三页", 2);
////
////             在Label对象的构造函数中,元格位置是第一列第一行(0,0)以及单元格内容为test
////                 Label label = new Label(3, 3, "test");
////
////             将定义好的单元格添加到工作表中
////                 sheet1.addCell(label);
//
//            /*
//             * 生成一个保存数字的单元格.必须使用Number的完整包路径,否则有语法歧义
//             */
//            //     jxl.write.Number number = new jxl.write.Number(1, 1, 555.12541);
//            //      sheet1.addCell(number);
//
//            // 写入数据并关闭文件
//            //      book.write();
//            //       book.close();
//        } catch (Exception e) {
//            System.out.println(e);
//            Log.v("MY_TAG", "Error create");
//            Log.v("MY_TAG", e + "");
//        }
//    }

//    public static void writeExcel(String filePath) {
//        try {
//            // 创建工作薄
//            WritableWorkbook wwb = Workbook.createWorkbook(new File(filePath));
//            // 创建工作表
//            WritableSheet ws = wwb.createSheet("Sheet1", 0);
//            // 添加标签文本
//            // Random rnd = new Random((new Date()).getTime());
//            // int forNumber = rnd.nextInt(100);
//            // Label label = new Label(0, 0, "test");
//            // for (int i = 0; i < 3; i++) {
//            // ws.addCell(label);
//            // ws.addCell(new jxl.write.Number(rnd.nextInt(50), rnd
//            // .nextInt(50), rnd.nextInt(1000)));
//            // }
//            // 添加图片(注意此处jxl暂时只支持png格式的图片)
//            // 0,1分别代表x,y 2,5代表宽和高占的单元格数
//            ws.addImage(new WritableImage(4, 4, 2, 5, new File(
//                    "mnt/sdcard/nb.png")));
//            wwb.write();
//            wwb.close();
//        } catch (Exception e) {
//            System.out.println(e.toString());
//            Log.v("MY_TAG", "Error write");
//            Log.v("MY_TAG", e + "");
//        }
//    }

    private class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return listdata.size();
        }

        @Override
        public Object getItem(int position) {
            return mylist.getItemAtPosition(position);
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
                viewHolder.txt_name = (TextView) convertView.findViewById(R.id.name);
                viewHolder.txt_stu_id = (TextView) convertView.findViewById(R.id.stu_id);
                viewHolder.txt_stu_arrive = (TextView) convertView.findViewById(R.id.stu_arrive);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.txt_stu_id.setText(listdata.get(position).get("stu_id").toString());
            viewHolder.txt_name.setText(listdata.get(position).get("name").toString());
            viewHolder.txt_stu_arrive.setText(listdata.get(position).get("stu_arrive").toString());

            viewHolder.txt_name.setBackgroundColor((Integer) listdata.get(position).get("color"));
            viewHolder.txt_stu_id.setBackgroundColor((Integer) listdata.get(position).get("color"));
            viewHolder.txt_stu_arrive.setBackgroundColor((Integer) listdata.get(position).get("color"));
            return convertView;
        }

        private class ViewHolder {
            TextView txt_name;
            TextView txt_stu_id;
            TextView txt_stu_arrive;
        }
    }

}
