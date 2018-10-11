package com.bg.freemovie;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.bg.freemovie.adapter.MovieListAdapter;
import com.bg.freemovie.entity.BaseBYGEntity;
import com.bg.freemovie.entity.MovieEntity;
import com.bg.freemovie.ui.WebViewActivity;
import com.bg.freemovie.utils.Constants;
import com.bg.freemovie.ui.MoviePlayerActivity;
import com.lzr.warden.terrificlibrary.base.BaseDrawerActivity;
import com.lzr.warden.terrificlibrary.http.HttpManager;
import com.lzr.warden.terrificlibrary.http.MyCallBack;
import com.lzr.warden.terrificlibrary.util.AppUtils;
import com.lzr.warden.terrificlibrary.util.BarUtils;
import com.lzr.warden.terrificlibrary.util.JsonUtil;
import com.lzr.warden.terrificlibrary.util.ToastUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Arrays;
import java.util.List;

import okhttp3.Call;

/**
 * Create by warden
 * 2018/7/1 10:35
 * email:wardenlzr@qq.com
 */
public class MainActivity extends BaseDrawerActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private MovieListAdapter adapter;

    @Override
    public int bindLayout() {
        setDrawer(R.layout.activity_movie_drawer);
        setItemListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_aiqiyi:
                    WebViewActivity.start(mContext,Constants.ITEM_IQIYI);
                    break;
                case R.id.action_tencent:
                    WebViewActivity.start(mContext,Constants.ITEM_TENCENT);
                    break;
                case R.id.action_youku:
                    WebViewActivity.start(mContext,Constants.ITEM_YOUKU);
                    break;
                case R.id.action_tudou:
                    WebViewActivity.start(mContext,Constants.ITEM_TUDOU);
                    break;
                case R.id.action_blog:
                    WebViewActivity.start(mContext,Constants.ITEM_BLOG);
                    break;
                case R.id.action_get_bonus:
                    AppUtils.getBonus();
                    break;
            }
            return false;
        });
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        initToolBar();
        getBaseBYG();
        findViewById(R.id.fab_tencent).setOnClickListener(view -> WebViewActivity.start(mContext, Constants.ITEM_TENCENT));
    }

    //获取基本信息
    private void getBaseBYG() {
        HttpManager.getAsync(Constants.GETBASEBYG, new MyCallBack<BaseBYGEntity>() {
            @Override
            public void ok(BaseBYGEntity result) {
                if (result.versionCode == AppUtils.getAppVersionCode()) {
                    Constants.BASEURL = result.byg;
                    getMovies();
                }else {
                    ToastUtils.showShort(R.string.updateTips);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }

    //获取电影列表
    private void getMovies() {
        mRecyclerView = findViewById(R.id.rv_list);
        HttpManager.getAsync(Constants.MOVIES, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                MovieEntity[] list = JsonUtil.toBean(response, MovieEntity[].class);
                ToastUtils.showLong("当前内置了"+list.length + "部会员电影，想免费看更多会员电影请联系作者");
                setAdapter(list);
            }
        });
    }

    private void initToolBar() {
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
    }

    private void setAdapter(MovieEntity[] arr) {
        List<MovieEntity> list = Arrays.asList(arr);
        Arrays.asList(arr);
        if (adapter != null) {
            adapter.setNewData(list);
            return;
        }
        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        mRecyclerView.setLayoutManager(manager);
        adapter = new MovieListAdapter(list);
        adapter.setOnItemClickListener((adapter1, view, position) -> MoviePlayerActivity.start(mContext,list.get(position).href));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
    private long exitTime = 0;
}
