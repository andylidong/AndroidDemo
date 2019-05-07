package com.andy.module.login.data.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;

import com.andy.library.common.retrofit.RetrofitHelper;
import com.andy.module.login.R;
import com.andy.module.login.ui.login.LoginDelegate;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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


    private LoginApi loginApi;

    private LoginDelegate loginDelegate;

    public LoginService(LoginDelegate loginDelegate) {
        this.loginDelegate = loginDelegate;
    }

    /***
     * 获取数据（这里暂时模拟请求）
     * @param login
     * @return
     */
    public void login(Login login) {
        loginApi = RetrofitHelper.getInstance().create(LoginApi.class);
        loginApi.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        try {
                            System.out.println("o = " + o);
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                        }
                    }

                    @Override
                    public void onComplete() {
                        loginDelegate.onLoginComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginDelegate.onLoginError(e);
                    }
                });
    }
}
