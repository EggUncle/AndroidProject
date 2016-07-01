package uncle.egg.listviewfresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by egguncle on 16.6.28.
 */
public class ReFreshListView extends ListView implements AbsListView.OnScrollListener {

    View header;//顶部布局文件
    int headerHeight;
    int firstVisibleItem;

    boolean isRemark;
    int startY;

    public ReFreshListView(Context context) {
        super(context);
        initView(context);
    }

    public ReFreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ReFreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        header = layoutInflater.inflate(R.layout.handler_layout, null);
        measureView(header);
        headerHeight = header.getMeasuredHeight();
        topPadding(-headerHeight);
        this.addHeaderView(header);
        setOnScrollListener(this);
    }

    private void measureView(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int width = ViewGroup.getChildMeasureSpec(0, 0, p.width);
        int height;
        int tempHeight = p.height;
        if (tempHeight > 0) {
            height = MeasureSpec.makeMeasureSpec(tempHeight, MeasureSpec.EXACTLY);
        } else {
            height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }

        view.measure(width, height);

    }

    private void topPadding(int toppadding) {
        header.setPadding(header.getPaddingLeft(), toppadding, header.getPaddingRight(), header.getPaddingBottom());
        header.invalidate();

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.firstVisibleItem = firstVisibleItem;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (firstVisibleItem == 0) {
                    isRemark = true;
                    startY = (int) ev.getY();
                }

                break;
            case MotionEvent.ACTION_MOVE:
                onMove(ev);
                break;
        }

        return super.onTouchEvent(ev);
    }

    private void onMove(MotionEvent ev) {
        if (!isRemark) {
            return;
        }
        int tempY = (int) ev.getY();
        int space = tempY - startY;
        int topPadding = space - headerHeight;
        topPadding(topPadding);
    }
}
