package com.andy.module.main.data.main;

import com.andy.library.common.util.EmptyUtil;

/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-04-28$ 14:55$
 * @UpdateUser: lidong
 * @UpdateDate: 2019-04-28$ 14:55$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class MainService {

    public Main getTitle(String title) {
        Main main = new Main();
        main.setTitle(EmptyUtil.isEmpty(title) ? "This is a demo for test!!!!" : title);
        return main;
    }
}
