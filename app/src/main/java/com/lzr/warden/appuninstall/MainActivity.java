package com.lzr.warden.appuninstall;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.lzr.warden.appuninstall.adapter.HomeAdapter;
import com.lzr.warden.appuninstall.entity.HomeItem;
import com.lzr.warden.appuninstall.ui.AppListActivity;
import com.lzr.warden.terrificlibrary.base.BaseDrawerActivity;
import com.lzr.warden.terrificlibrary.util.BarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yubin
 * 2018/5/29 0029-下午 1:53
 */
public class MainActivity extends BaseDrawerActivity {

    private RecyclerView mRecyclerView;
    private String[] TITLE = {"所有", "系统", "第三方", "SDCard"};
    private String[] IMG = {"apps", "app", "yixiazai", "app1"};
    private int[] FILTER = {0, 1, 2, 3};

    private HomeAdapter homeAdapter;
    private List<HomeItem> mDataList;
    private Toolbar mToolbar;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        mToolbar = findViewById(R.id.toolbar);
        View fakeStatusBar = findViewById(R.id.fake_status_bar);
        CollapsingToolbarLayout ctl = findViewById(R.id.ctl);
        ctl.setExpandedTitleColor(Color.parseColor("#00FFFFFF"));
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                rootLayout,
                mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        rootLayout.addDrawerListener(toggle);
        toggle.syncState();

        BarUtils.setStatusBarAlpha4Drawer(this, rootLayout, fakeStatusBar, 0, false);
        BarUtils.addMarginTopEqualStatusBarHeight(mToolbar);

        mRecyclerView = findViewById(R.id.rv_list);
        mDataList = new ArrayList<>();
        for (int i = 0; i < TITLE.length; i++) {
            HomeItem item = new HomeItem();
            item.setTitle(TITLE[i]);
            item.setImageResource(IMG[i]);
            item.setFilter(FILTER[i]);
            mDataList.add(item);
        }
        setHomeAdapter(mDataList);
    }
    public void setToolBarBG(int color){
        mToolbar.setBackgroundColor(color);
        BarUtils.setStatusBarColor(mContext, color, 0);
    }
    private void setHomeAdapter(final List<HomeItem> mDataList) {
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        homeAdapter = new HomeAdapter(mDataList);
        homeAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            int filter = mDataList.get(position).getFilter();
            bundle.putInt("filter", filter);
            String title;
            switch (filter){
                case 0:
                    title = "所有应用";
                    break;
                case 1:
                    title = "系统应用";
                    break;
                case 2:
                    title = "第三方应用";
                    break;
                case 3:
                    title = "SDCard应用";
                    break;
                default:
                    title = "所有应用";
                    break;
            }
            bundle.putString("title",title);
            startActivity(AppListActivity.class, bundle);
        });
        mRecyclerView.setAdapter(homeAdapter);
//        homeAdapter.setEmptyView(R.layout.view_empty);
    }



    @Override
    public void onBackPressed() {
        RecyclerView.Adapter adapter = mRecyclerView.getAdapter();
        if (adapter instanceof HomeAdapter) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
        } else setHomeAdapter(mDataList);
    }

    private long exitTime = 0;
    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
//                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/
    /*@TargetApi(Build.VERSION_CODES.KITKAT)
    public static void getPkgSize(final Context context, String pkgName, final AppInfo appInfo) {
        // getPackageSizeInfo是PackageManager中的一个private方法，所以需要通过反射的机制来调用
        Method method;
        try {
            method = PackageManager.class.getMethod("getPackageSizeInfo",
                    new Class[]{String.class, IPackageStatsObserver.class});
            // 调用 getPackageSizeInfo 方法，需要两个参数：1、需要检测的应用包名；2、回调
            method.invoke(context.getPackageManager(), pkgName,
                    new IPackageStatsObserver.Stub() {
                        @Override
                        public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) throws RemoteException {
                            if (succeeded && pStats != null) {
                                synchronized (AppInfo.class) {
//                                    appInfo.setCatchSize(pStats.cacheSize);//缓存大小
//                                    appInfo.setDataSize(pStats.dataSize);  //数据大小
//                                    appInfo.setCodeSize(pStats.codeSize);  //应用大小
                                    appInfo.setSize(pStats.cacheSize + pStats.codeSize + pStats.dataSize + "M");//应用的总大小
                                }
                            }
                        }
                    });
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }*/
}
