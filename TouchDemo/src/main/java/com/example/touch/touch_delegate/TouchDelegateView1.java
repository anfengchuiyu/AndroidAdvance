package com.example.touch.touch_delegate;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by liuzhe on 2016/7/27.
 * 第一种touch委托方式
 */
public class TouchDelegateView1 extends FrameLayout {

    private Point mTouchOffsetPoint = new Point();

    public TouchDelegateView1(Context context) {
        super(context);
        init();
    }

    public TouchDelegateView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchDelegateView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true; //1.一直拦截touch事件，不让其传递到子view中去
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //2.拦截之后，调用自身的onTouchEvent
        if(getChildCount() < 0){
            return false;
        }

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            //记录当前按下的位置坐标
            mTouchOffsetPoint.x = (int) event.getX();
            mTouchOffsetPoint.y = (int) event.getY();
        }

        View view = getChildAt(0);
        //设置当前按下的位置坐标为子View的触摸区域的中心点
        event.offsetLocation(-mTouchOffsetPoint.x + view.getWidth()/2, -mTouchOffsetPoint.y + view.getHeight()/2);

        return view.dispatchTouchEvent(event);
    }
}
