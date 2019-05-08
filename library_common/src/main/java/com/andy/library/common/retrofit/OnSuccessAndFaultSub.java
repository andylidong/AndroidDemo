package com.andy.library.common.retrofit;

import com.andy.library.common.util.EmptyUtil;

import io.reactivex.observers.DisposableObserver;

/**
 * @Description: 用于在Http请求, 调用者自己对请求数据进行处理   成功时 通过result是否等于1分别回调onSuccess和onFault，默认处理了401错误转登录,回调结果为String，需要手动序列化
 * @Author: lidong
 * @CreateDate: 2019-05-08$ 11:57$
 * @UpdateUser: lidong
 * @UpdateDate: 2019-05-08$ 11:57$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class OnSuccessAndFaultSub extends DisposableObserver<Object> {

    private OnSuccessAndFaultListener mOnSuccessAndFaultListener;

    /**
     * @param mOnSuccessAndFaultListener 回调监听
     */
    public OnSuccessAndFaultSub(OnSuccessAndFaultListener mOnSuccessAndFaultListener) {
        this.mOnSuccessAndFaultListener = mOnSuccessAndFaultListener;
    }

    @Override
    public void onNext(Object o) {
        mOnSuccessAndFaultListener.onSuccess(o);
    }

    @Override
    public void onError(Throwable e) {
        mOnSuccessAndFaultListener.onError(EmptyUtil.isNotEmpty(e) ? e.getMessage() : "");
    }

    @Override
    public void onComplete() {
    }
}
