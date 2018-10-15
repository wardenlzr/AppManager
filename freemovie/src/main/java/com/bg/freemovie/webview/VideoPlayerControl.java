package com.bg.freemovie.webview;

import android.webkit.WebView;

/**
 * Author: YuJunKui
 * Time:2018/10/14 11:36 PM
 * Tips: 基础视频播放view  统一出口入口
 */
public interface VideoPlayerControl {

    /**
     * 参数我tm 也不明确  有可能只需要一个webview
     * 为了对页面的按钮数据 进行修改 以至于引导用户播放
     */
    void  hookPage(WebView webView);

    /**
     * @param url 当前地址可以播放
     * @return
     */
    boolean isStartPlayer(String url);

}