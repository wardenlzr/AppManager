package com.lzr.warden.terrificlibrary.http;

import android.content.res.Resources;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.lzr.warden.terrificlibrary.util.LogUtils;
import com.lzr.warden.terrificlibrary.util.ToastUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Create by warden
 * 2018/7/1 16:47
 * email:wardenlzr@qq.com
 */
public abstract class MyCallBack<T> extends Callback<BaseBean<T>> {
    @Override
    public BaseBean<T> parseNetworkResponse(Response response, int id) throws Exception {
        Type entityType;

        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            //有泛型按照泛型处理
            ParameterizedType parameterizedType = (ParameterizedType) type;
            entityType = parameterizedType.getActualTypeArguments()[0];
        } else {
            //没写泛型按照obj处理
            entityType = Object.class;
        }
        return new Gson().fromJson(response.body().string(), new ParameterizedTypeImpl(BaseBean.class, new Type[]{entityType}));
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        String msg = e.getMessage();

        if (e instanceof SocketTimeoutException || e instanceof ConnectException) {
            msg = "网络连接错误!";

        } else if (e instanceof Resources.NotFoundException || TextUtils.isEmpty(e.getMessage())) {
            msg = "加载数据失败!";
        }
        if (!TextUtils.isEmpty(msg)) {
            LogUtils.e(msg);
            ToastUtils.showShort(msg+"");
        }
        fail(msg);
    }

    @Override
    public void onResponse(BaseBean<T> response, int id) {
        if (response.code == 0) {
            ok(response.data);
        } else {
            ToastUtils.showShort(response.msg + "");
        }
    }

    public abstract void ok(T bean);

    public abstract void fail(String msg);
}
