package com.andy.module.login.data.login;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
     * 获取数据（这里暂时模拟请求）
     * @param login
     * @return
     */
    public Observable<Login> login(Login login) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.1.3.155:9090/")
                // 可以接收自定义的Gson，当然也可以不传
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        loginApi = retrofit.create(LoginApi.class);

        loginApi.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onNext = " + o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError = " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return Observable.just(login).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
