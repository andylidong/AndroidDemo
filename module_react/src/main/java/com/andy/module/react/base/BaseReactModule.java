package com.andy.module.react.base;

import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

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

    private ReactApplicationContext reactContext;

    public BaseReactModule(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
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


    @ReactMethod()
    public void toast(String content) {
        Toast.makeText(reactContext, content, Toast.LENGTH_SHORT).show();
    }
}
