package com.andy.module.main.ui.index;

import android.widget.TextView;

import com.andy.module.main.R;
import com.kymjs.themvp.view.AppDelegate;

/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-06-04$ 16:33$
 * @UpdateUser: lidong
 * @UpdateDate: 2019-06-04$ 16:33$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class IndexDelegate extends AppDelegate {

    protected TextView title;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        title = get(R.id.tvTitle);
    }
}
