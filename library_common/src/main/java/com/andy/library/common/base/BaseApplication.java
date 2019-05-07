package com.andy.library.common.base;

import android.app.Application;

import android.content.pm.ApplicationInfo;

import com.alibaba.android.arouter.launcher.ARouter;
import com.andy.library.common.BuildConfig;
import com.andy.library.common.util.ContextUtil;


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
        initDebug();
        ContextUtil.set(this);
    }

    /**
     * 初始化阿里路由框架
     */
    private void initRouter() {
        if (BuildConfig.DEBUG) {
            // 一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }


    /**
     * 判断是否是开发模式
     */
    private void initDebug() {
        debug = getApplicationInfo() != null && (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    /**
     * 获取运行的结果
     *
     * @return
     */
    public static boolean isDebug() {
        return debug;
    }
}
