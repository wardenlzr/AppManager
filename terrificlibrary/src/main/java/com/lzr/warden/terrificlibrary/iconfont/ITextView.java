package com.lzr.warden.terrificlibrary.iconfont;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lzr.warden.terrificlibrary.base.BaseApplication;

/**
 * Created by yubin
 * 2018/5/29 0029-上午 11:17
 */
@SuppressLint({"NewApi", "AppCompatCustomView"})
public class ITextView extends TextView {
    public ITextView(Context context) {
        super(context);
        initIconFont();
    }

    public ITextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initIconFont();
    }

    public ITextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initIconFont();
    }

    public ITextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initIconFont();
    }

    private void initIconFont() {
        Typeface tf = Typeface.createFromAsset(BaseApplication.getInstance().getAssets(), "iconfont.ttf");
        this.setTypeface(tf);
    }
}
