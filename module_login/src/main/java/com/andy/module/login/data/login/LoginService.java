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

        if (checkForm(login.getEmail(), login.getPassword())) {
            return;
        }

        showProgress(true);

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
                        showProgress(false);
                        loginDelegate.getActivity().finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showProgress(false);
                        loginDelegate.mPasswordView.setError(getString(R.string.error_incorrect_password));
                        loginDelegate.mPasswordView.requestFocus();
                    }
                });
    }


    /**
     * 检查表单信息
     *
     * @param email
     * @param password
     * @return
     */
    protected boolean checkForm(String email, String password) {
        // 清除错误提示
        loginDelegate.mEmailView.setError(null);
        loginDelegate.mPasswordView.setError(null);

        boolean cancel = false;
        View focusView = null;

        // 检查密码
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            loginDelegate.mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = loginDelegate.mPasswordView;
            cancel = true;
        }

        // 检查邮箱
        if (TextUtils.isEmpty(email)) {
            loginDelegate.mEmailView.setError(getString(R.string.error_field_required));
            focusView = loginDelegate.mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            loginDelegate.mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = loginDelegate.mEmailView;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        }
        return cancel;
    }

    /**
     * 显示进度条和显示表单
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    protected void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = loginDelegate.getActivity().getResources().getInteger(android.R.integer.config_shortAnimTime);

            loginDelegate.mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            loginDelegate.mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginDelegate.mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            loginDelegate.mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            loginDelegate.mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginDelegate.mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            loginDelegate.mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            loginDelegate.mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * 获取资源文件String
     *
     * @param id
     * @return
     */
    private String getString(int id) {
        return loginDelegate.getActivity().getString(id);
    }

    /**
     * 判断是否是邮箱
     *
     * @param email
     * @return
     */
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    /**
     * 判断密码的准确度
     *
     * @param password
     * @return
     */
    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}
