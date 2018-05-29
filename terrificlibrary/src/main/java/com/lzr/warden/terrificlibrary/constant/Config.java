package com.lzr.warden.terrificlibrary.constant;

import android.os.Environment;

import com.lzr.warden.terrificlibrary.base.BaseApplication;

import java.io.File;

/**
 * <pre>
 *     author: Blankj
 *     editor: Warden
 *     blog  : http://blankj.com
 *     time  : 2017/05/10
 *     desc  : config about constants
 * </pre>
 */
public class Config {

    public static final String FILE_SEP = System.getProperty("file.separator");
    public static final String TEST_PKG = "com.blankj.testinstall";
    public static final String GITHUB   = "https://github.com/wardenlzr";
    public static final String BLOG     = "https://wardenlzr.github.io/";
    public static final String CACHE_PATH;
    public static final String TEST_APK_PATH;

    static {
        File cacheDir = BaseApplication.getInstance().getExternalCacheDir();
        if (cacheDir != null) {
            CACHE_PATH = cacheDir.getAbsolutePath();
        } else {
            CACHE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        TEST_APK_PATH = CACHE_PATH + FILE_SEP + "test_install.apk";

    }
}
