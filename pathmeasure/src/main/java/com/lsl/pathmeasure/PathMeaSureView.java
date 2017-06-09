package com.lsl.pathmeasure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/6/8.
 */

public class PathMeaSureView extends View {

    int w, h;
    Paint mPaint;

    public PathMeaSureView(Context context) {
        super(context);
    }

    public PathMeaSureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(7f);

    }

    public PathMeaSureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(w / 2, h / 2);
        Path path = new Path();
        path.lineTo(0, 200);
        path.lineTo(200, 200);
        path.lineTo(200, 0);


        PathMeasure measure2 = new PathMeasure(path, true);
        Path dst = new Path();
        measure2.getSegment(600, 800, dst, true);


        canvas.drawPath(path, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawPath(dst, mPaint);

//        canvas.drawArc();

    }
}
