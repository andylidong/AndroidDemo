package com.andy.module.react.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import com.facebook.react.ReactRootView;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

/**
 * @Description: ReactActivity的基类
 * @Author: lidong
 * @CreateDate: 2019-04-29$ 14:27$
 * @UpdateUser: lidong
 * @UpdateDate: 2019-04-29$ 14:27$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class BaseReactActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {

    private ReactRootView reactRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 加载ReactRootView到布局中
        reactRootView = ReactActivityHelper.getInstance().getContentView(this);
        setContentView(reactRootView);
    }


    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    /**
     * ReactInstanceManager生命周期同activity
     */
    @Override
    protected void onPause() {
        super.onPause();
        ReactActivityHelper.getInstance().onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ReactActivityHelper.getInstance().onResume(this, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ReactActivityHelper.getInstance().onDestroy(this, reactRootView);
    }

    @Override
    public void onBackPressed() {
        if (!ReactActivityHelper.getInstance().onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (ReactActivityHelper.getInstance().onKeyUp(keyCode)) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}