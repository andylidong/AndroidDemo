package com.andy;

import com.andy.library.common.base.BaseApplication;
import com.andy.library.common.util.ConstantUtil;
import com.andy.module.react.base.ReactActivityHelper;

/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-04-28 12:01
 * @UpdateUser: lidong
 * @UpdateDate: 2019-04-28 12:01
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class AppApplication extends BaseApplication {

    public AppApplication() {
        super();
        debug = BuildConfig.DEBUG;
        ConstantUtil.setIsDebug(BuildConfig.DEBUG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ReactActivityHelper.getInstance().initReact(this);
    }
}
