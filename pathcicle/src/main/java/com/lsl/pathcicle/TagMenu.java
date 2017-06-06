package com.lsl.pathcicle;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/6/6.
 */

public class TagMenu extends ViewGroup {


    private int heigth, width;

    public TagMenu(Context context) {
        this(context, null, 0);
    }

    public TagMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        heigth = h;
        width = w;
    }

    private void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        for (int i = 0; i < getChildCount(); i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int tempH = 0;
        int tempW = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            int h = child.getMeasuredHeight() + params.bottomMargin + params.topMargin;
            int w = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            TranslateAnimation animation = new TranslateAnimation(-w, 0, 0, 0);
            animation.setDuration(1000+i*100);
            child.startAnimation(animation);
            child.setVisibility(VISIBLE);
            tempH += h;
            child.layout(0, heigth - tempH, w, heigth - tempH + h);
        }

    }


    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
