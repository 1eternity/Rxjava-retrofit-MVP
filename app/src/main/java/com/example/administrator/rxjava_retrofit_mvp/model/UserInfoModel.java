package com.example.administrator.rxjava_retrofit_mvp.model;

import android.util.Log;

import com.example.administrator.rxjava_retrofit_mvp.bean.UserBean;
import com.example.administrator.rxjava_retrofit_mvp.callback.RetrofitCallback;
import com.example.administrator.rxjava_retrofit_mvp.instance.RetrofitInstance;
import com.example.administrator.rxjava_retrofit_mvp.inter.UserMessageInter;
import com.example.administrator.rxjava_retrofit_mvp.mvp.IModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by lgq on 2018/8/8.
 */

public class UserInfoModel implements IModel {
    
    private OnLoadingData onLoadingData;

    public void setOnLoadingData(OnLoadingData onLoadingData) {
        this.onLoadingData = onLoadingData;
    }

    @Override
    public void getData(String user) {
        Retrofit mRetrofit = RetrofitInstance.getInstance().getRetrofit();
        final List<UserBean> list = new ArrayList<>();
        UserMessageInter userMessageInter = mRetrofit.create(UserMessageInter.class);
        Call<UserBean> call = userMessageInter.getMessageCall(user);
        onLoadingData.showLoadingDialog();
        call.enqueue(new RetrofitCallback<UserBean>() {
            @Override
            public void onSuccess(UserBean model) {
                list.add(model);
                onLoadingData.onFinishListener(list);
            }

            @Override
            public void onFailure(int code, String codeMsg) {
                Log.e("tag", "code=" + code + "--codeMsg=" + codeMsg);
            }

            @Override
            public void onThrowable(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onFinish() {
                onLoadingData.dissLoadingDialog();
            }
        });
    }
    
    public interface OnLoadingData{
        void showLoadingDialog();
        void dissLoadingDialog();
        void onFinishListener(List<UserBean> list);
    }
}
