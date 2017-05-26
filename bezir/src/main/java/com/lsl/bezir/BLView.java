package com.lsl.bezir;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Description: 波浪边缘的view 学习自 wimg 的https://github.com/githubwing/WaveView.git
 * Author   :lishoulin
 * Date     :2017/5/26.
 */

public class BLView extends View {


    private Paint mPaint;
    private int width, heigth;
    private int padding = 20;
    private int radius = 10;

    private int count = 10;


    public BLView(Context context) {
        this(context, null, 0);
    }

    public BLView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public BLView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        heigth = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(padding, padding, width - padding, heigth - padding, mPaint);
//        int count = (heigth - padding) / radius; //得出波浪数
//
//        for (int i = 0; i < count / 2 - 1; i++) {
//            canvas.drawCircle(padding, i * radius * 2 + padding + radius, radius, mPaint);
//        }
//
//        for (int i = 0; i < count / 2 - 1; i++) {
//            canvas.drawCircle(width - padding, i * radius * 2 + padding + radius, radius, mPaint);
//        }

        Path path = new Path();
        int r = (heigth - padding * 2) / count;//得到三角形的边长

        path.moveTo(padding, padding); //移动到开始坐标

        //左边的
        for (int i = 0; i < count; i++) {
            path.lineTo(padding - r / 2, padding + i * r + r / 2);
            path.lineTo(padding, (i + 1) * r + padding);
        }
        path.close();
        canvas.drawPath(path, mPaint);
        //右边的

        path.moveTo(width - padding, padding);
        for (int i = 0; i < count; i++) {
            path.lineTo((width - padding) + r / 2, padding + i * r + r / 2);
            path.lineTo(width - padding, (i + 1) * r + padding);
        }
        path.close();
        canvas.drawPath(path, mPaint);
    }
}