package com.example.administrator.rxjava_retrofit_mvp.presenter;


import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lgq on 2018/8/8.
 */

public abstract class BasePresenter<V> {
    public V mView;
    public CompositeDisposable mCompositeDisposable;

    public void attachView(V mView) {
        this.mView = mView;
    }

    public void detachView() {
        this.mView = null;
    }

    public abstract void detachRxjava();
}
