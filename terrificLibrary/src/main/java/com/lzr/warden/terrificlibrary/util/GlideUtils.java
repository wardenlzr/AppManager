package com.lzr.warden.terrificlibrary.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.lzr.warden.terrificlibrary.R;

import java.security.MessageDigest;

/**
 * Created by yubin
 * 2018/5/30 0030-上午 8:55
 */
public class GlideUtils {

    private static Context context = Utils.getApp();
    private static int placeholder = R.drawable.empty;

    /**
     * 设置统一的默认占位图
     *
     * @param img
     */
    public static void setPlaceholder(int img) {
        placeholder = img;
    }

    /**
     * 加载图片
     *
     * @param url
     * @param imageView
     */
    public static void loadImg(String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(placeholder))
                .into(imageView);
    }

    public static void loadImg(Drawable drawable, ImageView imageView) {
        Glide.with(context)
                .load(drawable)
                .apply(new RequestOptions()
                        .placeholder(placeholder))
                .into(imageView);
    }

    /**
     * 加载图片，单独设置占位图
     *
     * @param url
     * @param placeholder
     * @param imageView
     */
    public static void loadImg(String url, int placeholder, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(placeholder))
                .into(imageView);
    }

    public static void loadImg(Drawable drawable, int placeholder, ImageView imageView) {
        Glide.with(context)
                .load(drawable)
                .apply(new RequestOptions()
                        .placeholder(placeholder))
                .into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param url
     * @param imageView
     */
    public static void loadCircleImg(String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .transform(new GlideCircleTransform())
                        .placeholder(placeholder))
                .into(imageView);
    }

    public static void loadCircleImg(Drawable drawable, ImageView imageView) {
        Glide.with(context)
                .load(drawable)
                .apply(new RequestOptions()
                        .transform(new GlideCircleTransform())
                        .placeholder(placeholder))
                .into(imageView);
    }

    /**
     * 加载圆形图片，单独设置占位图
     *
     * @param url
     * @param placeholder
     * @param imageView
     */
    public static void loadCircleImg(String url, int placeholder, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .transform(new GlideCircleTransform())
                        .placeholder(placeholder))
                .into(imageView);
    }

    public static void loadCircleImg(Drawable drawable, int placeholder, ImageView imageView) {
        Glide.with(context)
                .load(drawable)
                .apply(new RequestOptions()
                        .transform(new GlideCircleTransform())
                        .placeholder(placeholder))
                .into(imageView);
    }


    /**
     * Created by qly on 2016/6/22.
     * 将图片转化为圆形
     */
    static class GlideCircleTransform extends BitmapTransformation {

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        }
    }
}
