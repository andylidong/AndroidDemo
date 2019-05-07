package com.andy.module.login.ui.login;

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
    public View mProgressView;
    public View mLoginFormView;
    public EditText mPasswordView;
    public AutoCompleteTextView mEmailView;
    public Button mEmailSignInButton;

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

    protected void login() {
        // 获取信息
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        // 设置数据
        Login login = new Login(email, password);
        loginService.login(login);
    }
}
