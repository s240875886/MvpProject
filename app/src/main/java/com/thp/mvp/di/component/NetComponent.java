package com.thp.mvp.di.component;

import com.thp.mvp.di.module.NetModule;
import com.thp.mvp.networking.RetrofitClient;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by thp on 2019/6/1
 */
@Singleton
@Component(modules = NetModule.class)
public interface NetComponent {
    RetrofitClient getRetrofitClient();
}
