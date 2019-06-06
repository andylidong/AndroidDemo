package com.andy.module.main.ui.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.kymjs.themvp.presenter.FragmentPresenter;

/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-06-04 11:45
 * @UpdateUser: lidong
 * @UpdateDate: 2019-06-04 11:45
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class IndexFragment extends FragmentPresenter<IndexDelegate> {

    @Override
    protected Class<IndexDelegate> getDelegateClass() {
        return IndexDelegate.class;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        // 设置获取的跳转参数
        Bundle bundle = getArguments();
        System.out.println("System : " + bundle.getString("title"));
        viewDelegate.title.setText(bundle.getString("title"));
    }
}
