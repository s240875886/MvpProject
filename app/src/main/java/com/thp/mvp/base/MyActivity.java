package com.thp.mvp.base;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.WindowManager;

import com.gyf.barlibrary.ImmersionBar;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.hjq.toast.ToastUtils;
import com.thp.base.BaseActivity;
import com.thp.mvp.MyApplication;
import com.thp.mvp.di.InjectNet;
import com.thp.mvp.di.component.BaseComponent;
import com.thp.mvp.di.component.DaggerBaseComponent;
import com.thp.mvp.di.module.BaseModule;
import com.thp.mvp.other.StatusManager;
import com.thp.mvp.utils.ActivityStackManager;
import com.thp.mvp.utils.EventBusManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by thp on 2019/5/22
 */
public abstract class MyActivity extends BaseActivity implements OnTitleBarListener {
    private ImmersionBar mImmersionBar;//状态栏沉浸
    private Unbinder mButterKnife; // ButterKnife 注解

    @Override
    protected void initActivity() {
        super.initActivity();
        initImmersion();
        ActivityStackManager.getInstance().onActivityCreated(this);
    }

    @Override
    protected void initLayout() {
        super.initLayout();
        // 初始化标题栏的监听
        if (getTitleId() > 0) {
            if (findViewById(getTitleId()) instanceof TitleBar) {
                ((TitleBar) findViewById(getTitleId())).setOnTitleBarListener(this);
            }
        }

        mButterKnife = ButterKnife.bind(this);
        EventBusManager.register(this);
        initOrientation();
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersion() {
        //初始化沉浸式状态栏
        if (isStatusBarEnabled()) {
            statusBarConfig().init();

            //设置标题栏
            if (getTitleId() > 0) {
                ImmersionBar.setTitleBar(this, findViewById(getTitleId()));
            }
        }
    }

    /**
     * 是否使用沉浸式状态栏
     */
    public boolean isStatusBarEnabled() {
        return true;
    }

    /**
     * 获取状态栏沉浸的配置对象
     */
    public ImmersionBar getStatusBarConfig() {
        return mImmersionBar;
    }

    /**
     * 初始化沉浸式状态栏
     */
    protected ImmersionBar statusBarConfig() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this)
                .statusBarDarkFont(statusBarDarkFont())    //默认状态栏字体颜色为黑色
                .keyboardEnable(false, WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                        | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
        return mImmersionBar;
    }

    /**
     * 获取状态栏字体颜色
     */
    public boolean statusBarDarkFont() {
        //返回false表示白色字体
        return true;
    }

    /**
     * 初始化横竖屏方向，会和 LauncherTheme 主题样式有冲突，注意不要同时使用
     */
    protected void initOrientation() {
        // 当前 Activity 不能是透明的并且没有指定屏幕方向，默认设置为竖屏
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    /**
     * 设置标题栏的标题
     */
    @Override
    public void setTitle(int titleId) {
        setTitle(getText(titleId));
    }

    /**
     * 设置标题栏的标题
     */
    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        TitleBar titleBar = getTitleBar();
        if (titleBar != null) {
            titleBar.setTitle(title);
        }
    }

    @Nullable
    public TitleBar getTitleBar() {
        if (getTitleId() > 0 && findViewById(getTitleId()) instanceof TitleBar) {
            return findViewById(getTitleId());
        }
        return null;
    }


    @Override
    public void onLeftClick(View v) {

    }

    @Override
    public void onTitleClick(View v) {

    }

    @Override
    public void onRightClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) mImmersionBar.destroy();
        if (mButterKnife != null) mButterKnife.unbind();
        EventBusManager.unregister(this);
        ActivityStackManager.getInstance().onActivityDestroyed(this);
    }

    /**
     * 显示吐司
     */
    public void toast(CharSequence s) {
        ToastUtils.show(s);
    }

    public void toast(@StringRes int id) {
        ToastUtils.show(getString(id));
    }

    public void toast(Object object) {
        ToastUtils.show(object);
    }

    /**
     * 获取当前的 Application 对象
     */
    public final MyApplication getMyApplication() {
        return (MyApplication) getApplication();
    }

    private final StatusManager mStatusManager = new StatusManager();

    /**
     * 显示加载中
     */
    public void showLoading() {
        mStatusManager.showLoading(this);
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
        mStatusManager.showEmpty(getContentView());
    }

    /**
     * 显示错误提示
     */
    public void showError() {
        mStatusManager.showError(getContentView());
    }

    /**
     * 显示自定义提示
     */
    public void showLayout(@DrawableRes int iconId, @StringRes int textId) {
        mStatusManager.showLayout(getContentView(), iconId, textId);
    }

    public void showLayout(Drawable drawable, CharSequence hint) {
        mStatusManager.showLayout(getContentView(), drawable, hint);
    }

    public BaseComponent getBaseComponent(BaseView view) {
        //需要在BaseeComponent中配置activity
        BaseComponent component = DaggerBaseComponent.builder()
                .netComponent(InjectNet.getNetComponent(getApplicationContext()))
                .baseModule(new BaseModule(view))
                .build();
        return component;
    }
}
