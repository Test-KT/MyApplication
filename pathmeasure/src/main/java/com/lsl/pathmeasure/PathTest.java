package com.lsl.pathmeasure;

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
 * Date     :2017/6/26.
 */

public class PathTest extends View {

    Paint mPaint;
    int w, h;

    public PathTest(Context context) {
        this(context, null, 0);
    }

    public PathTest(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);


    }

    public PathTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10f);
        mPaint.setColor(Color.RED);
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
        canvas.drawCircle(0, 0, 50, mPaint);

        Path path = new Path();
        path.lineTo(50, 100);
        canvas.drawPath(path, mPaint);

        Path path1 = new Path();
        path.moveTo(10, 10);
        path.rLineTo(100, 100);
        canvas.drawPath(path1, mPaint);
    }
}

