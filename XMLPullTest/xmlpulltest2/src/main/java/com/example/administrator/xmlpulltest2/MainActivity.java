package com.example.administrator.xmlpulltest2;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btn;
    ListView list;

    List<River> riverData;
    List<Map<String, Object>> listRiverData;
    River river;
    MyAdapter myadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setListener();
    }

    private void init() {
        btn = (Button) findViewById(R.id.btn);
        list = (ListView) findViewById(R.id.my_list);
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

    public void setListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myParam();
//                for (CBook book: bookData) {
//                    Log.v("msg", " aaaaa " + book.getStrBookName());
//                }
                listRiverData = new ArrayList<Map<String, Object>>();
                myadapter = new MyAdapter();
                for (River river : riverData) {
                    Log.v("msg", "----------------");
                    Log.v("msg", river.getRiverName() + "");
                    Log.v("msg", river.getRiverLength() + "");
                    Log.v("msg", river.getRiverAbout() + "");
                    Map map = new HashMap();
                    map.put("name", river.getRiverName());
                    map.put("length", river.getRiverLength() + "");
                    map.put("about", river.getRiverAbout() + "");
                    listRiverData.add(map);
                }
                list.setAdapter(myadapter);
            }
        });
    }

    public void myParam() {
        String rLength = null;
        String rName = null;
        Boolean rFlag = false;
        //创建工厂
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            //添加设备
            XmlPullParser parser = factory.newPullParser();
            //
            parser.setInput(getInput(), "utf-8");

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        riverData = new ArrayList<>();
                        Log.v("msg", "START_DOCUMENT " + parser.getName());
                        break;
                    case XmlPullParser.START_TAG:
                        rLength = parser.getAttributeValue(null, "length");
                        rName = parser.getAttributeValue(null, "name");
                        if (rLength != null && rName != null) {
                            river = new River();
                            river.setRiverName(rName);
                            river.setRiverLength(Integer.parseInt(rLength));
                        }
                        Log.v("msg", "START_TAG " + parser.getName());
                        if (parser.getName().equals("introduction")) {
                            rFlag = true;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        Log.v("msg", "END_TAG " + parser.getName());
                        if (rFlag) {
                            if (river != null) {
                                rLength = null;
                                rName = null;
                                river = null;
                                rFlag = false;
                            }
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if (rFlag) {
//                                Log.v("msg", "about");
//                                Log.v("msg", parser.getText());
                            river.setRiverAbout(parser.getText());
                            riverData.add(river);
                        }

                        break;
                }
                eventType = parser.next();
            }
            Log.v("msg", "END_END_DOCUMENT " + parser.getName());

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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
                convertView = getLayoutInflater().inflate(R.layout.list_item_layout, null);
                viewHolder.river_name = (TextView) convertView.findViewById(R.id.river_name);
                viewHolder.river_length = (TextView) convertView.findViewById(R.id.river_length);
                viewHolder.river_about = (TextView) convertView.findViewById(R.id.about_river);
                viewHolder.river_img = (ImageView) convertView.findViewById(R.id.img_river);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //    viewHolder.river_name.setText("fdasfdasf");
            viewHolder.river_name.setText(listRiverData.get(position).get("name").toString());
            viewHolder.river_length.setText(listRiverData.get(position).get("length").toString());
            viewHolder.river_about.setText(listRiverData.get(position).get("about").toString());
            // viewHolder.river_img.setImageBitmap((Bitmap) riverData.get(position).get("img"));
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
