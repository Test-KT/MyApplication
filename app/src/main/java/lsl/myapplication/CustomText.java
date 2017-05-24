package lsl.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/5/24.
 */

public class CustomText extends View {

    private Paint mPaint;

    public CustomText(Context context) {
        super(context);
    }

    public CustomText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }


    public CustomText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10f);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(50);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        String ABD = "ABCDEF";
//        canvas.drawText(ABD,200,500,mPaint);

        canvas.drawText(ABD, 1, 3, 200, 500, mPaint); //截取文本 [起始位置~末尾位置) 末尾呈开区间 例如要截取 BC [1,3)  并且起始是0哦


    }
}
