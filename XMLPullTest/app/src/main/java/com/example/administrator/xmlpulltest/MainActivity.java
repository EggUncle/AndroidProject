package com.example.administrator.xmlpulltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView textView;

    List<CBook> bookData;
    String bFlag;
    CBook book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setListener();
    }

    public void init() {
        btn = (Button) findViewById(R.id.btn);
        textView = (TextView) findViewById(R.id.txt);
    }

    public void setListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  myParam();
                for (CBook book: bookData) {
                    Log.v("msg"," aaaaa "+book.getStrBookName());
                }
            }
        });
    }

    public InputStream getInput() {
        try {
            return getResources().getAssets().open("a.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void myParam() {
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
                        bookData = new ArrayList<>();
                        Log.v("msg","START_DOCUMENT "+parser.getName());
                        break;
                    case XmlPullParser.START_TAG:
                        if("book".equals(parser.getName())){
                            bFlag="book";
                        }
                        Log.v("msg","START_TAG "+parser.getName());
                        break;
                    case XmlPullParser.END_TAG:
                        Log.v("msg","END_TAG "+parser.getName());
                        if(book!=null){
                            bookData.add(book);
                            book=null;
                            bFlag="";
                        }
                        break;
                    case XmlPullParser.TEXT:
                       // Log.v("msg","TEXT "+parser.getName());
                        if ("book".equals(bFlag)){
                            book  = new CBook();
                            book.setStrBookName(parser.getText());
                        }
                        break;
                }
                eventType = parser.next();
            }
            Log.v("msg","END_END_DOCUMENT "+parser.getName());

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
