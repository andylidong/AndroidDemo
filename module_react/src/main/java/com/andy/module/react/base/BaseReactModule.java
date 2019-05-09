package com.andy.module.react.base;

import com.andy.module.react.util.EventUtil;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

import javax.annotation.Nonnull;

/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-05-09$ 18:09$
 * @UpdateUser: lidong
 * @UpdateDate: 2019-05-09$ 18:09$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class BaseReactModule extends ReactContextBaseJavaModule {

    public BaseReactModule(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
        EventUtil.init(reactContext);
    }

    @Nonnull
    @Override
    public String getName() {
        return "BaseReactModule";
    }
}
