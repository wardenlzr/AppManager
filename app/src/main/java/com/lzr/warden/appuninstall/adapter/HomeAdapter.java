package com.lzr.warden.appuninstall.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzr.warden.appuninstall.R;
import com.lzr.warden.appuninstall.entity.HomeItem;
import com.lzr.warden.terrificlibrary.iconfont.I;
import com.lzr.warden.terrificlibrary.util.ColorUtils;

import java.util.List;
import java.util.Random;

/**
 * Created by yubin
 * 2018/5/29 0029-下午 3:53
 */
public class HomeAdapter extends BaseQuickAdapter<HomeItem, BaseViewHolder> {
    public HomeAdapter(@Nullable List<HomeItem> data) {
        super(R.layout.home_item_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeItem item) {
        helper.setText(R.id.text, item.getTitle())
                .setText(R.id.icon, I.ICON_MAP.get(item.getImageResource()));
        helper.setTextColor(R.id.icon, ColorUtils.getRandomColor());
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }
}
