package com.bg.freemovie.ui;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.bg.freemovie.R;
import com.just.agentweb.AgentWeb;
import com.lzr.warden.terrificlibrary.base.BaseActivity;
import com.lzr.warden.terrificlibrary.base.BaseBackActivity;
import com.lzr.warden.terrificlibrary.util.AppUtils;
import com.lzr.warden.terrificlibrary.util.LogUtils;
import com.lzr.warden.terrificlibrary.util.ToastUtils;

/**
 * Create by warden
 * 2018/7/1 11:50
 * email:wardenlzr@qq.com
 */
public class WebViewActivity extends BaseActivity {

    private WebView webView;

    @Override
    public int bindLayout() {
        return R.layout.activity_webview;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        String url = getIntent().getStringExtra("url");
        AgentWeb mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(findViewById(R.id.ll_web), new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url);
        webView = mAgentWeb.getWebCreator().getWebView();
        /*String userAgent = webView.getSettings().getUserAgentString();
        if (!TextUtils.isEmpty(userAgent)) {
            webView.getSettings().setUserAgentString(userAgent
                    .replace("Android", "")
                    .replace("android", "")
                    + " cldc");
        }*/
        LogUtils.e("initView.url:" + webView.getUrl());
        findViewById(R.id.fab).setOnClickListener(view -> {
            String webViewUrl = webView.getUrl();
            LogUtils.e("webViewUrl:" + webViewUrl);
//            if (webViewUrl.contains("www.iqiyi") || webViewUrl.contains("v.qq") || webViewUrl.contains("v.youku") || webViewUrl.contains("new-play.tudou")) {
           /* if ((webViewUrl.contains("m.iqiyi") && !webViewUrl.contains("dianying"))
                    || (webViewUrl.contains("m.v.qq") && !webViewUrl.contains("movie"))
                    || webViewUrl.contains("m.youku")
                    || webViewUrl.contains("compaign.tudou"))*/
            if (webViewUrl.contains("www.iqiyi") || (webViewUrl.contains("m.v.qq") && !webViewUrl.contains("movie"))
                    || webViewUrl.contains("v.youku") || webViewUrl.contains("new-play.tudou")) {
                MoviePlayerActivity.start(mContext, webViewUrl);
            } else if (webViewUrl.contains("wardenlzr.github")) {
                AppUtils.getBonus();
            } else {
                ToastUtils.showLong("要看哪个你先点进去啊！");
            }
        });
    }

    public static void start(Activity context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);
        LogUtils.e("start.url:" + url);
        context.startActivity(intent);
        context.overridePendingTransition(com.lzr.warden.terrificlibrary.R.anim.slide_in_left, com.lzr.warden.terrificlibrary.R.anim.slide_out_left);
    }
}
