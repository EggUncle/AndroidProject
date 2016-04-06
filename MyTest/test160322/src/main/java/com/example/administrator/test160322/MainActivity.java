package com.example.administrator.test160322;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Handler handler;
    Animation anim;
    Animation reverse;
    FloatingActionButton fab;
    Toolbar toolbar;
    PopupWindow mPopupWindow;
    WindowManager.LayoutParams lp;

    View view_popwindow;
    Boolean bol_fab_btn = true;
    List<Map<String, Object>> listdata;
    Map map;
    ListView list;

    MyListAdapter myListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setMyPopWindow();
        setMyListener();
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void init() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    fab.startAnimation(reverse);
                }
                if (msg.what == 0x345) {
                    //  popup.dismiss();
                }
            }
        };

        myListAdapter = new MyListAdapter();
        listdata = new ArrayList<>();
        list = (ListView) findViewById(R.id.list);
        for(int i=0;i<8;i++){
            map = new HashMap();
            map.put("date",i+"月"+i+"日");
            map.put("weather","晴"+i);
            listdata.add(map);
        }
        myListAdapter.setListdata(this,listdata);
        list.setSelector(R.drawable.bg_list_item);
        list.setAdapter(myListAdapter);

        anim = AnimationUtils.loadAnimation(this, R.anim.anim);
        anim.setFillAfter(true);
        reverse = AnimationUtils.loadAnimation(this, R.anim.reverse);
        reverse.setFillAfter(true);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

         lp = getWindow().getAttributes();
    }

    private void setMyPopWindow() {
        view_popwindow = this.getLayoutInflater().inflate(R.layout.popwindow_layout, null);
        mPopupWindow = new PopupWindow(view_popwindow, 400, 350);
    }

    private void setMyListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bol_fab_btn) {

                    lp.alpha=0.6f;
                    getWindow().setAttributes(lp);
                    fab.setAlpha(1f);
                    mPopupWindow.showAsDropDown(findViewById(R.id.fab), -400, 0);

                    fab.startAnimation(anim);
                    bol_fab_btn = false;
                } else {
                    lp.alpha=1f;
                    getWindow().setAttributes(lp);
                    mPopupWindow.dismiss();
                    // popup.dismiss();
                    fab.startAnimation(reverse);
                    bol_fab_btn = true;
                }
//
//                new Timer().schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        handler.sendEmptyMessage(0x123);
//                    }
//                },1000);

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("取消", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Toast.makeText(MainActivity.this, "TEST", Toast.LENGTH_SHORT).show();
//                            }
//                        }).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id==R.id.scan){
            Toast.makeText(MainActivity.this,"---scan---",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Toast.makeText(this, "nav_camera", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(this, "nav_gallery", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(this, "nav_slideshow", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_manage) {
            Toast.makeText(this, "nav_manage", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "nav_share", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_send) {
            Toast.makeText(this, "nav_send", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
