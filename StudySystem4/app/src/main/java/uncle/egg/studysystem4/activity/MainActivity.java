package uncle.egg.studysystem4.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import uncle.egg.studysystem4.R;
import uncle.egg.studysystem4.fragment.FirstFragment;
import uncle.egg.studysystem4.fragment.FourthFragment;
import uncle.egg.studysystem4.fragment.SecondFragment;
import uncle.egg.studysystem4.fragment.CommunicateFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView txtWelcome;
    private FragmentManager fm;
    private FragmentTransaction transaction;

    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private CommunicateFragment communicateFragment;
    private FourthFragment fourthFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        init();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void init() {
        txtWelcome = (TextView) findViewById(R.id.txt_welcome);
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
        communicateFragment = new CommunicateFragment();
        fourthFragment = new FourthFragment();
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

//        FirstFragment firstFragment = new FirstFragment();
//        transaction.add(firstFragment,null);
//        transaction.commit();

        if (id == R.id.simple_select) {
            //   Toast.makeText(this,"test",Toast.LENGTH_SHORT).show();
            // Handle the camera action



            transaction = fm.beginTransaction();
            transaction.replace(R.id.fragment, firstFragment);
            transaction.commit();


        } else if (id == R.id.exam) {
            transaction = fm.beginTransaction();
            transaction.replace(R.id.fragment, secondFragment);
            transaction.commit();
        } else if (id == R.id.study) {

        } else if (id == R.id.errors) {
            transaction = fm.beginTransaction();
            transaction.replace(R.id.fragment, fourthFragment);
            transaction.commit();
        } else if (id == R.id.communicate) {
            transaction = fm.beginTransaction();
            transaction.replace(R.id.fragment, communicateFragment);
            transaction.commit();
        } else if (id == R.id.admin) {

        }

        if (txtWelcome.getVisibility() != View.GONE) {
            txtWelcome.setVisibility(View.GONE);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
