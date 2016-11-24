package com.app.egguncle.imageviewloadingtest;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

/**
 * http://blog.csdn.net/guolin_blog/article/details/9316683
 * 高效加载大图
 */
public class MainActivity extends AppCompatActivity {

    private ImageView img;
    private final static String TAG = "MY_TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.img);


        float maxMemory = (float) (Runtime.getRuntime().maxMemory() / 1024 /1024);
        Log.d(TAG, "Max memory is " + maxMemory + "MB");

        img.setImageBitmap(decodeSampleBitmapFromResource(getResources(),R.drawable.img_test,500,108));

    }

    /**
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return 计算出实际宽高和目标宽高的比例
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampleBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize=calculateInSampleSize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds=false;

        int imgHeight = options.outHeight;
        int imgWidth = options.outWidth;
        String imgType = options.outMimeType;
        Log.d(TAG, "height" + imgHeight);
        Log.d(TAG, "width" + imgWidth);
        Log.d(TAG, "type" + imgType);

        return BitmapFactory.decodeResource(res,resId,options);
    }
}
