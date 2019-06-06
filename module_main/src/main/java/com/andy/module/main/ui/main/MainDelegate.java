package com.andy.module.main.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.TextView;

import com.andy.module.main.R;
import com.andy.module.main.data.main.Main;
import com.andy.module.main.data.main.MainService;
import com.andy.module.main.ui.index.IndexFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.kymjs.themvp.view.AppDelegate;

import java.util.ArrayList;
import java.util.List;

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

    protected QBadgeView qBadgeView;

    protected Context context;

    protected ViewPager viewPager;

    /**
     * 判断是否点击过了
     */
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
        navView = get(R.id.navView);
        navView.enableAnimation(false);
        navView.enableShiftingMode(false);
        navView.enableItemShiftingMode(false);
        navView.setIconSize(26, 26);
        navView.setTextSize(10);
        // 模拟注释
        addBadgeAt(2, 4);
        // 获取viewpager
        viewPager = get(R.id.viewPager);
        List<Fragment> fragments = new ArrayList<>(1);
        IndexFragment indexFragment1 = new IndexFragment();
        indexFragment1.setArguments(this.getBundle("主页"));
        fragments.add(indexFragment1);

        IndexFragment indexFragment2 = new IndexFragment();
        indexFragment2.setArguments(this.getBundle("工作"));
        fragments.add(indexFragment2);

        IndexFragment indexFragment3 = new IndexFragment();
        indexFragment3.setArguments(this.getBundle("通知"));
        fragments.add(indexFragment3);

        IndexFragment indexFragment4 = new IndexFragment();
        indexFragment4.setArguments(this.getBundle("我的"));
        fragments.add(indexFragment4);
        ViewPagerAdapter adapter = new ViewPagerAdapter(((MainActivity) context).getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
    }

    protected void setTitle(String title) {
        Main main = mainService.getTitle(title);
        mTextMessage.setText(main.getTitle());
    }

    protected void addBadgeAt(int position, int number) {
        qBadgeView = new QBadgeView(context);
        qBadgeView.setBadgeNumber(number)
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
            boolean flag;
            if (id == R.id.navigation_home) {
                mTextMessage.setText(R.string.title_home);
                viewPager.setCurrentItem(1);
                flag = true;
            } else if (id == R.id.navigation_dashboard) {
                mTextMessage.setText(R.string.title_dashboard);
                viewPager.setCurrentItem(2);
                flag = true;
            } else if (id == R.id.navigation_notifications) {
                mTextMessage.setText(R.string.title_notifications);
                qBadgeView.hide(true);
                viewPager.setCurrentItem(3);
                flag = true;
            } else if (id == R.id.navigation_setting) {
                mTextMessage.setText(R.string.title_setting);
                viewPager.setCurrentItem(4);
                flag = true;
            } else {
                flag = false;
            }
            return flag;
        }
    };


    private static class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> data;

        public ViewPagerAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }
    }

    private Bundle getBundle(String params) {
        Bundle bundle = new Bundle();
        bundle.putString("title", params);
        return bundle;
    }
}
