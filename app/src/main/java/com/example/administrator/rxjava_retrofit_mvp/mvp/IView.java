package com.example.administrator.rxjava_retrofit_mvp.mvp;

import com.example.administrator.rxjava_retrofit_mvp.bean.UserBean;

import java.util.List;

/**
 * Created by lgq on 2018/8/8.
 */

public interface IView {
    void showLoadingDialog();
    void dissLoadingDialog();
    void showData(List<UserBean> list);
}
