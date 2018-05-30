package com.lzr.warden.appuninstall.ui;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzr.warden.appuninstall.R;
import com.lzr.warden.appuninstall.adapter.AppListAdapter;
import com.lzr.warden.appuninstall.entity.AppInfo;
import com.lzr.warden.terrificlibrary.base.BaseBackActivity;
import com.lzr.warden.terrificlibrary.util.AppUtils;
import com.lzr.warden.terrificlibrary.util.ColorUtils;
import com.lzr.warden.terrificlibrary.util.ThreadUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppListActivity extends BaseBackActivity {


    private RecyclerView mRecyclerView;
    private int filter = 0;
    private String title = "";
    private PackageManager pm;

    @Override
    public int bindLayout() {
        return R.layout.activity_app_list;
    }
    ThreadUtils.SimpleTask mSimpleTask = new ThreadUtils.SimpleTask<List<AppInfo>>() {
        @Override
        public List<AppInfo> doInBackground() {
            return queryFilterAppInfo(filter);
        }

        @Override
        public void onSuccess(List<AppInfo> result) {
            setAppAdapter(result);
            stopPd();
        }

    };
    @Override
    public void initData(@Nullable Bundle bundle) {
        filter = bundle.getInt("filter");
        title = bundle.getString("title");
        startPd();
        ThreadUtils.executeBySingle(mSimpleTask);
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        getToolBar().setTitle(title);
        setToolBarBG(ColorUtils.getRandomColor());
        mRecyclerView = findViewById(R.id.rvAppList);
        mRecyclerView.setHasFixedSize(true);

    }

    private void setAppAdapter(List<AppInfo> appInfos) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        AppListAdapter appListAdapter = new AppListAdapter(appInfos);
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
            switch (view.getId()){
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
                            .onNegative(((dialog, which) -> AppUtils.uninstallApp(item.getPkgName())))
                            .show();
                    break;
            }
        });
        mRecyclerView.setAdapter(appListAdapter);
        appListAdapter.setEmptyView(R.layout.view_empty, mRecyclerView);
        stopPd();
    }

    // 根据查询条件，查询特定的ApplicationInfo
    private List<AppInfo> queryFilterAppInfo(int filter) {
        pm = this.getPackageManager();
        // 查询所有已经安装的应用程序
        List<ApplicationInfo> listAppcations = pm
                .getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        Collections.sort(listAppcations,
                new ApplicationInfo.DisplayNameComparator(pm));// 排序
        List<AppInfo> appInfos = new ArrayList<AppInfo>(); // 保存过滤查到的AppInfo
        // 根据条件来过滤
        switch (filter) {
            case 0: // 所有应用程序
                appInfos.clear();
                for (ApplicationInfo app : listAppcations) {
                    appInfos.add(getAppInfo(app));
                }
                return appInfos;
            case 1: // 系统程序
                appInfos.clear();
                for (ApplicationInfo app : listAppcations) {
                    if ((app.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                        appInfos.add(getAppInfo(app));
                    }
                }
                return appInfos;
            case 2: // 第三方应用程序
                appInfos.clear();
                for (ApplicationInfo app : listAppcations) {
                    if ((app.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                        appInfos.add(getAppInfo(app));
                    }
                }
                break;
            case 3: // 安装在SDCard的应用程序
                appInfos.clear();
                for (ApplicationInfo app : listAppcations) {
                    if ((app.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
                        appInfos.add(getAppInfo(app));
                    }
                }
                return appInfos;
            default:
                return null;
        }
        return appInfos;
    }

    // 构造一个AppInfo对象 ，并赋值
    private AppInfo getAppInfo(ApplicationInfo app) {
        AppInfo appInfo = new AppInfo();
        appInfo.setAppLabel((String) app.loadLabel(pm));
        appInfo.setAppIcon(app.loadIcon(pm));
        appInfo.setPkgName(app.packageName);
        PackageManager manager = getPackageManager();
        Intent intent = manager.getLaunchIntentForPackage(app.packageName);
        appInfo.setIntent(intent);
        //获取应用数据大小
        long length = new File(app.sourceDir).length();
        //转换为 M
        float size = length * 1f / 1024 / 1024;
        String allSize = size + "M";
        appInfo.setSize(allSize);
        return appInfo;
    }
}
