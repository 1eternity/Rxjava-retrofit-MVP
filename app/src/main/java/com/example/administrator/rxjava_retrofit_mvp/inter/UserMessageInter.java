package com.example.administrator.rxjava_retrofit_mvp.inter;


import com.example.administrator.rxjava_retrofit_mvp.bean.UserBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2018\8\7 0007.
 */

public interface UserMessageInter {

    @GET("users/{user}")
    Observable<UserBean> getMessage(@Path("user") String user);

    @GET("users/{user}")
    Call<UserBean> getMessageCall(@Path("user") String user);
}
