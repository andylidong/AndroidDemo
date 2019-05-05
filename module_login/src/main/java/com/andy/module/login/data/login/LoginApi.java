package com.andy.module.login.data.login;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-05-05$ 16:56$
 * @UpdateUser: lidong
 * @UpdateDate: 2019-05-05$ 16:56$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public interface LoginApi {

    @GET("user/users?id=1")
    Call<ResponseBody> getUser();


    @GET("user/users?id=1")
    Observable<Object> getUsers();

}
