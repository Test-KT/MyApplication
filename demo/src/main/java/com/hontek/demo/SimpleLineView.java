package com.hontek.demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashMap;

/**
 * Description: 简单折线图学习
 * Author   :lishoulin
 * Date     :2017/5/25.
 */

public class SimpleLineView extends View {

    private int width, heigth;

    private Paint textPaint; //文字画笔

    private Paint pointPaint; //点画笔

    private Paint linePaint;//线条画笔

    //一些参数
    private float mFontSize = 24;
    private float mStrokeWidth = 8.0f;
    private float mPointRadius = 10f;
    private int defalutColor = Color.parseColor("#00BCD4");

    private int xyColor;  //xy轴坐标颜色

    private int lineColor;  //线条颜色

    private int pointColor;


    private String[] mXitems = {};
    private String[] mYitems = {};

    private HashMap<Integer, Integer> mPoints;

    private int[] xPoint;


    private int yInterval;//y轴间距

    private int xInterval;//x轴间距


    //X轴偏移量
    private int xOffset = 50;

    private Context mContext;


    public SimpleLineView(Context context) {
        this(context, null, 0);
    }

    public SimpleLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {

        TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.SimpleLineView);
        mFontSize = array.getInteger(R.styleable.SimpleLineView_fontSize, 24);
        mStrokeWidth = array.getFloat(R.styleable.SimpleLineView_StrokeWidth, 8.0f);
        mPointRadius = array.getFloat(R.styleable.SimpleLineView_StrokeWidth, 10f);
        xyColor = array.getColor(R.styleable.SimpleLineView_xyColor, defalutColor);
        lineColor = array.getColor(R.styleable.SimpleLineView_lineColor, defalutColor);
        pointColor = array.getColor(R.styleable.SimpleLineView_pointColor, defalutColor);
        array.recycle();


        textPaint = new Paint();
        textPaint.setTextSize(mFontSize);
        textPaint.setColor(xyColor);

        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setColor(pointColor);

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(mStrokeWidth);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        heigth = h;

        yInterval = (int) ((heigth - mFontSize) / mYitems.length);
        xInterval = (width - xOffset) / mXitems.length;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //画y轴
        for (int i = 0; i < mYitems.length; i++) {
            canvas.drawText(mYitems[i], 0, mFontSize + i * yInterval + xOffset + 10, textPaint);
        }
        //画x轴
        for (int i = 0; i < mXitems.length; i++) {
            canvas.drawText(mXitems[i], i * xInterval + xOffset, xOffset, textPaint);
            xPoint[i] = i * xInterval + xOffset + 7;
        }
        //画点
        for (int i = 0; i < mXitems.length; i++) {
            canvas.drawCircle(xPoint[i], mPoints.get(i) + 70, mPointRadius, pointPaint);

            if (i > 0) {
                canvas.drawLine(xPoint[i - 1], mPoints.get(i - 1) + 70, xPoint[i], mPoints.get(i) + 70, linePaint);
            }
        }


    }

    /**
     * 设置map数据
     *
     * @param data
     */
    public void setData(HashMap<Integer, Integer> data) {
        mPoints = data;

        invalidate();
    }

    /**
     * 设置Y轴文字
     *
     * @param yItem
     */
    public void setYItem(String[] yItem) {
        mYitems = yItem;


    }

    /**
     * 设置X轴文字
     *
     * @param xItem
     */
    public void setXItem(String[] xItem) {
        mXitems = xItem;

        xPoint = new int[mXitems.length];
    }

    /**
     * 设置字体大小
     *
     * @param fontSize
     */
    public void setFontSize(float fontSize) {
        mFontSize = fontSize;
    }

    /**
     * 设置线条宽度
     *
     * @param strokeWidth
     */
    public void setStrokeWidth(float strokeWidth) {
        mStrokeWidth = strokeWidth;
    }

    /**
     * 设置xy轴字体颜色
     *
     * @param xyColor
     */
    public void setXyColor(int xyColor) {
        this.xyColor = xyColor;
    }

    /**
     * 设置线条颜色
     *
     * @param lineColor
     */
    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    /**
     * 设置点颜色
     *
     * @param pointColor
     */
    public void setPointColor(int pointColor) {
        this.pointColor = pointColor;
    }
}
