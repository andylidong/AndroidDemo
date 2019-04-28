package com.andy.module.main.ui.main;

import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.andy.library.common.router.RouterActivityPath;
import com.kymjs.themvp.presenter.ActivityPresenter;

/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-04-28 13:49
 * @UpdateUser: lidong
 * @UpdateDate: 2019-04-28 13:49
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Route(path = RouterActivityPath.Main.PAGER_MAIN)
public class MainActivity extends ActivityPresenter<MainDelegate> {

    private final String BUNDLE_NAME = "Home";

    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        // 设置导航栏的点击事件
        viewDelegate.navView.setOnNavigationItemSelectedListener(viewDelegate.mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        // 设置获取的跳转参数
        Bundle bundle = getIntent().getExtras();
        viewDelegate.setTitle(bundle.getString(BUNDLE_NAME));
    }

}