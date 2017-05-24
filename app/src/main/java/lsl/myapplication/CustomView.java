package lsl.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Description: 饼图自定义view
 * Author   :lishoulin
 * Date     :2017/5/22.
 */

public class CustomView extends View {
    private Context mContext;

    private Paint mPaint;

    private int width;
    private int heigth;
    private List<PieData> mDatas;

    private float start=0;

    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    private RectF mRectF;
    public CustomView(Context context) {
        this(context,null,0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }

    private void initPaint() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10f);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initPaint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w;
        heigth=h;
        float r=(Math.min(width,heigth)/2)*0.5f;
        mRectF=new RectF(-r,-r,r,r);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mDatas==null){
            return;
        }
        float current=start;
        canvas.translate(width/2,heigth/2);
        for (PieData d:mDatas){
            mPaint.setColor(d.getColor());
            canvas.drawArc(mRectF,current,d.getAngle(),true,mPaint);
            current+=d.getAngle();
        }

        mPaint.setColor(Color.RED);

        canvas.drawText("Android",200,200,mPaint);

    }



    public void  setDatas(List<PieData> datas){
        if(datas==null||datas.size()==0){
            return;
        }
        int i=0;
        for(PieData d :datas){
            d.setColor(mColors[i]);
            float ang=360*d.getPercentage();
            d.setAngle(ang);
            i++;
        }

        invalidate();

        mDatas=datas;
    }
}
