package com.lzr.warden.terrificlibrary.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.lzr.warden.terrificlibrary.util.AppUtils;
import com.lzr.warden.terrificlibrary.util.CrashUtils;
import com.lzr.warden.terrificlibrary.util.LogUtils;
import com.lzr.warden.terrificlibrary.util.Utils;

/**
 * <pre>
 *     author: Blankj
 *     editor: Warden
 *     blog  : http://blankj.com
 *     time  : 2017/03/30
 *     desc  : base about application
 * </pre>
 */
public class BaseApplication extends Application {

    private static final String TAG = "BaseApplication";

    private static BaseApplication sInstance;

    public static BaseApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        registerActivityLifecycleCallbacks(mCallbacks);
        Utils.init(this);
        initCrash();
    }
    @SuppressLint("MissingPermission")
    private void initCrash() {
        CrashUtils.init((crashInfo, e) -> {
            LogUtils.e(crashInfo);
//                AppUtils.relaunchApp();
        });
    }
    private ActivityLifecycleCallbacks mCallbacks = new ActivityLifecycleCallbacks() {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Log.d(TAG, "onActivityCreated() called with: activity = [" + activity + "], savedInstanceState = [" + savedInstanceState + "]");
        }

        @Override
        public void onActivityStarted(Activity activity) {
            Log.d(TAG, "onActivityStarted() called with: activity = [" + activity + "]");
        }

        @Override
        public void onActivityResumed(Activity activity) {
            Log.d(TAG, "onActivityResumed() called with: activity = [" + activity + "]");
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Log.d(TAG, "onActivityPaused() called with: activity = [" + activity + "]");
        }

        @Override
        public void onActivityStopped(Activity activity) {
            Log.d(TAG, "onActivityStopped() called with: activity = [" + activity + "]");
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            Log.d(TAG, "onActivitySaveInstanceState() called with: activity = [" + activity + "], outState = [" + outState + "]");
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            Log.d(TAG, "onActivityDestroyed() called with: activity = [" + activity + "]");
        }
    };
}
