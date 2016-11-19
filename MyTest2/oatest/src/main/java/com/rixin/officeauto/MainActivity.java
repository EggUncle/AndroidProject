package com.rixin.officeauto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * 关于OA接口的测试
 * <p>
 * 使用了volley框架
 */
public class MainActivity extends AppCompatActivity {

    //祖传access_token
    private static  final String ACCESSTOKEN="39mWMgBP8bbBxZ93Nj08ug4uTknnsUYguMcfGrFd";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // getAccessToken();
    }

//    /**
//     * 获取access_token
//     * 因为暂时没有获取code的方法，所以先用postman获取access_token，再来进行其他操作，该方法暂时不用
//     */
//    private void getAccessToken() {
//        String url = "https://oa.ecjtu.net/api/v1/oauth/access_token";
//        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                Log.v("MY_TAG", "SUCESS :" + s);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Log.v("MY_TAG", "Fail");
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("client_id", "aeIrXT8b7MYwQIkdSBHYqcxrtlf6DkVg2uM1J5");
//                map.put("client_secret", "BPHFm9IobSKuhltDKYuYJzjySyxsZLc7C6cLA3");
//                map.put("grant_type", "authorization_code");
//                map.put("redirect_uri", "https://oa.ecjtu.net/oauth/oauth_client/code");
//                map.put("code", "uXAJia1fBDNNgbkW3PhrdnFGT3aSLpMVqKsY12rg");  //code只能用一次
//                return map;
//            }
//
//        };
//        request.setTag("accessGet");
//        MyApplication.getHttpQueues().add(request);
//
//
//    }



}
