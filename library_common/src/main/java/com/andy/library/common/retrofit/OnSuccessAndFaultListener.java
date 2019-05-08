package com.andy.library.common.retrofit;

/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-05-08$ 11:53$
 * @UpdateUser: lidong
 * @UpdateDate: 2019-05-08$ 11:53$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public interface OnSuccessAndFaultListener<T> {
    void onSuccess(T result);

    void onError(String errorMsg);
}
