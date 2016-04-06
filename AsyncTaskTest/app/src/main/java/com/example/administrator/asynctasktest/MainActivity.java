package com.example.administrator.asynctasktest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends Activity {

    private TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show = (TextView) findViewById(R.id.show);
    }

    public void download(View source) throws MalformedURLException {
        DownTask task = new DownTask(this);
        task.execute(new URL("https://www.google.com/"));
    }

    class DownTask extends AsyncTask<URL, Integer, String> {

        ProgressDialog pdialog;
        int hasRead = 0;
        Context mContext;

        public DownTask(Context ctx) {
            mContext = ctx;
        }

        @Override
        protected String doInBackground(URL... params) {
            StringBuilder sb = new StringBuilder();
            URLConnection conn = null;
            try {
                conn = params[0].openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                    hasRead++;
                    publishProgress(hasRead);
                }
                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            show.setText(result);
            pdialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            pdialog = new ProgressDialog(mContext);
            // 设置对话框的标题
            pdialog.setTitle("任务正在执行中");
            // 设置对话框显示的内容
            pdialog.setMessage("任务正在执行中，敬请等待...");
            // 设置对话框不能用“取消”按钮关闭
            pdialog.setCancelable(false);
            // 设置该进度条的最大进度值
            pdialog.setMax(202);
            // 设置对话框的进度条风格
            pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            // 设置对话框的进度条是否显示进度
            pdialog.setIndeterminate(false);
            pdialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            show.setText("已经读取了【" + values[0] + "】行！");
            pdialog.setProgress(values[0]);
        }
    }
}
