package com.thp.mvp.di.component;

import com.thp.mvp.di.module.BaseModule;
import com.thp.mvp.di.scope.ActivityScope;
import com.thp.mvp.di.scope.FragmentScope;
import com.thp.mvp.ui.fragment.NewsFragment;

import dagger.Component;

/**
 * Created by thp on 2019/6/1
 */
@ActivityScope
@FragmentScope
@Component(dependencies = NetComponent.class, modules = BaseModule.class)
public interface BaseComponent {
    void inject(NewsFragment newsFragment);
}
