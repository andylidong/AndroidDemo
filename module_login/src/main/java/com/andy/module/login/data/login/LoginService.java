package com.andy.module.login.data.login;

import com.andy.library.common.retrofit.RetrofitFactory;

import io.reactivex.observers.DisposableObserver;

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


    private LoginApi loginApi;

    /***
     * 登录信息
     * @param login
     * @return
     */
    public void login(Login login, DisposableObserver<Object> subscriber) {
        System.out.println("login = " + login);
        loginApi = RetrofitFactory.getInstance().create(LoginApi.class);
        RetrofitFactory.getInstance().toSubscribe(loginApi.getUsers(), subscriber);
    }
}
