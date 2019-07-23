package com.thp.mvp.base;

import javax.inject.Inject;

/**
 * Created by thp on 2019/6/10
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends MyFragment {
    @Inject
    protected P basePresenter;

    @Override
    protected void initFragment() {
        initInject();
        if (null != basePresenter) {
            basePresenter.attachView((BaseView) this);
        }
        super.initFragment();
    }


    protected abstract void initInject();

    @Override
    public void onDestroy() {
        if (null != basePresenter) {
            basePresenter.detachView();
            basePresenter = null;
        }
        super.onDestroy();
    }

}
