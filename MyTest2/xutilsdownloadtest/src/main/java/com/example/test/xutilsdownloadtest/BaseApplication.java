package com.example.test.xutilsdownloadtest;

import android.app.Application;

import org.xutils.x;

/**
 * Created by egguncle on 16.10.9.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
