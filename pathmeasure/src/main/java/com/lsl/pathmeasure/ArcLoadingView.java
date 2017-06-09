package com.lsl.pathmeasure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/6/8.
 */

public class ArcLoadingView extends View {

    int heigth, weigth;
    float radius;
    Paint mPaint;

    Paint scanPaint;

    public ArcLoadingView(Context context) {
        super(context);
    }

    public ArcLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
//        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1f);

        scanPaint = new Paint();
        scanPaint.setColor(Color.parseColor("#FF4081"));
    }

    public ArcLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        heigth = h;
        weigth = w;
        radius = Math.min(heigth, weigth) / 2 * 0.7f;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(weigth / 2, heigth / 2, radius, mPaint);

        RectF rectF = new RectF(weigth / 2 - radius, heigth / 2 - radius, weigth / 2 + radius, heigth / 2 + radius);

        canvas.drawArc(rectF, 270, 45, true, scanPaint);

        canvas.drawCircle(weigth / 2, heigth / 2, radius * 0.87f, mPaint);
        scanPaint.setTextAlign(Paint.Align.CENTER);
        scanPaint.setTextSize(20);
        canvas.drawText("45%",weigth/2,heigth/2,scanPaint);

    }
}
