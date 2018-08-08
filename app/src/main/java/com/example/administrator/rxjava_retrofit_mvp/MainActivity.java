package com.example.administrator.rxjava_retrofit_mvp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.rxjava_retrofit_mvp.bean.UserBean;
import com.example.administrator.rxjava_retrofit_mvp.callback.RetrofitCallback;
import com.example.administrator.rxjava_retrofit_mvp.instance.RetrofitInstance;
import com.example.administrator.rxjava_retrofit_mvp.inter.UserMessageInter;
import com.example.administrator.rxjava_retrofit_mvp.mvp.IView;
import com.example.administrator.rxjava_retrofit_mvp.presenter.UserInfoPresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements IView, View.OnClickListener {

    private Retrofit retrofit;
    private Button mUserInfoBtn;
    private TextView mInfoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        mUserInfoBtn = findViewById(R.id.getInfo);
        mInfoText = findViewById(R.id.infoText);
        mUserInfoBtn.setOnClickListener(this);
    }

    @Override
    protected ProgressDialog initProgress() {
        return new ProgressDialog(this);
    }

    @Override
    protected UserInfoPresenter loadPresenter() {
        return new UserInfoPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    public void showLoadingDialog() {
        progressDialog.show();
    }

    @Override
    public void dissLoadingDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showData(List<UserBean> list) {
        Log.e("tag", "list" + list.get(0).toString());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getInfo:
                userInfoPresenter.initData("Guolei1130");
                break;
        }
    }
}
