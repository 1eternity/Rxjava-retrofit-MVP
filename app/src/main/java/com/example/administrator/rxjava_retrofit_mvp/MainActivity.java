package com.example.administrator.rxjava_retrofit_mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.rxjava_retrofit_mvp.bean.UserBean;
import com.example.administrator.rxjava_retrofit_mvp.instance.RetrofitInstance;
import com.example.administrator.rxjava_retrofit_mvp.inter.UserMessageInter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrofitInstance instance = RetrofitInstance.getInstance();
        retrofit = instance.getRetrofit();
        loadingData();
    }
    private void loadingData() {
        UserMessageInter userMessageInter = retrofit.create(UserMessageInter.class);
        Observable<UserBean> ob = userMessageInter.getMessage("ligoudan");
        ob.subscribeOn(Schedulers.io()).subscribe(new Subscriber<UserBean>() {
            @Override
            public void onCompleted() {
                Log.e("tag", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("tag", "onError");
            }

            @Override
            public void onNext(UserBean userBean) {
                Log.e("tag", "user" + userBean.toString());
            }
        });
    }
}
