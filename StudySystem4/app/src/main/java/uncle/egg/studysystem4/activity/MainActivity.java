package uncle.egg.studysystem4.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import uncle.egg.studysystem4.R;
import uncle.egg.studysystem4.fragment.AdminFragment;
import uncle.egg.studysystem4.fragment.ErrorsFragment;
import uncle.egg.studysystem4.fragment.ExamFragment;
import uncle.egg.studysystem4.fragment.SimpleFragment;
import uncle.egg.studysystem4.fragment.StudyFragment;
import uncle.egg.studysystem4.fragment.FourthFragment;
import uncle.egg.studysystem4.fragment.SecondFragment;
import uncle.egg.studysystem4.fragment.CommunicateFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView imgWelcome;
    private FragmentManager fm;
    private FragmentTransaction transaction;

    private StudyFragment studyFragment;
    private SecondFragment secondFragment;
    private CommunicateFragment communicateFragment;
    private FourthFragment fourthFragment;

    private ErrorsFragment errorsFragment;
    private AdminFragment adminFragment;
    private SimpleFragment simpleFragment;
    private ExamFragment examFragment;

    private boolean isQuit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("欢迎来到考试系统");
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
        imgWelcome = (ImageView) findViewById(R.id.img_welcome);
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        studyFragment = new StudyFragment();
        secondFragment = new SecondFragment();
        communicateFragment = new CommunicateFragment();
        fourthFragment = new FourthFragment();
        errorsFragment = new ErrorsFragment();
        adminFragment = new AdminFragment();
        simpleFragment  = new SimpleFragment();
        examFragment  = new ExamFragment();
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



        if (id == R.id.simple_select) {
            //   Toast.makeText(this,"test",Toast.LENGTH_SHORT).show();
            // Handle the camera action

            transaction = fm.beginTransaction();
            transaction.replace(R.id.fragment, simpleFragment);
            transaction.commit();
            setTitle("单项选择");



        } else if (id == R.id.exam) {
            transaction = fm.beginTransaction();
            transaction.replace(R.id.fragment, examFragment);
            transaction.commit();
            setTitle("模拟考试");
        } else if (id == R.id.study) {
            transaction = fm.beginTransaction();
            transaction.replace(R.id.fragment, studyFragment);
            transaction.commit();
            setTitle("学习篇");
        } else if (id == R.id.errors) {
            transaction = fm.beginTransaction();
            transaction.replace(R.id.fragment, simpleFragment);
            transaction.commit();
            setTitle("错题集");
        } else if (id == R.id.communicate) {
            transaction = fm.beginTransaction();
            transaction.replace(R.id.fragment, communicateFragment);
            transaction.commit();
            setTitle("交流互动");
        } else if (id == R.id.admin) {
            transaction = fm.beginTransaction();
            transaction.replace(R.id.fragment, adminFragment);
            transaction.commit();
            setTitle("管理员登录");
        }

        if (imgWelcome.getVisibility() != View.GONE) {
            imgWelcome.setVisibility(View.GONE);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isQuit = false;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isQuit) {
                isQuit = true;
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                // 利用handler延迟发送更改状态信息
                mHandler.sendEmptyMessageDelayed(0, 2000);
            } else {
                finish();
                System.exit(0);
            }
        }
        return false;
    }
}
