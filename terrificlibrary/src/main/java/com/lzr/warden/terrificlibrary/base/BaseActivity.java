package com.lzr.warden.terrificlibrary.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.lzr.warden.terrificlibrary.R;
import com.lzr.warden.terrificlibrary.util.ToastUtils;

/**
 * <pre>
 *     author: Blankj
 *     editor: Warden
 *     blog  : http://blankj.com
 *     time  : 2016/10/24
 *     desc  : base about activity
 * </pre>
 */
public abstract class BaseActivity extends AppCompatActivity
        implements IBaseView {

    protected View mContentView;
    protected Activity mContext;

    /**
     * 上次点击时间
     */
    private long lastClick = 0;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mContext = this;
            pd = new ProgressDialog(mContext);
            try {
                Bundle bundle = getIntent().getExtras();
                initData(bundle);
            } catch (Exception e) {
                e.printStackTrace();
                shortToast("参数传递出错了");
            }
            setBaseView(bindLayout());
            initView(savedInstanceState, mContentView);
        } catch (Exception e) {
            e.printStackTrace();
            shortToast(getString(R.string.init_exception) + e.getMessage());
        }
    }

    public void initData(@Nullable final Bundle bundle) {

    }

    public void startActivity(Class clazz) {
        startActivity(clazz, null);
    }

    public void startActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(mContext, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public void startActivityForResult(Class clazz, int requestCode) {
        startActivityForResult(clazz, null, requestCode);
    }

    public void startActivityForResult(Class clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(mContext, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public void startPd() {
        if (pd != null) {
            pd.setMessage("加载中...");
            pd.setCanceledOnTouchOutside(false);
            pd.show();

            //设置超时自动消失
            new Handler().postDelayed(() -> {
                //取消加载框
                if (dismissProgressDialog()) {
                    shortToast("啥玩意了？15秒还没好...");
                }
            }, 15000L);//超时时间
        }
    }

    public void stopPd() {
        if (pd != null) {
            pd.cancel();
        }
    }

    public Boolean dismissProgressDialog() {
        if (pd != null) {
            if (pd.isShowing()) {
                pd.dismiss();
                return true;//取消成功
            }
        }
        return false;//已经取消过了，不需要取消
    }

    @SuppressLint("ResourceType")
    protected void setBaseView(@LayoutRes int layoutId) {
        if (layoutId <= 0) return;
        setContentView(mContentView = LayoutInflater.from(this).inflate(layoutId, null));
    }

    /**
     * 判断是否快速点击
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    private boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 200) {
            lastClick = now;
            return false;
        }
        return true;
    }

    public void shortToast(String s) {
        ToastUtils.showShort(s);
    }

}
