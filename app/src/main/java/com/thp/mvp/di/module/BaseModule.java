package com.thp.mvp.di.module;

import com.thp.mvp.base.BaseView;
import com.thp.mvp.networking.RetrofitClient;
import com.thp.mvp.networking.NetService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thp on 2019/5/24
 */

@Module
public class BaseModule {

    private final BaseView mView;

    public BaseModule(BaseView baseView) {
        this.mView = baseView;
    }

    @Provides
    BaseView provideView() {
        return mView;
    }

    @Provides
    NetService provideService(RetrofitClient restClient) {
        return new NetService(restClient);
    }
}
