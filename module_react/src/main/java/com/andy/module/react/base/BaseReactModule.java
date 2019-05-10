package com.andy.module.react.base;

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
    }

    @Nonnull
    @Override
    public String getName() {
        return "BaseReactModule";
    }


    @Override
    public boolean canOverrideExistingModule() {
        return true;
    }
}
