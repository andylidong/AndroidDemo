package com.andy.module.login.ui.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.andy.module.login.R;
import com.andy.module.login.data.login.Login;
import com.andy.module.login.data.login.LoginService;
import com.kymjs.themvp.view.AppDelegate;

/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-04-28$ 15:29$
 * @UpdateUser: lidong
 * @UpdateDate: 2019-04-28$ 15:29$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class LoginDelegate extends AppDelegate {

    // UI
    protected View mProgressView;
    protected View mLoginFormView;
    protected EditText mPasswordView;
    protected AutoCompleteTextView mEmailView;
    protected Button mEmailSignInButton;

    // 业务
    private LoginService loginService;

    public LoginDelegate() {
        loginService = new LoginService(this);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mEmailView = get(R.id.email);
        mPasswordView = get(R.id.password);
        mProgressView = get(R.id.login_progress);
        mLoginFormView = get(R.id.login_form);
        mEmailSignInButton = get(R.id.email_sign_in_button);
    }

    public void login() {
        // 获取信息
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        // 设置数据
        Login login = new Login(email, password);

        if (checkForm(login.getEmail(), login.getPassword())) {
            return;
        }
        showProgress(true);
        loginService.login(login);
    }

    public void onLoginComplete() {
        showProgress(false);
        getActivity().finish();
    }


    public void onLoginError(Throwable e) {
        showProgress(false);
        mPasswordView.setError(getString(R.string.error_incorrect_password));
        mPasswordView.requestFocus();
    }

    /**
     * 检查表单信息
     *
     * @param email
     * @param password
     */
    private boolean checkForm(String email, String password) {
        // 清除错误提示
        mEmailView.setError(null);
        mPasswordView.setError(null);

        boolean cancel = false;
        View focusView = null;

        // 检查密码
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // 检查邮箱
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
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
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getActivity().getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * 获取资源文件String
     *
     * @param id
     * @return
     */
    private String getString(int id) {
        return getActivity().getString(id);
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
