package com.lzr.warden.appuninstall.ui;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzr.warden.appuninstall.R;
import com.lzr.warden.appuninstall.adapter.AppListAdapter;
import com.lzr.warden.appuninstall.entity.AppInfo;
import com.lzr.warden.appuninstall.task.QueryAppTask;
import com.lzr.warden.terrificlibrary.base.BaseBackActivity;
import com.lzr.warden.terrificlibrary.util.AppUtils;
import com.lzr.warden.terrificlibrary.util.ColorUtils;
import com.lzr.warden.terrificlibrary.util.ThreadUtils;

import java.util.List;

public class AppListActivity extends BaseBackActivity {


    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout srl;
    private int filter = 0;
    private String title = "";
    private AppListAdapter appListAdapter;

    @Override
    public int bindLayout() {
        return R.layout.activity_app_list;
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        filter = bundle.getInt("filter");
        title = bundle.getString("title");
        getAppList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99) {
            getAppList();
        }
    }

    private void getAppList() {
        if (appListAdapter != null) {
            appListAdapter.clear();
        }
        startPd();
        ThreadUtils.executeBySingle(new QueryAppTask(this, filter));
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        getToolBar().setTitle(title);
        setToolBarBG(ColorUtils.getRandomColor());
        mRecyclerView = findViewById(R.id.rvAppList);
        srl = findViewById(R.id.srl);
        srl.setColorSchemeColors(ColorUtils.getRandomColor());
//        ImageView imageView = findViewById(R.id.iv);
//        GlideUtils.loadCircleImg("https://avatars2.githubusercontent.com/u/16572346?s=400&u=3d68b0771b27b7f7bfd98a2d0f99ab2b1f5a2817&v=4\n",imageView);
        mRecyclerView.setHasFixedSize(true);
        srl.setOnRefreshListener(() -> getAppList());
    }

    public void setAppAdapter(List<AppInfo> appInfos) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        appListAdapter = new AppListAdapter(appInfos);
        appListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        appListAdapter.setNotDoAnimationCount(3);
        appListAdapter.setOnItemClickListener((adapter, view, position) -> {
            AppInfo item = appInfos.get(position);
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            // 将文本内容放到系统剪贴板里。
            assert cm != null;
            cm.setText(item.getPkgName());
            shortToast("包名已复制到粘贴板");
        });
        appListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            AppInfo item = appInfos.get(position);
            switch (view.getId()) {
                case R.id.tv_start:
                    Intent intent = item.getIntent();
                    if (intent == null) {
                        shortToast("抱歉，此应用小编打不开");
                        break;
                    }
                    startActivity(item.getIntent());
                    break;
                case R.id.tv_delete:
                    new MaterialDialog.Builder(mContext)
                            .title(item.getAppLabel())
                            .content("确认卸载？卸载后此应用数据也会清空。")
                            .positiveText("取消")
                            .onPositive((dialog, which) -> dialog.dismiss())
                            .negativeText("确定")
                            .onNegative(((dialog, which) -> AppUtils.uninstallApp(mContext, item.getPkgName(), 99)))
                            .show();
                    break;
            }
        });
        mRecyclerView.setAdapter(appListAdapter);
        appListAdapter.setEmptyView(R.layout.view_empty, mRecyclerView);
        stopPd();
        srl.setRefreshing(false);
    }

}
