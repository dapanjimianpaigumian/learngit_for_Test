package com.yulu.zhaoxinpeng.mytreasuremap.net;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2017/3/29.
 */

public class NetClient {
    private HttpLoggingInterceptor mInterceptor;
    private OkHttpClient mOkHttpClient;
    private static NetClient mNetClient;

    /**
     *
     */
    public NetClient(){
        //对OKHTTPClient 设置事件拦截器
        mInterceptor = new HttpLoggingInterceptor();
        mInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        mOkHttpClient = new OkHttpClient.Builder().addInterceptor(mInterceptor).build();
    }

    public static synchronized NetClient getInstance(){
        if (mNetClient==null) {
            mNetClient=new NetClient();
        }
        return mNetClient;
    }

    public Call getData(){
        Request request = new Request.Builder()
                .get()
                .url("http://www.baidu.com")
                .addHeader("content-type", "html")
                .addHeader("context-length", "1024")
                .build();
        return mOkHttpClient.newCall(request);
    }
}
