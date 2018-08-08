package com.example.administrator.rxjava_retrofit_mvp.model;


import android.util.Log;

import com.example.administrator.rxjava_retrofit_mvp.bean.UserBean;
import com.example.administrator.rxjava_retrofit_mvp.instance.RetrofitInstance;
import com.example.administrator.rxjava_retrofit_mvp.inter.UserMessageInter;
import com.example.administrator.rxjava_retrofit_mvp.mvp.IModel;
import com.example.administrator.rxjava_retrofit_mvp.rxjava.ApiCallback;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by lgq on 2018/8/8.
 */

public class UserInfoModel implements IModel {

    private OnLoadingData onLoadingData;
    public CompositeDisposable mCompositeDisposable;

    public void setOnLoadingData(OnLoadingData onLoadingData) {
        this.onLoadingData = onLoadingData;
    }

    @Override
    public void getData(String user) {
        Retrofit mRetrofit = RetrofitInstance.getInstance().getRetrofit();
        final List<UserBean> list = new ArrayList<>();
        UserMessageInter userMessageInter = mRetrofit.create(UserMessageInter.class);
//        Observable<UserBean> call = userMessageInter.getMessage(user);
        onLoadingData.showLoadingDialog();
        addSubscription(mCompositeDisposable, userMessageInter.getMessage(user), new ApiCallback<UserBean>() {

            @Override
            public void onSuccess(UserBean model) {
                list.add(model);
                Log.e("tag","mCompositeDisposable3="+mCompositeDisposable);
                onLoadingData.onFinishListener(list);
            }

            @Override
            public void onFailure(String msg) {
                //失败
                Log.e("tag","失败了————"+msg);
            }

            @Override
            public void onFinish() {
                onLoadingData.dissLoadingDialog();
                
            }
        });
    }

    public void addSubscription(CompositeDisposable compositeDisposable, Observable observable, DisposableObserver observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
            
        }
        mCompositeDisposable.add(observer);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
    }
    
    public void detachRxjava(){
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
    public interface OnLoadingData {
        void showLoadingDialog();

        void dissLoadingDialog();

        void onFinishListener(List<UserBean> list);
        
    }
}
