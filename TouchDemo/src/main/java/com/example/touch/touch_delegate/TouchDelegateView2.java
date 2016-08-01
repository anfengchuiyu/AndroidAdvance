package com.example.touch.touch_delegate;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by liuzhe on 2016/7/27.
 * 第二种touch委托方式
 */
public class TouchDelegateView2 extends FrameLayout {


    public TouchDelegateView2(Context context) {
        super(context);
        init();
    }

    public TouchDelegateView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchDelegateView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        View child = getChildAt(0);
        if (child == null)
            return;
        Rect bounds = new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
        TouchDelegate delegate = new TouchDelegate(bounds, child);
        setTouchDelegate(delegate);
    }
}
