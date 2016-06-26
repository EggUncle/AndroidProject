package uncle.egg.getvcf;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


/**
 * Created by egguncle on 16.6.19.
 */
public class ClientThread implements Runnable {
    private Socket s;
    private Handler handler;
    public Handler revHnadler;
    private OutputStream os;


    public ClientThread() {
      //  this.handler = handler;
    }

    @Override
    public void run() {

        try {
            s = new Socket("192.168.1.105", 30000);
             os = s.getOutputStream();
            Looper.prepare();
            revHnadler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x123) {
                        try {
                            Log.v("mymsg","sssss");
                          //  BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

                            os.write("test".getBytes("utf-8"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            };
            Looper.loop();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
