package com.andy.module.login.data.login;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-04-28$ 15:13$
 * @UpdateUser: lidong
 * @UpdateDate: 2019-04-28$ 15:13$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class LoginService {


    /***
     * 获取数据（这里暂时模拟请求）
     * @param login
     * @return
     */
    public Observable login(Login login) {
        return Observable.just(login).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
