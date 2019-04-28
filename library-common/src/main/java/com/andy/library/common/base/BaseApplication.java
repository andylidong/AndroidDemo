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

        //初始化阿里路由框架
        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
    }
}
