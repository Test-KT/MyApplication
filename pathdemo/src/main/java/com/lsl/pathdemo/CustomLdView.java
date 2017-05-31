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
 * Date     :2017/5/25.
 */

public class CustomLdView extends View {

    private int mWidth, mHeigth;  //长宽
    private int centerX, centerY;

    private int count = 6;
    private float angle = (float) (Math.PI * 2 / count);

    private float radius;

    private Paint mPaint;
    private Context mContext;


    public CustomLdView(Context context) {
        this(context, null, 0);
    }

    public CustomLdView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomLdView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2f);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeigth = h;
        centerX = mWidth / 2;
        centerY = mHeigth / 2;
        radius = (float) (Math.min(w, h) / 2);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.translate(mWidth / 2, mHeigth / 2); //移动坐标系







        drawPolygon(canvas); //画多边形

    }

    private void drawPolygon(Canvas canvas) {

        Path path = new Path();
        float r = radius / (count - 1);
        for (int i = 1; i < count; i++) {
            float curR = r * i;
            path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY);
                } else {
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (centerX + curR * Math.cos(angle * j));
                    float y = (float) (centerY + curR * Math.sin(angle * j));
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, mPaint);
        }
    }
}
