package com.andy.module.react.ui.react;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.andy.library.common.router.RouterActivityPath;
import com.andy.module.react.R;

@Route(path = RouterActivityPath.React.PAGER_REACT)
public class ReactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react);
    }

}
