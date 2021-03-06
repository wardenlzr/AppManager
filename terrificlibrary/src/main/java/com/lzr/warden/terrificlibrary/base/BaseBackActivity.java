package com.lzr.warden.terrificlibrary.base;

import android.annotation.SuppressLint;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.lzr.warden.terrificlibrary.R;
import com.lzr.warden.terrificlibrary.util.BarUtils;
import com.r0adkll.slidr.Slidr;


/**
 * <pre>
 *     author: Blankj
 *     editor: Warden
 *     blog  : http://blankj.com
 *     time  : 2017/06/27
 *     desc  : base about back activity
 * </pre>
 */
public abstract class BaseBackActivity extends BaseActivity {

    protected Toolbar mToolbar;
    protected FrameLayout flActivityContainer;

    @SuppressLint("ResourceType")
    @Override
    protected void setBaseView(@LayoutRes int layoutId) {
        Slidr.attach(this);
        mContentView = LayoutInflater.from(this).inflate(R.layout.activity_back, null);
        setContentView(mContentView);
        mToolbar = findViewById(R.id.toolbar);
        flActivityContainer = findViewById(R.id.activity_container);
        if (layoutId > 0) {
            flActivityContainer.addView(LayoutInflater.from(this).inflate(layoutId, flActivityContainer, false));
        }
        setSupportActionBar(mToolbar);
        getToolBar().setDisplayHomeAsUpEnabled(true);

        BarUtils.setStatusBarColor(mContext, ContextCompat.getColor(BaseApplication.getInstance(), R.color.colorPrimary), 0);
        BarUtils.addMarginTopEqualStatusBarHeight(mContentView);
    }

    /*public void setToolBarVisible(boolean visible) {
        mToolbar.setVisibility(visible? View.VISIBLE:View.GONE);
        BarUtils.setNavBarVisibility(mContext.getWindow(),visible);
    }

    public void setToolBarBG(int color) {
        mToolbar.setBackgroundColor(color);
        BarUtils.setStatusBarColor(mContext, color, 50);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    protected ActionBar getToolBar() {
        return getSupportActionBar();
    }
}
