package com.example.administrator.saxtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    List<Book> bookData;
 //   List<River> riverData;
    Button sax_btn;
    Button get_btn;
    boolean bFlag=false;
    Book myBook;
   // River myRiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setlistener();
        bookData = new ArrayList<>();
     //   riverData = new ArrayList<>();
    }

    private void init() {
        sax_btn = (Button) findViewById(R.id.sax_btn);
        get_btn = (Button) findViewById(R.id.get_btn);
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
                for (Book myBook : bookData) {
                     Log.v("msg",myBook.getBookName());
                }
//                for (River myRiver : riverData) {
//                   Log.v("msg",myRiver.getRiverName());
//               }
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
           return getResources().getAssets().open("mybook.xml");
         //   return getResources().getAssets().open("a.xml");
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
                myBook.setBookName(new String(ch, start, length));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if (localName.equals("introduction")) {
                bFlag = false;
                bookData.add(myBook);
                if (myBook != null) {
                    myBook = null;
                }
            }
        }


        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
         //   Log.v("msg", localName);
       //     Log.v("msg", qName);
            if (localName.equals("introduction")) {
         //      Log.v("msg", attributes.getValue(""));
                myBook = new Book();
                bFlag = true;
            }
        }
    }
}
