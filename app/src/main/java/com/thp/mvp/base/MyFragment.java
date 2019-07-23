package com.thp.mvp.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.hjq.bar.TitleBar;
import com.thp.base.BaseLazyFragment;
import com.hjq.toast.ToastUtils;
import com.thp.mvp.di.InjectNet;
import com.thp.mvp.di.component.BaseComponent;
import com.thp.mvp.di.component.DaggerBaseComponent;
import com.thp.mvp.di.module.BaseModule;
import com.thp.mvp.other.StatusManager;
import com.thp.mvp.utils.EventBusManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by thp on 2019/5/22
 */
public abstract class MyFragment<T extends MyActivity> extends BaseLazyFragment<T> {
    private ImmersionBar mImmersionBar; // 状态栏沉浸
    private Unbinder mButterKnife; // View注解

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mButterKnife = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initFragment() {
        super.initFragment();
        initImmersion();
        EventBusManager.register(this);

    }

    @Override
    protected void initView() {

    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersion() {

        // 初始化沉浸式状态栏
        if (isStatusBarEnabled()) {
            statusBarConfig().init();

            // 设置标题栏
            if (getTitleId() > 0) {
                ImmersionBar.setTitleBar(mActivity, findViewById(getTitleId()));
            }
        }
    }

    /**
     * 是否在Fragment使用沉浸式
     */
    public boolean isStatusBarEnabled() {
        return false;
    }

    /**
     * 获取状态栏沉浸的配置对象
     */
    protected ImmersionBar getStatusBarConfig() {
        return mImmersionBar;
    }

    /**
     * 初始化沉浸式
     */
    private ImmersionBar statusBarConfig() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this)
                .statusBarDarkFont(statusBarDarkFont())    //默认状态栏字体颜色为黑色
                .keyboardEnable(true);  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
        return mImmersionBar;
    }

    /**
     * 获取状态栏字体颜色
     */
    protected boolean statusBarDarkFont() {
        //返回true表示黑色字体
        return true;
    }

    @Nullable
    public TitleBar getTitleBar() {
        if (getTitleId() > 0 && findViewById(getTitleId()) instanceof TitleBar) {
            return findViewById(getTitleId());
        }
        return null;
    }

    /**
     * 显示吐司
     */
    public void toast(CharSequence s) {
        ToastUtils.show(s);
    }

    public void toast(int id) {
        ToastUtils.show(id);
    }

    public void toast(Object object) {
        ToastUtils.show(object);
    }

    private final StatusManager mStatusManager = new StatusManager();

    /**
     * 显示加载中
     */
    public void showLoading() {
        mStatusManager.showLoading(getBindingActivity());
    }

    /**
     * 显示加载完成
     */
    public void showComplete() {
        mStatusManager.showComplete();
    }

    /**
     * 显示空提示
     */
    public void showEmpty() {
        mStatusManager.showEmpty(getView());
    }

    /**
     * 显示错误提示
     */
    public void showError() {
        mStatusManager.showError(getView());
    }

    /**
     * 显示自定义提示
     */
    public void showLayout(@DrawableRes int iconId, @StringRes int textId) {
        mStatusManager.showLayout(getView(), iconId, textId);
    }

    public void showLayout(Drawable drawable, CharSequence hint) {
        mStatusManager.showLayout(getView(), drawable, hint);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) mImmersionBar.destroy();
        if (mButterKnife != null) {
            mButterKnife.unbind();
        }
        EventBusManager.unregister(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isStatusBarEnabled() && isLazyLoad()) {
            // 重新初始化状态栏
            statusBarConfig().init();
        }
    }

    public BaseComponent getBaseComponent(BaseView view) {
        //需要在BaseeComponent中配置activity
        BaseComponent component = DaggerBaseComponent.builder()
                .netComponent(InjectNet.getNetComponent(getActivity()))
                .baseModule(new BaseModule(view))
                .build();
        return component;
    }
}
