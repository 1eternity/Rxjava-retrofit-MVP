package com.example.administrator.rxjava_retrofit_mvp.inter;


import com.example.administrator.rxjava_retrofit_mvp.bean.UserBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2018\8\7 0007.
 */

public interface UserMessageInter {

    @GET("users/{user}")
    Call<UserBean> getMessage(@Path("user") String user);
}
