package com.lzr.warden.terrificlibrary.http;

import com.lzr.warden.terrificlibrary.util.JsonUtil;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Create by warden
 * 2018/7/1 16:47
 * email:wardenlzr@qq.com
 */
public abstract class MyListCallBack<T> extends Callback<List<T>> {
    @Override
    public List<T> parseNetworkResponse(Response response, int id) throws Exception {
        return JsonUtil.toList(response.body().string(), (Class<T>) Object[].class);
    }
    @Override
    public abstract void onError(Call call, Exception e, int id);

    @Override
    public abstract void onResponse(List<T> list, int id);
}
