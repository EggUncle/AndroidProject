package uncle.egg.volleytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
* volley网络框架
*
* */
public class MainActivity extends AppCompatActivity {

    private TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        volley_Get();
    }

    private void volley_Get() {
        String url = "http://gank.io/api/history/content/2/1";
//        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                //数据请求成功
//                Log.v("MY_TAG",s);
//                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
//                text.setText(s);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                 //数据请求失败
//                Log.v("MY_TAG",volleyError.toString());
//                Toast.makeText(MainActivity.this,volleyError.toString(),Toast.LENGTH_LONG).show();
//                text.setText(volleyError.toString());
//            }
//        });
//        request.setTag("abcGet");
//        MyApplication.getHttpQueues().add(request);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray catName;
                        try {
                            catName = jsonObject.getJSONArray("results");
                            String s=catName.optJSONObject(1).get("title").toString();
                            String tel = jsonObject.optString("title");
                            text.setText(s + " " + tel);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                             text.setText(volleyError.toString());
                    }
                });

        request.setTag("abcGet");
        MyApplication.getHttpQueues().add(request);

    }
}
