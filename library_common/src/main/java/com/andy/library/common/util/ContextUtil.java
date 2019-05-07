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
public class ContextUtil {

    private static Context context;

    public static void set(Context contexts) {
        context = contexts;
    }

    public static Context get() {
        return context;
    }
}

