package com.andy.module.main.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.module.main.R;
import com.andy.module.main.data.main.Main;
import com.andy.module.main.data.main.MainService;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.kymjs.themvp.view.AppDelegate;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-04-28$ 13:57$
 * @UpdateUser: lidong
 * @UpdateDate: 2019-04-28$ 13:57$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class MainDelegate extends AppDelegate {

    protected TextView mTextMessage;

    protected BottomNavigationViewEx navView;

    protected Context context;

    // 判断是否点击过了
    private int lastId;

    private MainService mainService;

    public MainDelegate() {
        this.mainService = new MainService();
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mTextMessage = get(R.id.message);
        // 设置底部导航栏
        navView = get(R.id.nav_view);
        navView.enableAnimation(false);
        navView.enableShiftingMode(false);
        navView.enableItemShiftingMode(false);
        navView.setIconSize(26, 26);
        navView.setTextSize(10);
        // 模拟注释
        addBadgeAt(2, 4);
    }

    protected void setTitle(String title) {
        Main main = mainService.getTitle(title);
        mTextMessage.setText(main.getTitle());
    }

    protected Badge addBadgeAt(int position, int number) {
        return new QBadgeView(context)
                .setBadgeNumber(number)
                .setGravityOffset(26, 2, true)
                .bindTarget(navView.getBottomNavigationItemView(position))
                .setOnDragStateChangedListener((dragState, badge, targetView) -> {
                });
    }

    protected BottomNavigationViewEx.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationViewEx.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            if (lastId == id) {
                return false;
            }
            lastId = id;
            if (id == R.id.navigation_home) {
                mTextMessage.setText(R.string.title_home);
                return true;
            } else if (id == R.id.navigation_dashboard) {
                mTextMessage.setText(R.string.title_dashboard);
                return true;
            } else if (id == R.id.navigation_notifications) {
                mTextMessage.setText(R.string.title_notifications);
                return true;
            } else if (id == R.id.navigation_setting) {
                mTextMessage.setText(R.string.title_setting);
                return true;
            }
            return false;
        }
    };
}
