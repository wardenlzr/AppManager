package com.lzr.warden.appuninstall.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzr.warden.appuninstall.R;
import com.lzr.warden.appuninstall.entity.AppInfo;
import com.lzr.warden.terrificlibrary.util.GlideUtils;

import java.util.List;

/**
 * Created by yubin
 * 2018/5/29 0029-下午 5:51
 */
public class AppListAdapter extends BaseQuickAdapter<AppInfo, BaseViewHolder> {
    public AppListAdapter(@Nullable List<AppInfo> data) {
        super(R.layout.app_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        ImageView imgApp = helper.getView(R.id.imgApp);
        GlideUtils.loadImg(item.getAppIcon(), imgApp);
        helper.setText(R.id.tv_label, "应用名：" + item.getAppLabel())
                .setText(R.id.tv_size, "数据大小：" + item.getSize())
                .setText(R.id.tv_pkg, "包名：" + item.getPkgName());
        helper.addOnClickListener(R.id.tv_start).addOnClickListener(R.id.tv_delete);
        /*helper.setOnItemClickListener(R.id.tv_start, (parent, view, position, id) -> {
            mContext.startActivity(item.getIntent());
        }).setOnItemClickListener(R.id.tv_delete,((parent, view, position, id) -> {
            new MaterialDialog.Builder(mContext)
                    .title(item.getAppLabel())
                    .content("确认卸载？卸载后此应用数据也会清空。")
                    .positiveText("取消")
                    .onPositive((dialog, which) -> dialog.dismiss())
                    .negativeText("确定")
                    .onNegative(((dialog, which) -> AppUtils.uninstallApp(item.getPkgName())))
                    .show();
        }));*/
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }
}
