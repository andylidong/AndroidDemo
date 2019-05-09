package com.andy.module.react.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.KeyEvent;

import com.andy.library.common.util.ConstantUtil;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-04-29$ 11:57$
 * @UpdateUser: lidong
 * @UpdateDate: 2019-04-29$ 11:57$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class ReactActivityHelper {

    private ReactInstanceManager mReactInstanceManager;

    private List<ReactPackage> reactPackageList = new ArrayList<>();

    private ReactActivityHelper() {
    }

    private static ReactActivityHelper ourInstance;

    public static ReactActivityHelper getInstance() {
        if (ourInstance == null) {
            synchronized (ReactActivityHelper.class) {
                if (ourInstance == null) {
                    ourInstance = new ReactActivityHelper();
                }
            }
        }
        return ourInstance;
    }

    public void initReact(Application application) {
        if (mReactInstanceManager == null) {
            reactPackageList.add(new MainReactPackage());
            reactPackageList.add(new BaseReactPackage());
            mReactInstanceManager = ReactInstanceManager.builder()
                    .setApplication(application)
                    .setBundleAssetName("index.android.bundle")
                    .setJSMainModulePath("index")
                    .addPackages(reactPackageList)
                    .setUseDeveloperSupport(ConstantUtil.getIsDebug())
                    .setInitialLifecycleState(LifecycleState.BEFORE_CREATE)
                    .build();
            mReactInstanceManager.createReactContextInBackground();
        }
    }

    protected ReactRootView getContentView(Context context, String componentName) {
        ReactRootView mReactRootView = new ReactRootView(context);
        //这里的AndroidRnDemoApp必须对应“index.js”中的“AppRegistry.registerComponent()”的第一个参数
        mReactRootView.startReactApplication(mReactInstanceManager, componentName, null);
        return mReactRootView;
    }

    /**
     * ReactInstanceManager生命周期同activity
     */

    protected void onPause(Activity activity) {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(activity);
        }
    }


    protected void onResume(Activity activity, DefaultHardwareBackBtnHandler defaultBackButtonImpl) {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(activity, defaultBackButtonImpl);
        }
    }


    protected void onDestroy(Activity activity, ReactRootView reactRootView) {
        if (mReactInstanceManager != null) {
            reactRootView.unmountReactApplication();
            mReactInstanceManager.detachRootView(reactRootView);
            mReactInstanceManager.onHostDestroy(activity);
        }
    }


    public boolean onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
            return true;
        }
        return false;
    }


    public boolean onKeyUp(int keyCode) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return false;
    }
}
