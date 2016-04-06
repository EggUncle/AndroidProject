package com.example.administrator.myimagetest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 西域战神阿凡提 on 2016/1/20.
 */
public class MyCircle extends View {
    public MyCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        // RectF rectF = new RectF(100,100,100,100);
        paint.setAntiAlias(true);
        canvas.drawCircle(100, 100, 100, paint);
//        RectF rectF = new RectF(100,100,100,100);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.soto);
        // canvas.drawArc(rectF,100,180,true,paint);
//        canvas.drawBitmap(bitmap,20,20,paint);
//        if (!bitmap.isRecycled()) {
//            bitmap.recycle();
//        }
    }
}
