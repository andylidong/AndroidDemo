package com.andy.library.common.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-05-07$ 16:08$
 * @UpdateUser: lidong
 * @UpdateDate: 2019-05-07$ 16:08$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class RetrofitHelper {


    private final String BASE_URL = "http://10.1.3.155:9090/";

    private static Retrofit retrofit;


    private RetrofitHelper() {
        init();
    }

    private static RetrofitHelper ourInstance;

    public static RetrofitHelper getInstance() {
        if (ourInstance == null) {
            synchronized (RetrofitHelper.class) {
                if (ourInstance == null) {
                    ourInstance = new RetrofitHelper();
                }
            }
        }
        return ourInstance;
    }

    public Retrofit retrofit() {
        return retrofit;
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

    private void init() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                // 可以接收自定义的Gson，当然也可以不传
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
