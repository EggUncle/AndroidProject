package com.example.administrator.smartcommunity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView_home;
    private RecyclerView recyclerView_community;
    private MyAdapter myAdapter;
  //  private MyAdapter myAdapter2;
    private CommunityAdapter communityAdapter;
    private List<Map<String, Object>> listData;
    private List<Map<String, Object>> listData2;
    private FrameLayout frame_layout_home;
    private FrameLayout frame_layout_community;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        init();

    }

    private void init() {
        String[] data_str = new String[]{"sensor_1", "sensor_2","sensor_3","sensor_4"};

        frame_layout_community = (FrameLayout) findViewById(R.id.frame_layout_community);
        frame_layout_home = (FrameLayout) findViewById(R.id.frame_layout_home);


        Map map;
        listData = new ArrayList<>();
        listData2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            map = new HashMap();
            map.put("sensor_name",data_str[i]);
            listData.add(map);
        }
        for (int i = 0; i < 9; i++) {
            map = new HashMap();
            map.put("txt_community","test"+i);
            listData2.add(map);
        }
        recyclerView_community = (RecyclerView) findViewById(R.id.recyclerview_community);
        recyclerView_community.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView_community.setItemAnimator(new DefaultItemAnimator());
        recyclerView_community.setHasFixedSize(true);
        communityAdapter = new CommunityAdapter(this, listData2);
        recyclerView_community.setAdapter(communityAdapter);

        recyclerView_home = (RecyclerView) findViewById(R.id.recyclerview_home);
        recyclerView_home.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_home.setItemAnimator(new DefaultItemAnimator());
        recyclerView_home.setHasFixedSize(true);
        myAdapter = new MyAdapter(this, listData);
        recyclerView_home.setAdapter(myAdapter);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            frame_layout_home.setVisibility(View.VISIBLE);
            frame_layout_community.setVisibility(View.GONE);
            // Handle the camera action
//            Intent intent = new Intent(MainActivity.this,SensorActivity.class);
//            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            frame_layout_home.setVisibility(View.GONE);
            frame_layout_community.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
