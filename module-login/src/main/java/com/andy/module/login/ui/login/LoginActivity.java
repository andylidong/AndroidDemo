package com.andy.module.login.ui.login;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.andy.library.common.router.RouterActivityPath;
import com.kymjs.themvp.presenter.ActivityPresenter;

@Route(path = RouterActivityPath.Login.PAGER_LOGIN)
public class LoginActivity extends ActivityPresenter<LoginDelegate> {


    @Override
    protected Class<LoginDelegate> getDelegateClass() {
        return LoginDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    viewDelegate.login();
                    return true;
                }
                return false;
            }
        });
        viewDelegate.mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                viewDelegate.login();
            }
        });
    }
}

