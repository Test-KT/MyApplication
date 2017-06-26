package com.lsl.pathmeasure;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/6/26.
 */

public class PathChange extends View {
    public PathChange(Context context) {
        super(context);
    }

    public PathChange(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PathChange(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
    
}
