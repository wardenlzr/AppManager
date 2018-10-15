package com.bg.freemovie.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.bg.freemovie.R;
import com.bg.freemovie.utils.Constants;
import com.bg.freemovie.utils.ParseWebUrlHelper;
import com.just.agentweb.AgentWeb;
import com.lzr.warden.terrificlibrary.base.BaseActivity;
import com.lzr.warden.terrificlibrary.util.BarUtils;
import com.lzr.warden.terrificlibrary.util.LogUtils;

/**
 * Create by warden
 * 2018/7/1 11:50
 * email:wardenlzr@qq.com
 */
public class MoviePlayerActivity extends BaseActivity {
    //    private String BASEURL = "http://yun.baiyug.cn/vip/?url=";
//    private String BASEURL = "http://app.baiyug.cn:2019/vip/?url=";

    @Override
    public int bindLayout() {
        return R.layout.activity_movie_player;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        BarUtils.setStatusBarColor(mContext, Color.BLACK, 0);
        String url = getIntent().getStringExtra("url");

        LogUtils.e("MoviePlayerActivity.initView:" + url);
        String analysisUrl = Constants.BASEURL + url;
        AgentWeb mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(findViewById(R.id.ll_movie_player), new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(analysisUrl);
        WebView webView = mAgentWeb.getWebCreator().getWebView();
        String userAgent = webView.getSettings().getUserAgentString();
        //去广告
        if (!TextUtils.isEmpty(userAgent)) {
            webView.getSettings().setUserAgentString(userAgent
                    .replace("Android", "")
                    .replace("android", "")
                    + " cldc");
        }
        //初始化
        ParseWebUrlHelper parseWebUrlHelper = ParseWebUrlHelper.getInstance().init(mContext, analysisUrl);
        //解析网页中视频
        parseWebUrlHelper.setOnParseListener(new ParseWebUrlHelper.OnParseWebUrlListener() {
            @Override
            public void onFindUrl(String url) {
                LogUtils.e("webUrl:" + url);
                //*****处理代码
            }

            @Override
            public void onError(String errorMsg) {
                LogUtils.e(errorMsg);
            }
        });
    }

    public static void start(Activity context, String url) {
        Intent intent = new Intent(context, MoviePlayerActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
        context.overridePendingTransition(com.lzr.warden.terrificlibrary.R.anim.slide_in_left, com.lzr.warden.terrificlibrary.R.anim.slide_out_left);
    }
}
