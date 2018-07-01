package com.bg.freemovie.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.bg.freemovie.R;
import com.just.agentweb.AgentWeb;
import com.lzr.warden.terrificlibrary.base.BaseBackActivity;
import com.lzr.warden.terrificlibrary.util.ColorUtils;
import com.lzr.warden.terrificlibrary.util.ToastUtils;

/**
 * Create by warden
 * 2018/7/1 11:50
 * email:wardenlzr@qq.com
 */
public class MoviePlayerActivty extends BaseBackActivity {
    private String BASEURL = "http://yun.baiyug.cn/vip/?url=";

//    private IjkPlayerView mPlayerView;

    @Override
    public int bindLayout() {
        return R.layout.activity_movie_player;
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
        setToolBarVisible(false);
        setToolBarBG(Color.BLACK);
        String url = getIntent().getStringExtra("url");

        ToastUtils.showLong("这广告小编正在想办法去掉...");
//        WebView webView = findViewById(R.id.webview);
//        webView.loadUrl(BASEURL+url);
        AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) contentView, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(BASEURL +url);
//        initPlayerView();
    }
    public static void start(Activity context, String url){
        Intent intent = new Intent(context, MoviePlayerActivty.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
        context.overridePendingTransition(com.lzr.warden.terrificlibrary.R.anim.slide_in_left, com.lzr.warden.terrificlibrary.R.anim.slide_out_left);
    }
    /*private void initPlayerView() {
        mPlayerView = findViewById(R.id.player_view);

        mPlayerView.init()
                .setTitle(Title)
                .alwaysFullScreen()			// keep fullscreen
                .setVideoPath(url)
                .start();
    }*/


    /*@Override
    protected void onResume() {
        super.onResume();
        mPlayerView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPlayerView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayerView.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mPlayerView.configurationChanged(newConfig);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mPlayerView.handleVolumeKey(keyCode)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (mPlayerView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }*/
}
