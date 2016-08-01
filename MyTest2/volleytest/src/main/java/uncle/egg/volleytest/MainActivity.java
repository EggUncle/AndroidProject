package uncle.egg.volleytest;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
* volley网络框架
*
* */
public class MainActivity extends AppCompatActivity {

    private TextView text;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        img = (ImageView) findViewById(R.id.img);
            volley_Get();
    //    getImage();
    }


    private void getImage() {
        String url = "http://www.owbase.com/uploads/newspic/images/2016/2016062501.jpg";
        RequestQueue queue = Volley.newRequestQueue(this);
        //ImageRequest的构造函数接收六个参数，
        // 第一个参数就是图片的URL地址
        // 第二个参数是图片请求成功的回调，这里我们把返回的Bitmap参数设置到ImageView中
        // 第三第四个参数分别用于指定允许图片最大的宽度和高度，
        // 如果指定的网络图片的宽度或高度大于这里的最大值，则会对图片进行压缩，
        // 指定成0的话就表示不管图片有多大，都不会进行压缩
        // 。第五个参数用于指定图片的颜色属性，
        // Bitmap.Config下的几个常量都可以在这里使用，其中ARGB_8888可以展示最好的颜色属性
        // ，每个图片像素占据4个字节的大小，而RGB_565则表示每个图片像素占据2个字节大小。
        // 第六个参数是图片请求失败的回调，
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {

            @Override
            public void onResponse(Bitmap bitmap) {
                img.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                img.setImageResource(R.mipmap.ic_launcher);
            }
        });
        imageRequest.setTag("imgGet");
        MyApplication.getHttpQueues().add(imageRequest);
    }

    private void volley_Get() {
     //   String url = "http://gank.io/api/history/content/2/1";
        String url = "http://gank.io/api/search/query/listview/category/Android/count/10/page/1";
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
                            String s = catName.optJSONObject(1).get("desc").toString();
                            String tel =catName.optJSONObject(1).get("type").toString();
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
