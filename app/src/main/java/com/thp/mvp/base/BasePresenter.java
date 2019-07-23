package com.thp.mvp.base;

/**
 * Created by thp on 2019/5/24
 * presenter基类
 */
public interface BasePresenter<T extends BaseView> {

    void attachView(T baseView);

    void detachView();
}
