package uncle.egg.wifitest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class Main2Activity extends Activity implements View.OnClickListener {
    // 右侧滚动条按钮
    private ScrollView sView;
    private Button openNetCard;
    private Button closeNetCard;
    private Button checkNetCardState;
    private Button scan;
    private Button getScanResult;
    private Button connect;
    private Button disconnect;
    private Button checkNetWorkState;
    private TextView scanResult;

    private String mScanResult;
    private WifiAdmin2 mWifiAdmin;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mWifiAdmin = new WifiAdmin2(Main2Activity.this);
        init();
    }

    /**
     * 按钮等控件的初始化
     */
    public void init() {
        sView = (ScrollView) findViewById(R.id.mScrollView);
        openNetCard = (Button) findViewById(R.id.openNetCard);
        closeNetCard = (Button) findViewById(R.id.closeNetCard);
        checkNetCardState = (Button) findViewById(R.id.checkNetCardState);
        scan = (Button) findViewById(R.id.scan);
        getScanResult = (Button) findViewById(R.id.getScanResult);
        scanResult = (TextView) findViewById(R.id.scanResult);
        connect = (Button) findViewById(R.id.connect);
        disconnect = (Button) findViewById(R.id.disconnect);
        checkNetWorkState = (Button) findViewById(R.id.checkNetWorkState);

        openNetCard.setOnClickListener(Main2Activity.this);
        closeNetCard.setOnClickListener(Main2Activity.this);
        checkNetCardState.setOnClickListener(Main2Activity.this);
        scan.setOnClickListener(Main2Activity.this);
        getScanResult.setOnClickListener(Main2Activity.this);
        connect.setOnClickListener(Main2Activity.this);
        disconnect.setOnClickListener(Main2Activity.this);
        checkNetWorkState.setOnClickListener(Main2Activity.this);
    }

    /**
     * WIFI_STATE_DISABLING 0 WIFI_STATE_DISABLED 1 WIFI_STATE_ENABLING 2
     * WIFI_STATE_ENABLED 3
     */
    public void openNetCard() {
        mWifiAdmin.openNetCard();
    }

    public void closeNetCard() {
        mWifiAdmin.closeNetCard();
    }

    public void checkNetCardState() {
        mWifiAdmin.checkNetCardState();
    }

    public void scan() {
        mWifiAdmin.scan();
    }

    public void getScanResult() {
        mScanResult = mWifiAdmin.getScanResult();
        scanResult.setText(mScanResult);
    }

    public void connect() {
        mWifiAdmin.connect();
//		startActivityForResult(new Intent(
//				android.provider.Settings.ACTION_WIFI_SETTINGS), 0);
        startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
    }

    public void disconnect() {
        mWifiAdmin.disconnectWifi();
    }

    public void checkNetWorkState() {
        mWifiAdmin.checkNetWorkState();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.openNetCard:
                openNetCard();
                break;
            case R.id.closeNetCard:
                closeNetCard();
                break;
            case R.id.checkNetCardState:
                checkNetCardState();
                break;
            case R.id.scan:
                scan();
                break;
            case R.id.getScanResult:
                getScanResult();
                break;
            case R.id.connect:
                connect();
                break;
            case R.id.disconnect:
                disconnect();
                break;
            case R.id.checkNetWorkState:
                checkNetWorkState();
                break;
            default:
                break;
        }
    }
}
