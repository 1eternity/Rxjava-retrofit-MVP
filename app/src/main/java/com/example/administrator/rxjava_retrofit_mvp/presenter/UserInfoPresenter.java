package com.example.administrator.rxjava_retrofit_mvp.presenter;

import android.os.Looper;
import android.util.Log;

import com.example.administrator.rxjava_retrofit_mvp.bean.UserBean;
import com.example.administrator.rxjava_retrofit_mvp.callback.RetrofitCallback;
import com.example.administrator.rxjava_retrofit_mvp.instance.RetrofitInstance;
import com.example.administrator.rxjava_retrofit_mvp.inter.UserMessageInter;
import com.example.administrator.rxjava_retrofit_mvp.model.UserInfoModel;
import com.example.administrator.rxjava_retrofit_mvp.mvp.IModel;
import com.example.administrator.rxjava_retrofit_mvp.mvp.IPresenter;
import com.example.administrator.rxjava_retrofit_mvp.mvp.IView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by lgq on 2018/8/8.
 */

public class UserInfoPresenter implements IPresenter,UserInfoModel.OnLoadingData {
    private IView mView;
    private UserInfoModel mModel;

    public UserInfoPresenter(IView view) {
        this.mView = view;
        this.mModel = new UserInfoModel();
        mModel.setOnLoadingData(this);
    }

    @Override
    public void initData(String user) {
        mModel.getData("Guolei1130");
    }
    public boolean isMain() {

        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    @Override
    public void showLoadingDialog() {
        mView.showLoadingDialog();
    }

    @Override
    public void dissLoadingDialog() {
        mView.dissLoadingDialog();
    }

    @Override
    public void onFinishListener(List<UserBean> list) {
        mView.showData(list);
    }
}
