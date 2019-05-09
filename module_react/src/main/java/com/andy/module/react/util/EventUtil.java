package com.andy.module.react.util;

import com.andy.library.common.util.EmptyUtil;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import javax.annotation.Nullable;

/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-05-09 17:49
 * @UpdateUser: lidong
 * @UpdateDate: 2019-05-09 17:49
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class EventUtil {

    private static ReactContext mReactContext;

    public static void init(ReactContext reactContext) {
        mReactContext = reactContext;
    }


    /**
     * 发送事件到JS
     */
    public static void sendEvent(String eventName, @Nullable WritableMap params) {
        if (EmptyUtil.isEmpty(mReactContext)) {
            return;
        }
        mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    /**
     * 发送事件到JS
     */
    public static void sendEventWithArray(String eventName, @Nullable WritableArray params) {
        if (EmptyUtil.isEmpty(mReactContext)) {
            return;
        }
        mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

}
