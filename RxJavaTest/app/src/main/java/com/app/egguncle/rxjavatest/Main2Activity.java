package com.app.egguncle.rxjavatest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Main2Activity extends AppCompatActivity {

    private Button btn;
    private ImageView img;
    private ImageView img2;
    private ImageView img3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn = (Button) findViewById(R.id.btn);
        img = (ImageView) findViewById(R.id.img);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);

        final String[] filePaths={
                Environment.getExternalStorageDirectory() + "/test2/test_img.jpg",
                Environment.getExternalStorageDirectory() + "/test2/test_img2.jpg",
                Environment.getExternalStorageDirectory() + "/test2/test_img3.jpg",
        };
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Observable.from(filePaths)
                        .map(new Func1<String, Bitmap>() {
                            @Override
                            public Bitmap call(String s) {
                                return BitmapFactory.decodeFile(s);
                            }
                        })
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Bitmap>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Bitmap bitmap) {
                                img.setImageBitmap(bitmap);
                                img2.setImageBitmap(bitmap);
                                img3.setImageBitmap(bitmap);
                            }
                        });
            }
        });
    }
}
