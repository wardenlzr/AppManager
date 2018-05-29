package com.lzr.warden.terrificlibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * <pre>
 *     author: Blankj
 *     editor: Warden
 *     blog  : http://blankj.com
 *     time  : 2017/06/27
 *     desc  :
 * </pre>
 */
interface IBaseView {

    /**
     * 初始化数据
     *
     * @param bundle 传递过来的 bundle
     */
//    void initData(@Nullable final Bundle bundle);

    /**
     * 绑定布局
     *
     * @return 布局 Id
     */
    int bindLayout();

    /**
     * 初始化 view
     */
    void initView(final Bundle savedInstanceState, final View contentView);

}
