package com.lsl.pathmeasure;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import com.bumptech.glide.Glide;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/6/9.
 */

public class TextParseView extends android.support.v7.widget.AppCompatTextView {
    public TextParseView(Context context) {
        super(context);
    }

    public TextParseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);

    }


    public TextParseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init(final Context c) {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                CharSequence html = getText();
                SpannableStringBuilder builder = (SpannableStringBuilder) Html.fromHtml(html.toString(), new GlideImageGetter(c, Glide.with(c), TextParseView.this, false, ((View) getParent()).getWidth(), 800), null);
                setText(builder);

            }
        });

    }


}
