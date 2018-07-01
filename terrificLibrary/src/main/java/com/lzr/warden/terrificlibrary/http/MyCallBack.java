package com.lzr.warden.terrificlibrary.http;

import com.lzr.warden.terrificlibrary.util.JsonUtil;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Create by warden
 * 2018/7/1 16:47
 * email:wardenlzr@qq.com
 */
public abstract class MyCallBack<T> extends Callback<T> {
    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        return JsonUtil.toBean(response.body().string(), (Class<T>) Object.class);
    }
    @Override
    public void onError(Call call, Exception e, int id)
    {

    }

    @Override
    public void onResponse(T response, int id)
    {

    }
}
