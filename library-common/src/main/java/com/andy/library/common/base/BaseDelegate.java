package com.andy.library.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-04-28 12:01
 * @UpdateUser: lidong
 * @UpdateDate: 2019-04-28 12:01
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public interface BaseDelegate {

    void create(LayoutInflater i, ViewGroup v, Bundle b);

    View getRootView();
}
