package com.lzr.warden.appuninstall.task;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.lzr.warden.appuninstall.entity.AppInfo;
import com.lzr.warden.appuninstall.ui.AppListActivity;
import com.lzr.warden.terrificlibrary.util.NumberUtils;
import com.lzr.warden.terrificlibrary.util.ThreadUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yubin
 * 2018/6/1 0001-上午 10:51
 */
public class QueryAppTask extends ThreadUtils.SimpleTask<List<AppInfo>> {

    private AppListActivity mContext;
    private PackageManager pm;
    private int filter;

    public QueryAppTask(AppListActivity context, int f) {
        mContext = context;
        filter = f;
    }

    @Override
    public List<AppInfo> doInBackground() throws Throwable {
        return queryFilterAppInfo(filter);
    }

    @Override
    public void onSuccess(List<AppInfo> result) {
        mContext.setAppAdapter(result);
    }

    // 根据查询条件，查询特定的ApplicationInfo
    private List<AppInfo> queryFilterAppInfo(int filter) {
        pm = mContext.getPackageManager();
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
        Intent intent = pm.getLaunchIntentForPackage(app.packageName);
        appInfo.setIntent(intent);
        //获取应用数据大小
        long length = new File(app.sourceDir).length();
        //转换为 M
        float size = length * 1f / 1024 / 1024;
        String allSize = NumberUtils.get2Decimal(size) + "M";
        appInfo.setSize(allSize);
        return appInfo;
    }
}
