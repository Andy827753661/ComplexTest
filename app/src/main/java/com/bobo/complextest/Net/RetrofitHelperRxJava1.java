
package com.bobo.complextest.Net;

import com.bobo.complextest.Constant.Constant;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/3/13.
 */

public class RetrofitHelperRxJava1 {

    private Retrofit mRetrofit;

    private OkHttpClient mOkHttpClient;

    protected  Retrofit getRetrofit() {
        if (mRetrofit == null) {
            if (mOkHttpClient == null) {
                mOkHttpClient = new OkHttp3Helper().getOkHttpClient();
            }
            mRetrofit = new Retrofit.Builder()
                    // 设置服务器路径
                    .baseUrl(Constant.BASE_URL + "/")
                    // 添加转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    // 添加回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    // 设置使用okhttp网络请求
                    .client(mOkHttpClient).build();
        }
        return mRetrofit;
    }
}
