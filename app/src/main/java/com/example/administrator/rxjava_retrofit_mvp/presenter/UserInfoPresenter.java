package com.example.administrator.rxjava_retrofit_mvp.presenter;

import com.example.administrator.rxjava_retrofit_mvp.bean.UserBean;
import com.example.administrator.rxjava_retrofit_mvp.model.UserInfoModel;
import com.example.administrator.rxjava_retrofit_mvp.mvp.IPresenter;
import com.example.administrator.rxjava_retrofit_mvp.mvp.IView;
import java.util.List;


/**
 * Created by lgq on 2018/8/8.
 */

public class UserInfoPresenter extends BasePresenter<IView> implements IPresenter, UserInfoModel.OnLoadingData {
    private IView mView;
    private UserInfoModel mModel;

    public UserInfoPresenter(IView view) {
        this.mView = view;
        this.mModel = new UserInfoModel();
        attachView(view);
        mModel.setOnLoadingData(this);
    }

    @Override
    public void initData(String user) {
        mModel.getData(user);
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


    @Override
    public void detachRxjava() {
        mModel.detachRxjava();
    }
}
