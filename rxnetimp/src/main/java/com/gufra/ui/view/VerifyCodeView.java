package com.gufra.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

/**
 * @author yinchaoyin
 * 图形验证码
 * */
public class VerifyCodeView extends View {
    private String TAG = "gufra.VerifyCodeView";
    private int mTextSize;
    /**宽度*/
    private int mWidth;
    /**高度*/
    private int mHeight;
    /***/
    private Random mRandom;
    /**随机码*/
    private char[] mCodes = new char[4];
    /**随机码颜色*/
    private int[] mColor = new int[4];
    /**位置*/
    private float[] mLocationY = new float[4];
    /**画笔*/
    private Paint mPaint;
    /**背景颜色*/
    private int mBackColor;

    Paint.FontMetrics metrics;

    private boolean reFresh = false;
    private boolean mIsOnClickRefresh = false;
    public VerifyCodeView(Context context) {
        super(context);
    }

    public VerifyCodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG,"构造");
    }

    public VerifyCodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
    /**onMeasure*/
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//处理wrap_content问题
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, 100);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, 100);
        }

        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
        mTextSize = (int) ((float) mWidth / 4.5);
        //如果高度小于字体大小，则字体高度为0.5倍的view高度，防止字体超出view大小
        if (mHeight < mTextSize) {
            mTextSize = (int) (0.5 * mHeight);
        }
    }

    /**onDraw*/
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG,"onDraw");
        initPaint();
        metrics =mPaint.getFontMetrics();

        if (!reFresh){
            initData();
        }
        /**设置背景颜色*/
        setBackgroundColor(mBackColor);

        String drawText = "A B C D";
        float startX = (getWidth() - mPaint.measureText(drawText)) / 2;
        for (int i = 0; i < 4; i++){
            mPaint.setColor(mColor[i]);
            float x = startX + i * mPaint.measureText("A ");
            if (i == 3) {
                canvas.drawText(String.valueOf(mCodes[i]), x, mLocationY[i], mPaint);
            } else {
                canvas.drawText(mCodes[i] + " ", x, mLocationY[i], mPaint);
            }
        }

        //画三条干扰线
        for (int i = 0; i < 3; i ++){
            mPaint.setColor(mColor[i]);
            canvas.drawLine(0,mRandom.nextInt(mHeight),mWidth,mRandom.nextInt(mHeight),mPaint);
        }
        mPaint.setStrokeWidth(8);
        //画干扰点
        for (int i = 0; i < 20; i ++){
            mPaint.setColor(getRandomTextColor());
            canvas.drawPoint(mRandom.nextInt(mWidth),mRandom.nextInt(mHeight),mPaint);
        }
    }

    /**初始化随机数*/
    private void initData(){
        Log.d(TAG,"initData");
        mRandom = new Random(System.currentTimeMillis());
        mBackColor = getRandomBackColor();
        for (int i = 0; i < 4; i++){
            mCodes[i] = getRandomCode();
            mColor[i] = getRandomTextColor();
            mLocationY[i] = getRandomY(metrics,mHeight);
        }
        //点击事件
        if (mIsOnClickRefresh) {
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //清空数据
                    reFresh = false;
                    //重绘
                    invalidate();
                }
            });
        }
        reFresh = true;
    }

    /**初始画笔*/
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
        /**去锯齿*/
        mPaint.setAntiAlias(true);
        /**设置画笔笔触宽度*/
        mPaint.setStrokeWidth(4);
    }

    /**获取随机码*/
    private char getRandomCode(){
        int i = mRandom.nextInt(42)+48;
        while (i > 57 && i < 65){
            i = mRandom.nextInt(42)+48;
        }
        return (char)(i);
    }

    /**获取随机背景颜色*/
    private int getRandomBackColor(){
        int r = mRandom.nextInt(140)+115;
        int g = mRandom.nextInt(140)+115;
        int b = mRandom.nextInt(140)+115;
        return Color.rgb(r,g,b);
    }

    /**获取随机字体颜色*/
    private int getRandomTextColor(){
        int r = mRandom.nextInt(90)+40;
        int g = mRandom.nextInt(90)+40;
        int b = mRandom.nextInt(90)+40;
        return Color.rgb(r,g,b);
    }

    /**获取随机Y位置*/
    private float getRandomY(Paint.FontMetrics fontMetrics, int height){
        int min = (int) (height - Math.abs(fontMetrics.ascent) - fontMetrics.descent);
        return mRandom.nextInt(min) + Math.abs(fontMetrics.ascent);
    }

    /**对比检查*/
    public boolean checkCodes(String code){
        if (TextUtils.isEmpty(String.valueOf(mCodes)) || TextUtils.isEmpty(code)) {
            return false;
        }
        if (String.valueOf(mCodes).toLowerCase().equals(code.trim().toLowerCase())) {
            return true;
        }
        return false;
    }
    /**可刷新状态*/
    public void setOnClickReFresh(boolean fresh){
        Log.d(TAG,"setOnClickReFresh"+fresh);
        if (fresh){
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    reFresh =false;
                    invalidate();
                }
            });
        }
    }
    /**转换成字符串*/
    public String getCode(){
        return String.valueOf(mCodes);
    }
}
