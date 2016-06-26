package com.example.administrator.httptest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//
//    public void ppost() {
//        String uriAPI = "http://119.29.189.61:8080/Nafio/Emulator/test/tempPostWml.jsp";
//		/*建立HTTP Post连线*/
//        HttpPost httpRequest =new HttpPost(uriAPI);
//        //Post运作传送变数必须用NameValuePair[]阵列储存
//        //传参数 服务端获取的方法为request.getParameter("name")
//        List<NameValuePair> params=new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("imei","imei"));
//        params.add(new BasicNameValuePair("wml","我的测试"));
//        try{
//            //发出HTTP request
//            httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));//注意这里要写成utf-8,与jsp对应
//            //取得HTTP response
//            HttpResponse httpResponse=new DefaultHttpClient().execute(httpRequest);
//            //若状态码为200 ok
//            if(httpResponse.getStatusLine().getStatusCode()==200){
//                //取出回应字串
//                String strResult= EntityUtils.toString(httpResponse.getEntity());
//            }else{
//                Log.e("n", "b");
//            }
//        }catch(ClientProtocolException e){
//
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static final String URL = "http://119.29.189.61:8080/Test_2/login";
    Button submitBtnPost = null;
    Button submitBtnGet = null;
    TextView infoTextView = null;
    EditText nameEdit = null;
    EditText codeEdit = null;
    ScrollView scrollView = null;
    boolean isPost = true; //默认采取post登录方式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView = (ScrollView) findViewById(R.id.info_scroll_view);
        submitBtnPost = (Button) findViewById(R.id.btn_submit_post);
        submitBtnGet = (Button) findViewById(R.id.btn_submit_get);
        infoTextView = (TextView) findViewById(R.id.tv_info);
        nameEdit = (EditText) findViewById(R.id.edit_name);
        codeEdit = (EditText) findViewById(R.id.edit_code);
        submitBtnPost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                isPost = true;
                new SubmitAsyncTask().execute(URL);
            }
        });
        submitBtnGet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                isPost = false;
                new SubmitAsyncTask().execute(URL);
            }
        });
    }


    public class SubmitAsyncTask extends AsyncTask<String, Void, String> {
        String info = "";

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String url = params[0];
            String reps = "";
            if (isPost) {
                info = "HttpPost返回结果: ";
                reps = doPost(url);
            } else {
                info = "HttpGet返回结果: ";
                reps = doGet(url);
            }
            return reps;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            infoTextView.append("\n" + info + result + "\n");
            String res = result.trim();
            if (res.equals("0")) {
                info = "验证通过.....";
            } else if (res.equals("1")) {
                info = "密码错误.....";
            } else if (res.equals("2")) {
                info = "用户名错误.....";
            } else if (res.equals("-1")) {
                info = "返回结果异常！";
            }
            infoTextView.append(info + "\n");
            scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            super.onPostExecute(result);
        }


    }

    private String doGet(String url) {
        String responseStr = "";
        try {
            String name = nameEdit.getText().toString().trim();
            String code = codeEdit.getText().toString().trim();
            String getUrl = URL + "?NAME=" + name + "&" + "CODE=" + code;

            HttpGet httpRequest = new HttpGet(getUrl);
            HttpParams params = new BasicHttpParams();
            ConnManagerParams.setTimeout(params, 1000);
            HttpConnectionParams.setConnectionTimeout(params, 3000);
            HttpConnectionParams.setSoTimeout(params, 5000);
            httpRequest.setParams(params);

            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
            final int ret = httpResponse.getStatusLine().getStatusCode();
            if (ret == HttpStatus.SC_OK) {
                responseStr = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);
            } else {
                responseStr = "-1";
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return responseStr;
    }

    /**
     * 156.     * 用Post方式跟服务器传递数据
     * 157.     * @param url
     * 158.     * @return
     * 159.
     */
    private String doPost(String url) {
        String responseStr = "";
        try {
            HttpPost httpRequest = new HttpPost(url);
            HttpParams params = new BasicHttpParams();
            ConnManagerParams.setTimeout(params, 1000); //从连接池中获取连接的超时时间
            HttpConnectionParams.setConnectionTimeout(params, 3000);//通过网络与服务器建立连接的超时时间
            HttpConnectionParams.setSoTimeout(params, 5000);//读响应数据的超时时间
            httpRequest.setParams(params);
            //下面开始跟服务器传递数据，使用BasicNameValuePair
            List<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
            String name = nameEdit.getText().toString().trim();
            String code = codeEdit.getText().toString().trim();
            paramsList.add(new BasicNameValuePair("NAME", name));
            paramsList.add(new BasicNameValuePair("CODE", code));
            UrlEncodedFormEntity mUrlEncodeFormEntity = new UrlEncodedFormEntity(paramsList, HTTP.UTF_8);
            httpRequest.setEntity(mUrlEncodeFormEntity);
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            final int ret = httpResponse.getStatusLine().getStatusCode();
            if (ret == HttpStatus.SC_OK) {
                responseStr = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);
            } else {
                responseStr = "-1";
            }

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return responseStr;
    }

}