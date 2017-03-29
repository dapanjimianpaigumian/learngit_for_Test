package com.yulu.zhaoxinpeng.mytreasuremap.activity.user.login;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2017/3/28.
 */
// 登录的业务类：帮View去做业务请求
public class LoginPresenter {
    /**
     * 业务类中间涉及到的视图怎么处理？
     * 1. 创建LoginActivity，不能采用这种方式
     * 2. 接口回调的方式
     * A 接口  里面有一个a()
     * AImpl是A接口的实现类  实现a()
     * 使用：A a = new Aimpl();
     * this.a = a;
     * a.a();
     * <p>
     * 接口创建好了，怎么初始化？
     * Activity实现视图接口
     */
    private LoginView mLoginView;
    private HttpLoggingInterceptor mInterceptor;
    private OkHttpClient mOkHttpClient;

    public LoginPresenter(LoginView loginView) {
        this.mLoginView = loginView;
    }

    // 登录的业务
    public void Login() {

        //对OKHTTPClient 设置事件拦截器
        mInterceptor = new HttpLoggingInterceptor();
        mInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        mOkHttpClient = new OkHttpClient.Builder().addInterceptor(mInterceptor).build();

        Request request = new Request.Builder().get()
                .url("http://www.baidu.com")
                .addHeader("content-type", "html")
                .addHeader("context-length", "1024")
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("okhttp","onfailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("okhttp","onresponse"+response.code());
                if (response.isSuccessful()) {
                     Log.e("响应成功","响应体数据"+response.body().string());
                }
            }
        });
    }

}
