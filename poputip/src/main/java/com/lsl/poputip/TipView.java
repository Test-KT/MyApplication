package com.lsl.poputip;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/6/5.
 */

public class TipView extends View {

    private Paint mPaint;
    private int mWidth, mHeigth;

    public TipView(Context context) {
        this(context, null, 0);
    }

    public TipView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeigth = h;
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRoundRect(0, 0, mWidth, (float) (mHeigth * 0.7), 10, 10, mPaint);
        Path path = new Path();
        path.moveTo(mWidth/2, (float) (mHeigth * 0.7));
        path.lineTo(mWidth/2-60, (float) (mHeigth * 0.7) + 40);
        path.lineTo((float) ((mWidth /2)* 0.7), (float) (mHeigth * 0.7));
        path.close();
        canvas.drawPath(path, mPaint);


    }
}
