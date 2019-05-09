package com.andy.module.react.ui.react;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.andy.library.common.router.RouterActivityPath;
import com.andy.module.react.base.BaseReactActivity;
import com.andy.module.react.util.EventUtil;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-05-09 18:01
 * @UpdateUser: lidong
 * @UpdateDate: 2019-05-09 18:01
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Route(path = RouterActivityPath.React.PAGER_REACT)
public class ReactActivity extends BaseReactActivity {

    private final String COMPONENT_NAME = "module_react";

    public ReactActivity() {
        componentName = COMPONENT_NAME;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        WritableMap params = Arguments.createMap();
        params.putString("react", bundle.getString("react"));
        EventUtil.sendEvent("react", params);
    }
}
