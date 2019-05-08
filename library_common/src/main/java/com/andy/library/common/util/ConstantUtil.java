package com.andy.library.common.util;

import android.content.Context;

/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-05-07$ 16:38$
 * @UpdateUser: lidong
 * @UpdateDate: 2019-05-07$ 16:38$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class ConstantUtil {

    // 获取上下文资源
    private static Context context;

    // 获取判断是否是debug
    private static Boolean isDebug = false;

    // 获取token
    private static String token = "";

    public static void setContext(Context context) {
        ConstantUtil.context = context;
    }

    public static Context getContext() {
        return context;
    }

    public static void setIsDebug(Boolean isDebug) {
        ConstantUtil.isDebug = isDebug;
    }

    public static Boolean getIsDebug() {
        return isDebug;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        ConstantUtil.token = token;
    }
}

