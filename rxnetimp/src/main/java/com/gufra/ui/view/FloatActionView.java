package com.gufra.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class FloatActionView extends View {
    private String TAG = "gufra.FloatActionView";
    /**
     * 屏幕
     */
    DisplayMetrics metrics;
    private int mWidth;
    private int mHeight;
    /**
     * 最终位置
     */
    private int lastX = 0;
    private int lastY = 0;

    public FloatActionView(Context context) {
        this(context,null);

    }

    public FloatActionView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public FloatActionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG,"构造函数");
        metrics = context.getResources().getDisplayMetrics();
        mWidth = metrics.widthPixels;
        mHeight = metrics.heightPixels;
        Log.d(TAG,"width:"+mWidth+"&&height:"+mHeight);
    }
    int dx = 0;
    int dy =0;
    int left = 0;
    int right = 0;
    int top = 0;
    int bottom = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int rowX = (int) event.getRawX();
        int rowY = (int) event.getRawY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                lastX = rowX;
                lastY = rowY;
                break;
            case MotionEvent.ACTION_MOVE:
                //计算移动了多少
                dx = rowX - lastX;
                dy = rowY - lastY;
                //计算当前位置
                left = getLeft() + dx;
                right = getRight() + dx;
                top = getTop() + dy;
                bottom = getBottom() + dy;
                //设置当前位置
                layout(left, top, right, bottom);
                //当前位置设置为最终位置
                lastX = rowX;
                lastY = rowY;
                break;
            case MotionEvent.ACTION_UP:
                //计算当前位置
                top = getTop() + dy;
                bottom = getBottom() + dy;
                //判断当前位置离那里较近
                if (left > mWidth / 2) {
                    Log.d(TAG,"离右边近");
                    //离右边近
                    left = mWidth - (getRight() - getLeft());
                    right = mWidth;
                } else {
                    Log.d(TAG,"离左边边近");
                    //离左边近
                    left = 0;
                    right = getRight() - getLeft();
                }
                //设置当前位置
                layout(left,top, right, bottom);
                //当前位置设置为最终位置
                lastX = rowX;
                lastY = rowY;
                break;
            default:
                break;
        }
        return true;
    }


}
