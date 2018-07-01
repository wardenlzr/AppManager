package com.bg.freemovie;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.bg.freemovie.adapter.MovieListAdapter;
import com.bg.freemovie.entity.MovieEntity;
import com.bg.freemovie.ui.MoviePlayerActivty;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzr.warden.terrificlibrary.base.BaseDrawerActivity;
import com.lzr.warden.terrificlibrary.util.BarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by warden
 * 2018/7/1 10:35
 * email:wardenlzr@qq.com
 */
public class MainActivity extends BaseDrawerActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

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
        setAdapter();

    }

    private void setAdapter() {
        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        mRecyclerView.setLayoutManager(manager);
        List<MovieEntity> list = new ArrayList<>();
        MovieEntity entity = new MovieEntity();
        entity.movieName = "后来的我们";
        entity.moviePic = "http://pic2.qiyipic.com/image/20180621/15/b9/v_114345562_m_601_m9_180_236.jpg";
        entity.movieUrl = "http://www.iqiyi.com/v_19rrf0jhms.html";
        entity.movieToStar = "井柏然 周冬雨";
        list.add(entity);
        MovieEntity entity1 = new MovieEntity();
        entity1.movieName = "红海行动";
        entity1.moviePic = "http://pic4.qiyipic.com/image/20180428/84/a8/v_112882553_m_601_m4_180_236.jpg";
        entity1.movieUrl = "http://www.iqiyi.com/v_19rr7plwdc.html#vfrm=2-4-0-1";
        entity1.movieToStar = "张译 黄景瑜";
        list.add(entity1);
        MovieListAdapter adapter = new MovieListAdapter(list);
        adapter.setOnItemClickListener((adapter1, view, position) -> MoviePlayerActivty.start(mContext,list.get(position).movieUrl));
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
