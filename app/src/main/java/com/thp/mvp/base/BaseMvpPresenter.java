package com.thp.mvp.base;

/**
 * Created by thp on 2019/5/24
 */
public class BaseMvpPresenter<T extends BaseView> implements BasePresenter<T> {
    protected T baseView;

    @Override
    public void attachView(T baseView) {
        this.baseView = baseView;
    }

    @Override
    public void detachView() {
        this.baseView = null;
    }
}
