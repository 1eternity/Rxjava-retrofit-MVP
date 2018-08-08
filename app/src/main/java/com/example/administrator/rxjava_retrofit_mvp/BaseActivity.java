package com.example.administrator.rxjava_retrofit_mvp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.rxjava_retrofit_mvp.presenter.UserInfoPresenter;

/**
 * Created by lgq on 2018/8/8.
 */

public abstract class BaseActivity<T extends UserInfoPresenter> extends AppCompatActivity {
    public T userInfoPresenter;
    public ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        progressDialog=initProgress();
        userInfoPresenter=loadPresenter();
        
    }

    protected abstract void initView();

    protected abstract ProgressDialog initProgress();


    protected abstract T loadPresenter();

    protected abstract int getLayoutId();
}
