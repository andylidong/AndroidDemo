package com.andy;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
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
    private final int OVERLAY_PERMISSION_REQ_CODE = 1000;

    private boolean isAllowed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        checkAppPermission();

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

        Button react = findViewById(R.id.react);
        react.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAllowed) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("react", "This is a demo for Home!!!!");
                navigation(RouterActivityPath.React.PAGER_REACT, bundle);
            }
        });
    }


    private void navigation(String path, Bundle bundle) {
        ARouter.getInstance().build(path).with(bundle).navigation();
    }

    private void checkAppPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    //SYSTEM_ALERT_WINDOW被拒绝
                    this.isAllowed = false;
                }
            }
        }
    }
}
