package com.lzr.warden.appuninstall.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzr.warden.appuninstall.R;
import com.lzr.warden.appuninstall.entity.HomeItem;
import com.lzr.warden.terrificlibrary.iconfont.I;

import java.util.List;

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
    }
}
