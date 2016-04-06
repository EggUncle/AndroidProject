package com.example.administrator.webviewtest;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText url;
    WebView show;
    Button mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlStr = url.getText().toString();
                show.loadUrl(urlStr);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_SEARCH) {
            String urlStr = url.getText().toString();
            show.loadUrl(urlStr);
            return true;
        }

        return false;
    }

    private void init() {
        mSearch = (Button) findViewById(R.id.my_search);
        url = (EditText) findViewById(R.id.url);
        show = (WebView) findViewById(R.id.show);
    }
}
