package com.andy.module.react.ui.react;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.andy.library.common.router.RouterActivityPath;
import com.andy.module.react.base.BaseReactActivity;

@Route(path = RouterActivityPath.React.PAGER_REACT)
public class ReactActivity extends BaseReactActivity {

    private final String COMPONENT_NAME = "module_react";

    public ReactActivity() {
        componentName = COMPONENT_NAME;
    }
}
