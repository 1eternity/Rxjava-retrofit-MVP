package com.example.administrator.rxjava_retrofit_mvp.rxjava;

import android.util.Log;

import io.reactivex.observers.DisposableObserver;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by lgq on 2018/8/8.
 */

public abstract class ApiCallback<M> extends DisposableObserver<M> {
    public abstract void onSuccess(M model);

    public abstract void onFailure(String msg);

    public abstract void onFinish();

    @Override
    public void onNext(M value) {
        onSuccess(value);
    }

    @Override
    public void onComplete() {
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            Log.e("tag","code="+code);
            String message = httpException.getMessage();
            if (code == 504) {
                message = "网络不给力";
            }
            if (code == 502 || code == 404) {
                message = "服务器异常，请稍后再试";
            }
            onFailure(message);
        } else {
            onFailure(e.getMessage());
        }
        onFinish();
    }
}
