package com.bg.freemovie.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.bg.freemovie.R;
import com.just.agentweb.AgentWeb;
import com.lzr.warden.terrificlibrary.base.BaseActivity;
import com.lzr.warden.terrificlibrary.base.BaseApplication;
import com.lzr.warden.terrificlibrary.base.BaseBackActivity;
import com.lzr.warden.terrificlibrary.util.BarUtils;
import com.lzr.warden.terrificlibrary.util.LogUtils;
import com.lzr.warden.terrificlibrary.util.ToastUtils;

/**
 * Create by warden
 * 2018/7/1 11:50
 * email:wardenlzr@qq.com
 */
public class MoviePlayerActivity extends BaseActivity {
    private String BASEURL = "http://yun.baiyug.cn/vip/?url=";

    @Override
    public int bindLayout() {
        return R.layout.activity_movie_player;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        BarUtils.setStatusBarColor(mContext, Color.BLACK, 0);
        String url = getIntent().getStringExtra("url");

        LogUtils.e("MoviePlayerActivity.initView:" + url);
        AgentWeb mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(findViewById(R.id.ll_movie_player), new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(BASEURL + url);
        WebView webView = mAgentWeb.getWebCreator().getWebView();
        String userAgent = webView.getSettings().getUserAgentString();
        if (!TextUtils.isEmpty(userAgent)) {
            webView.getSettings().setUserAgentString(userAgent
                    .replace("Android", "")
                    .replace("android", "")
                    + " cldc");
        }
    }

    public static void start(Activity context, String url) {
        Intent intent = new Intent(context, MoviePlayerActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
        context.overridePendingTransition(com.lzr.warden.terrificlibrary.R.anim.slide_in_left, com.lzr.warden.terrificlibrary.R.anim.slide_out_left);
    }
}
