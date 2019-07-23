package com.thp.mvp.di.module;

import android.content.Context;

import com.thp.mvp.networking.RetrofitApi;
import com.thp.mvp.networking.RetrofitClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thp on 2019/6/1
 */
@Module
public class NetModule {
    private final Context mContext;

    @Singleton
    public NetModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    RetrofitClient provideRetrofitClient() {
        return new RetrofitClient(RetrofitApi.NEWS_HOST, mContext);
    }
}
