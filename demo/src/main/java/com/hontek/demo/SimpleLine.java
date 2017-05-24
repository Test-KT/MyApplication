package com.hontek.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/5/24.
 */

public class SimpleLine extends View {

    private String[] xItems = {};
    private String[] yItems = {};


    private String textcolor = "#3F51B5";
    private int textSize = 30;
    private Paint mTextPaint;

    private int heigth;
    private int width;


    private int yPoint[];

    private int xPoint[];

    private int yInterval;  //y轴间距
    private int xInterval; //x轴间距


    private Paint mPaint;


    public SimpleLine(Context context) {
        this(context, null, 0);
    }

    public SimpleLine(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public SimpleLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mTextPaint = new Paint();
        mTextPaint.setTextSize(textSize);
        mTextPaint.setColor(Color.parseColor(textcolor));

        mPaint = new Paint();
        mPaint.setStrokeWidth(10f);
        mPaint.setColor(Color.RED);
    }

    //设置x轴
    public void setXItems(String[] xItems) {
        this.xItems = xItems;
        xPoint = new int[this.xItems.length];
    }

    //设置y轴
    public void setYItems(String[] yItems) {
        this.yItems = yItems;
        yPoint = new int[this.yItems.length];


    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        heigth = h;
        xInterval = (width - 50) / xItems.length;
        yInterval = heigth / yItems.length;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (xItems.length == 0 || yItems.length == 0) {
            throw new IllegalArgumentException("point is null");
        }

        for (int i = 0; i < yItems.length; i++) {
            yPoint[i] = yInterval * i;
            canvas.drawText(yItems[i], 0, yPoint[i], mTextPaint);
        }
        int y = yPoint[yItems.length - 1] + yInterval;


        for (int i = 0; i < xItems.length; i++) {
            xPoint[i] = xInterval * i + xInterval;
            canvas.drawText(xItems[i], xPoint[i], y, mTextPaint);
        }


        for (int i = 0; i < xPoint.length; i++) {
            canvas.drawPoint(xPoint[i], yPoint[i], mPaint);
            if (i + 1 != xPoint.length) {
                canvas.drawLine(xPoint[i], yPoint[i], xPoint[i + 1], yPoint[i + 1], mPaint);
            }
        }


    }
}
