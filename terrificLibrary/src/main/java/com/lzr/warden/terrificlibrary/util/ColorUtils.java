package com.lzr.warden.terrificlibrary.util;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by yubin
 * 2018/5/30 0030-下午 5:50
 */
public class ColorUtils {

    private ColorUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取随机颜色
     * @return
     */
    public static int getRandomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return Color.rgb(r, g, b);
    }
}
