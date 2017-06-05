package com.lsl.pathstudy;

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
 * Date     :2017/6/2.
 */

public class PathView extends View {

    private Paint mPaint;
    private int w, h;
    private float r;

    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.MAGENTA);
        mPaint.setStrokeWidth(8f);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;

        r = Math.min(w, h) * 0.4f;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(w / 2, h / 2);
        Path path = new Path();
        path.moveTo(-r, 0);
        path.quadTo(r / 2, -r / 8, r, 0);
        path.quadTo(r / 2, r / 8, r, 0);
        canvas.drawPath(path, mPaint);

    }
}
