package com.andy.library.common.retrofit;

import com.andy.library.common.R;
import com.andy.library.common.util.ConstantUtil;
import com.andy.library.common.util.EmptyUtil;
import com.andy.library.common.util.NetUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
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
public class RetrofitFactory {

    public String TAG = "RetrofitFactory";
    // 请求失败重连次数
    private int RETRY_COUNT = 0;
    // 设置基本的路由
    private final String BASE_URL = ConstantUtil.getContext().getString(R.string.base_url);
    // 填写自己的包名
    private static final String CACHE_NAME = ConstantUtil.getContext().getString(R.string.base_package);
    // 连接超时时间
    private static final int DEFAULT_CONNECT_TIMEOUT = 30;
    // 写入超时时间
    private static final int DEFAULT_WRITE_TIMEOUT = 30;
    // 读取超时时间
    private static final int DEFAULT_READ_TIMEOUT = 30;
    // 创建网络访问对象
    private OkHttpClient.Builder okHttpBuilder;
    // 创建Retrofit对象
    private Retrofit retrofit;

    private static RetrofitFactory ourInstance;

    public static RetrofitFactory getInstance() {
        if (ourInstance == null) {
            synchronized (RetrofitFactory.class) {
                if (ourInstance == null) {
                    ourInstance = new RetrofitFactory();
                }
            }
        }
        return ourInstance;
    }


    private RetrofitFactory() {
        init();
    }

    private void init() {
        //手动创建一个OkHttpClient并设置超时时间
        okHttpBuilder = new OkHttpClient.Builder();
        // 设置缓存
        File cacheFile = new File(ConstantUtil.getContext().getExternalCacheDir(), CACHE_NAME);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = chain -> {
            Request request = chain.request();
            if (!NetUtil.isNetworkConnected()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (!NetUtil.isNetworkConnected()) {
                int maxAge = 0;
                // 有网络时 设置缓存超时时间0个小时
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .removeHeader(CACHE_NAME)
                        .build();
            } else {
                // 无网络时，设置超时为4周
                int maxStale = 60 * 60 * 24 * 28;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader(CACHE_NAME)
                        .build();
            }
            return response;
        };
        okHttpBuilder.cache(cache).addInterceptor(cacheInterceptor);


        // 设置头信息
        Interceptor headerInterceptor = chain -> {
            Request originalRequest = chain.request();
            Request.Builder requestBuilder = originalRequest.newBuilder()
                    .addHeader("Accept-Encoding", "gzip")
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .method(originalRequest.method(), originalRequest.body());
            // 添加请求头信息，服务器进行token有效性验证
            if (EmptyUtil.isNotEmpty(ConstantUtil.getToken())) {
                requestBuilder.addHeader("Authorization", "Bearer " + ConstantUtil.getToken());
            }
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
        okHttpBuilder.addInterceptor(headerInterceptor);


        if (ConstantUtil.getIsDebug()) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> {
                System.out.println("HttpLoggingInterceptor = " + message);
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            // 设置 Debug Log 模式
            okHttpBuilder.addInterceptor(loggingInterceptor);
        }

        // 设置超时和重新连接
        okHttpBuilder.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);
        // 错误重连
        okHttpBuilder.retryOnConnectionFailure(true);
        // 创建对象信息
        retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                //json转换成JavaBean
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    /**
     * 创建访问Class
     *
     * @param service
     * @param <T>
     * @return
     */
    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

    /**
     * 设置订阅 和 所在的线程环境
     */
    public <T> void toSubscribe(Observable<T> o, DisposableObserver<T> s) {

        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //请求失败重连次数
                .retry(RETRY_COUNT)
                .subscribe(s);

    }
}
