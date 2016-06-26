package uncle.egg.studysystem.myView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import uncle.egg.studysystem.R;

/**
 * Created by egguncle on 16.6.14.
 */
public class myButton extends LinearLayout {

    private TextView textView;
    private ImageView imageView;

    public myButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        textView = (TextView) findViewById(R.id.txt);
        imageView = (ImageView) findViewById(R.id.img);
    }



}
