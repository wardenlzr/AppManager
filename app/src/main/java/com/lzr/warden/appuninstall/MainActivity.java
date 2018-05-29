package com.lzr.warden.appuninstall;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzr.warden.appuninstall.adapter.HomeAdapter;
import com.lzr.warden.appuninstall.entity.HomeItem;
import com.lzr.warden.terrificlibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yubin
 * 2018/5/29 0029-下午 1:53
 */
public class MainActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private String[] TITLE = {"所有", "第三方", "系统", "SDCard"};
    private String[] IMG = {"apps", "yixiazai", "app", "app1"};
    private List<HomeItem> mDataList;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        mRecyclerView = findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        initAdapter();
    }

    private void initAdapter() {
        initData();
        HomeAdapter homeAdapter = new HomeAdapter(mDataList);
        homeAdapter.openLoadAnimation();
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                shortToast(mDataList.get(position).getTitle());
            }
        });
        mRecyclerView.setAdapter(homeAdapter);
    }

    private void initData() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < TITLE.length; i++) {
            HomeItem item = new HomeItem();
            item.setTitle(TITLE[i]);
            item.setImageResource(IMG[i]);
            mDataList.add(item);
        }
    }
}
