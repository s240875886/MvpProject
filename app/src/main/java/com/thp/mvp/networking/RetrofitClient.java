package com.thp.mvp.networking;

import android.content.Context;
import android.util.Log;

import com.thp.mvp.BuildConfig;
import com.thp.mvp.MyApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by thp on 2019/5/23
 */
public class RetrofitClient {
    private static final int DEFAULT_TIME_OUT = 10;//超时时间 10s
    private static final int DEFAULT_READ_TIME_OUT = 30;
    private final Retrofit mClient;


    public RetrofitClient(String baseUrl, final Context mContext) {
        /**
         * 自定义日志拦截器
         */
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog", "retrofitBack = " + message);
            }
        });
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        // 指定缓存路径,缓存大小100Mb
        Cache cache = new Cache(new File(MyApplication.getContext().getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);
        OkHttpClient okClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)//是否断网重新连接
                .cache(cache)
                .addInterceptor(interceptor)
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)//读操作超时时间
                .writeTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS)//写操作 超时时间
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request request = chain.request().newBuilder()
                                .addHeader("User-Agent", "Mozilla/5.0 (compatible; mobile; ios;android; zzb;fwpt;)")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .addNetworkInterceptor((new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        int maxAge = 60;//缓存失效时间，单位为秒
                        Request request = chain.request().newBuilder()
                                .removeHeader("Pragma")//清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                                .header("Cache-Control", "public ,max-age=" + maxAge)
                                .build();
                        return chain.proceed(request);
                    }
                })).build();
        mClient = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public <T> T create(Class<T> apiInterfaceClass) {
        return mClient.create(apiInterfaceClass);
    }
}
