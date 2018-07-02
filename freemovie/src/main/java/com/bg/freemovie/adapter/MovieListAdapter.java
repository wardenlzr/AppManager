package com.bg.freemovie.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bg.freemovie.R;
import com.bg.freemovie.entity.MovieEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzr.warden.terrificlibrary.util.GlideUtils;

import java.util.List;

/**
 * Create by warden
 * 2018/7/1 10:35
 * email:wardenlzr@qq.com
 */
public class MovieListAdapter extends BaseQuickAdapter<MovieEntity, BaseViewHolder> {

    public MovieListAdapter(@Nullable List<MovieEntity> data) {
        super(R.layout.item_movie,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieEntity item) {
        GlideUtils.loadImg(item.moviePic, (ImageView) helper.getView(R.id.moviePic));
        helper.setText(R.id.tv_name, item.movieName)
                .setText(R.id.tv_to_star, "主演："+item.movieToStar);
    }

}
