package com.andy.library.common.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.andy.library.common.BuildConfig;


/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-04-28 12:02
 * @UpdateUser: lidong
 * @UpdateDate: 2019-04-28 12:02
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initRouter();
    }

    /**
     * 初始化阿里路由框架
     */
    private void initRouter() {
        if (BuildConfig.DEBUG) {
            //一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }
}
