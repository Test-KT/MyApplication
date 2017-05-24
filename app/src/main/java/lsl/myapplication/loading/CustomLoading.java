package lsl.myapplication.loading;

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
 * Date     :2017/5/24.
 */

public class CustomLoading extends View {

    private int width, heigth;//控件长宽

    private int mProgressWidth;//进度条长度

    private int mProcess = 0;//进度

    private final int TOTAL_PROCESS = 100;  //总进度

    private int mCurrentProgressPosition;//当前进度长度

    private int mArcRadius = 10;

    private Paint mWhitePaint;
    private Paint mOrangePain;

    private RectF mArcRectF;

    private RectF mWhiteRect;

    private RectF mOrangeRectF;

    public CustomLoading(Context context) {
        this(context, null, 0);
    }

    public CustomLoading(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomLoading(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {

        mWhitePaint = new Paint();
        mWhitePaint.setStyle(Paint.Style.FILL);
        mWhitePaint.setColor(Color.WHITE);
        mWhitePaint.setAntiAlias(true);


        mOrangePain = new Paint();
        mOrangePain.setStyle(Paint.Style.FILL);
        mOrangePain.setColor(Color.GREEN);
        mOrangePain.setAntiAlias(true);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        heigth = h;
        mProgressWidth = width;
        mArcRadius = h / 2;
        mArcRectF = new RectF(0, 0, mArcRadius * 2, h);
        mWhiteRect = new RectF(mArcRadius, 0, w, h);


        mOrangeRectF = new RectF(mArcRadius, 0, 0, mArcRadius * 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mCurrentProgressPosition = mProgressWidth * mProcess / TOTAL_PROCESS;
        if (mCurrentProgressPosition < mArcRadius) {
            canvas.drawArc(mArcRectF, 90, 180, false, mWhitePaint);

            canvas.drawRect(mWhiteRect, mWhitePaint);


            int angle = (int) Math.toDegrees(Math.acos((mArcRadius - mCurrentProgressPosition)
                    / (float) mArcRadius));  //计算出角度

            // 起始的位置
            int startAngle = 180 - angle;
            // 扫过的角度
            int sweepAngle = 2 * angle;

            canvas.drawArc(mArcRectF, startAngle, sweepAngle, false, mOrangePain);
        } else {
            canvas.drawArc(mArcRectF, 90, 180, false, mOrangePain);


            mWhiteRect.left = mCurrentProgressPosition;
            canvas.drawRect(mWhiteRect, mWhitePaint);

            mOrangeRectF.right = mCurrentProgressPosition;

            canvas.drawRect(mOrangeRectF, mOrangePain);
        }
    }


    public void setProcess() {
        mProcess++;
        invalidate();
    }
}
