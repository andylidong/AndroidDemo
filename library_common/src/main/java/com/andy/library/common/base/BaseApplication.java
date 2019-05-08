package com.andy.library.common.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.andy.library.common.util.ConstantUtil;


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

    protected static boolean debug = false;

    @Override
    public void onCreate() {
        super.onCreate();
        initRouter();
        ConstantUtil.setContext(this);
    }

    /**
     * 初始化阿里路由框架
     */
    private void initRouter() {
        if (debug) {
            // 一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }
}
