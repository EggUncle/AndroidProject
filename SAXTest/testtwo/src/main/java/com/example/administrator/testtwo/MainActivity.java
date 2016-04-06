package com.example.administrator.testtwo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    List<Map<String,Object>> riverData;
    Map map;
    MyAdapter myAdapter;

    boolean bFlag = false;
    River myRiver;
    String rLength;
    String rName;

    Button sax_btn;
    Button get_btn;
    ImageView img;
    Bitmap bmp;
  //  Bitmap bmlmg;
    ListView my_list;

    private String url_1 = "http://imgsrc.baidu.com/baike/pic/item/389aa8fdb7b8322e08244d3c.jpg";
    private String url_2 = "http://imgsrc.baidu.com/baike/pic/item/389aa8fdb7b8322e08244d3c.jpg";
    private String url_3 = "http://imgsrc.baidu.com/baike/pic/item/389aa8fdb7b8322e08244d3c.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        riverData = new ArrayList<>();
        myAdapter  = new MyAdapter();
        setlistener();
    }


    private void init() {
        sax_btn = (Button) findViewById(R.id.sax_btn);
        get_btn = (Button) findViewById(R.id.get_btn);
   //     img = (ImageView) findViewById(R.id.img);
        my_list = (ListView) findViewById(R.id.my_list);
        img = new ImageView(this);

    }

    private void setlistener() {
        sax_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_Resolve();
            }
        });

        get_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for (River myRiver : riverData) {
//                    Log.v("msg", "名字   " + myRiver.getRiverName());
//                    Log.v("msg", "长度   " + myRiver.getRiverLength() + "");
//                    Log.v("msg", "介绍   " + myRiver.getRiverAbout());
//                }
                my_list.setAdapter(myAdapter);
            }
        });
    }

    private void my_Resolve() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            MyHandle handle = new MyHandle();
            parser.parse(getInput(), handle);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private InputStream getInput() {
        try {
            // return getResources().getAssets().open("mybook.xml");
            return getResources().getAssets().open("a.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class MyHandle extends DefaultHandler {
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            if (bFlag) {
                {
                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            Bitmap bmp = getURLimage(url_1);
                            Message msg = new Message();
                            msg.what = 0;
                            msg.obj = bmp;
                            System.out.println("000");
                            handle.sendMessage(msg);
                        }
                    }).start();
                }
                //   myBook.setBookName(new String(ch, start, length));
                //      myRiver.setRiverAbout(new String(ch, start, length));
                map = new HashMap();
                map.put("about", new String(ch, start, length));
                map.put("name", rName);
                map.put("length", rLength);
                map.put("img",bmp);
                riverData.add(map);
               // bFlag = false;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
//            if (localName.equals("introduction")) {
           //      bFlag = false;
            //    myRiver.setRiverLength(Integer.parseInt(rLength));
            //    myRiver.setRiverName(rName);
            //    riverData.add(myRiver);
           //     if (myRiver != null) {
           //         myRiver = null;
           //     }
//                bookData.add(myBook);
//                if (myBook != null) {
//                    myBook = null;
//                }
    //        }
        }


        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            //   Log.v("msg", localName);
            //     Log.v("msg", qName);

      //      myRiver = new River();

//            if (localName.equals("introduction")) {
//                //      Log.v("msg", attributes.getValue(""));
//                //    myBook = new Book();
//
//
//            }

            if (qName.equals("river")) {
                Log.v("msg", attributes.getValue("name"));
                Log.v("msg", attributes.getValue("length"));
//                myRiver.setRiverName(attributes.getValue("name"));
//                myRiver.setRiverLength(Integer.parseInt(attributes.getValue("length")));
                rName = attributes.getValue("name");
                rLength = attributes.getValue("length");
                bFlag = true;
            }
        }
    }




    public Bitmap getURLimage(String url) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

    private Handler handle = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    System.out.println("111");
                     bmp=(Bitmap)msg.obj;
                 //   img.setImageBitmap(bmp);
                    break;
            }
        };
    };

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return riverData.size();
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
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView=getLayoutInflater().inflate(R.layout.list_item_layout,null);
                viewHolder.river_name= (TextView) convertView.findViewById(R.id.river_name);
                viewHolder.river_length= (TextView) convertView.findViewById(R.id.river_length);
                viewHolder.river_about=(TextView)convertView.findViewById(R.id.about_river);
                viewHolder.river_img= (ImageView) convertView.findViewById(R.id.img_river);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
        //    viewHolder.river_name.setText("fdasfdasf");
            viewHolder.river_name.setText(riverData.get(position).get("name").toString());
            viewHolder.river_length.setText(riverData.get(position).get("length").toString());
            viewHolder.river_about.setText(riverData.get(position).get("about").toString());
            viewHolder.river_img.setImageBitmap((Bitmap)riverData.get(position).get("img"));
            return convertView;
        }

        private class ViewHolder {
            TextView river_name;
            TextView river_length;
            TextView river_about;
            ImageView river_img;
        }
    }
}

