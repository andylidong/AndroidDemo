package com.andy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.andy.library.common.router.RouterActivityPath;


/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-04-28 12:01
 * @UpdateUser: lidong
 * @UpdateDate: 2019-04-28 12:01
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class AppActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigation(RouterActivityPath.Login.PAGER_LOGIN, null);
            }
        });

        Button home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("Home", "This is a demo for Home!!!!");
                navigation(RouterActivityPath.Main.PAGER_MAIN, bundle);
            }
        });
    }


    private void navigation(String path, Bundle bundle) {
        ARouter.getInstance().build(path).with(bundle).navigation();
    }
}
