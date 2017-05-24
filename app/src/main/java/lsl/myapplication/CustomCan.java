package lsl.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Description: bitmap使用
 * Author   :lishoulin
 * Date     :2017/5/23.
 */

public class CustomCan extends View {
    private int w, h;

    private Paint mPaint;

    private Picture mPicture;

    private Bitmap mBitmap;

    private Context mContext;

    private int animCurrentPage = 12;

    public CustomCan(Context context) {
        super(context);
    }

    public CustomCan(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        init();
    }

    public CustomCan(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.source);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
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
        canvas.drawCircle(0, 0, w /4, mPaint);
        int length = mBitmap.getHeight();
        Rect rect = new Rect(animCurrentPage * length, 0, length * (animCurrentPage + 1), length);
        Rect dst = new Rect(-100, -100, 100, 100);
        canvas.drawBitmap(mBitmap, rect, dst, null);
    }
}
