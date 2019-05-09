package com.andy.module.react.base;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-05-09$ 18:08$
 * @UpdateUser: lidong
 * @UpdateDate: 2019-05-09$ 18:08$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class BaseReactPackage implements ReactPackage {

    private List<NativeModule> nativeModuleList = new ArrayList<>();
    private List<ViewManager> viewManagerList = new ArrayList<>();

    @Nonnull
    @Override
    public List<NativeModule> createNativeModules(@Nonnull ReactApplicationContext reactContext) {
        nativeModuleList.add(new BaseReactModule(reactContext));
        return nativeModuleList;
    }

    @Nonnull
    @Override
    public List<ViewManager> createViewManagers(@Nonnull ReactApplicationContext reactContext) {
        return viewManagerList;
    }
}
