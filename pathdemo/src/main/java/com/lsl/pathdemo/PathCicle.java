package com.lsl.pathdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/5/31.
 */

public class PathCicle extends View {
    Paint mPaint;
    int w, h;

    public PathCicle(Context context) {
        super(context);
    }

    public PathCicle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8f);
        mPaint.setAntiAlias(true);
    }

    public PathCicle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        canvas.drawCircle(0, 0, 200, mPaint);

        Path path = new Path();
        path.moveTo(-200, 0);
//        path.quadTo(0, 100, 200, 0);
        path.cubicTo(-200, 200, 200, -200, 200, 0);
        canvas.drawPath(path, mPaint);


    }
}
