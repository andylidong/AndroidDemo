package com.andy.module.login.ui.login;

import android.view.inputmethod.EditorInfo;

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
        viewDelegate.mPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                viewDelegate.login();
                return true;
            }
            return false;
        });
        viewDelegate.mEmailSignInButton.setOnClickListener(view -> viewDelegate.login());
    }
}

