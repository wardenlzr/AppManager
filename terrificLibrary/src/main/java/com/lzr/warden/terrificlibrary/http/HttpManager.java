package com.lzr.warden.terrificlibrary.http;

import com.lzr.warden.terrificlibrary.util.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * Create by warden
 * 2018/7/1 13:54
 * email:wardenlzr@qq.com
 */
public class HttpManager {

    /**
     * 异步的Get请求，返回结果字符串
     * @param url
     * @param callback
     */
    public static void getAsyncAsString(String url, StringCallback callback) {
        log("getAsyncAsString:"+url);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .readTimeOut(30_000L)
                .connTimeOut(30_000L)
                .readTimeOut(30_000L)
                .execute(callback);
    }

    /**
     * 异步的Get请求
     * 使用url作为唯一标识
     * @param url
     * @param callback
     */
    public static void getAsync(String url, Callback callback) {
        log("getAsync:"+url);
        OkHttpUtils
                .get()
                .url(url)
                .tag(url)
                .build()
                .readTimeOut(30_000L)
                .connTimeOut(30_000L)
                .readTimeOut(30_000L)
                .execute(callback);
    }

    /**
     * 异步的Post请求
     * @param url
     * @param params
     * @param callback
     */
    public static void postAsync(String url, Map<String, String> params, Callback callback) {
        log("postAsync:"+url);
        log(params.toString());
        OkHttpUtils
                .post()
                .url(url)
                .params(params)
                .build()
                .readTimeOut(30_000L)
                .connTimeOut(30_000L)
                .readTimeOut(30_000L)
                .execute(callback);
    }

    /**
     * 异步下载文件
     * @param url
     * @param callback
     */
    public static void downloadAsync(String url, FileCallBack callback) {
        log(url);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(callback);
    }

    /**
     * 文件上传
     * @param url
     * @param callback
     */
    public static void postFile(String url,String fileName, File file, StringCallback callback) {
        log(url);
        OkHttpUtils
                .post()
                .addFile("mFile", fileName, file)
                .url(url)
                .build()
                .execute(callback);
    }

    /**
     * 文件上传
     * @param url
     * @param callback
     */
    public static void postFileWithSHA1(String url, String fileName, File file, StringCallback callback) {
        log(url);
        OkHttpUtils
                .post()
                .addFile("mFile", fileName, file)
                .url(url)
                .build()
                .execute(callback);
    }

    private static void log(String msg) {
        if (true) {
            LogUtils.e(msg);
        }
    }
    public static String getFileName(String path) {
        int separatorIndex = path.lastIndexOf("/");
        return (separatorIndex < 0) ? path : path.substring(separatorIndex + 1, path.length());
    }
    public static String getFiletype(String path) {
        int separatorIndex = path.lastIndexOf(".");
        return (separatorIndex < 0) ? path : path.substring(separatorIndex + 1, path.length());
    }
    /**
     * 取消请求
     * @param tag
     */
    public static void cancleTag(String tag) {
        OkHttpUtils.getInstance().cancelTag(tag);
    }
    /**
     * 取消所有请求
     */
    public static void cancelAll() {
        try {
            List<Call> calls = new ArrayList<Call>();
            OkHttpClient mOkHttpClient = OkHttpUtils.getInstance().getOkHttpClient();
            calls.addAll(mOkHttpClient.dispatcher().queuedCalls());
            calls.addAll(mOkHttpClient.dispatcher().runningCalls());
            for (Call call : calls){
                if (call != null) {
                    call.cancel();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*public void cancelAll(){
        try {
            List<Call> calls = new ArrayList<Call>();
            OkHttpClient mOkHttpClient = OkHttpUtils.getInstance().getOkHttpClient();
            calls.addAll(mOkHttpClient.dispatcher().queuedCalls());
            calls.addAll(mOkHttpClient.dispatcher().runningCalls());
            for (Call call : calls){
                if (call != null) {
                    call.cancel();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
