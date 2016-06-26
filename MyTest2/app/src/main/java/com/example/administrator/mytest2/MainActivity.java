package com.example.administrator.mytest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;

import com.lidroid.xutils.view.annotation.ViewInject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_xml;
    private List<CPeron> data;
    private CPeron person = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_xml = (Button) findViewById(R.id.btn_xml);

        btn_xml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputStream input = getResources().getAssets().open("person.xml");
                    XmlPullParser pullParser = Xml.newPullParser();
                    pullParser.setInput(input, "utf-8");
                    int event = pullParser.getEventType();
                    while (event != XmlPullParser.END_DOCUMENT) {
                        switch (event) {
                            case XmlPullParser.START_DOCUMENT: {
                                data = new ArrayList<CPeron>();
                            }
                            break;
                            case XmlPullParser.END_DOCUMENT: {
                                data.add(person);
                                event = pullParser.next();
                            }
                            break;
                            case XmlPullParser.START_TAG: {
                                String name = null;

                            }
                            break;
                            case XmlPullParser.END_TAG: {
                            }
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
