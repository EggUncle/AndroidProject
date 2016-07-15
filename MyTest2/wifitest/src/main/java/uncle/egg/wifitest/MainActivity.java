package uncle.egg.wifitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MY_TAG";

    private Button getWifi;
    private Button openCloseWifi;
    private Button createWifiLock;
    private Button lockUnlockWifi;
    private Button getConfiguration;
    private Button connect;
    private Button scanWifi;

    private WifiAdmin mWifiAdmin;
    private Boolean wifiState;
    private Boolean wifiLockState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        getWifi = (Button) findViewById(R.id.get_wifi);
        openCloseWifi = (Button) findViewById(R.id.open_close_wifi);
        createWifiLock = (Button) findViewById(R.id.create_wifi_lock);
        lockUnlockWifi = (Button) findViewById(R.id.lock_unlock_wifi);
        getConfiguration = (Button) findViewById(R.id.get_configuration);
        connect = (Button) findViewById(R.id.connect);
        scanWifi = (Button) findViewById(R.id.scan_wifi);

        wifiState = false;
        wifiLockState = false;

        MyClick myClick = new MyClick();

        getWifi.setOnClickListener(myClick);
        openCloseWifi.setOnClickListener(myClick);
        createWifiLock.setOnClickListener(myClick);
        lockUnlockWifi.setOnClickListener(myClick);
        scanWifi.setOnClickListener(myClick);
    }

    private class MyClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.get_wifi: {
                    mWifiAdmin = new WifiAdmin(MainActivity.this);
                    Log.v(TAG, "创建wifi对象");
                }
                break;
                case R.id.open_close_wifi: {
                    if (!wifiState) {
                        mWifiAdmin.OpenWifi();
                        wifiState = true;
                        Log.v(TAG, "打开wifi");
                    } else {
                        mWifiAdmin.CloseWifi();
                        wifiState = false;
                        Log.v(TAG, "关闭wifi");
                    }
                }
                break;
                case R.id.create_wifi_lock: {
                    mWifiAdmin.CreatWifiLock();
                    Log.v(TAG, "创建wifi锁");
                }
                break;
                case R.id.lock_unlock_wifi: {
                    if (!wifiLockState) {
                        mWifiAdmin.AcquireWifiLock();
                        wifiLockState = true;
                        Log.v(TAG, "锁定wifi");
                    } else {
                        mWifiAdmin.ReleaseWifiLock();
                        wifiLockState = false;
                        Log.v(TAG, "解锁wifi");
                    }
                }
                break;
                case R.id.get_configuration: {

                }
                break;
                case R.id.connect: {

                }
                break;
                case R.id.scan_wifi: {
                    mWifiAdmin.StartScan();
                    Log.v(TAG, "扫描wifi");
                    Log.v(TAG, "扫描结果:  \n" + mWifiAdmin.LookUpScan());
                }
                break;
            }
        }
    }
}
