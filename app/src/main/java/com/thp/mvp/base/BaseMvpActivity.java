package com.thp.mvp.base;

import android.os.Bundle;

import com.thp.base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by thp on 2019/5/24
 * MVP的Activity基类：
 * 纯粹的 MVP 包装，不要增加任何View层基础功能
 * 如果要添加基类功能，请在{@link MyActivity} 中添加
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends MyActivity {
    @Inject
    protected P basePresenter;

    @Override
    protected void initView() {
        initInject();
        if (null != basePresenter) {
            basePresenter.attachView((BaseView) this);
        }
    }

    protected abstract void initInject();

    @Override
    protected void onDestroy() {
        if (null != basePresenter) {
            basePresenter.detachView();
            basePresenter = null;
        }
        super.onDestroy();
    }


}
