package com.thp.mvp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import com.hjq.toast.ToastUtils;
import com.thp.mvp.ui.activity.CustomErrorActivity;
import com.thp.mvp.ui.activity.HomeActivity;
import com.thp.mvp.utils.EventBusManager;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.MoveType;
import com.yhao.floatwindow.Screen;

import cat.ereza.customactivityoncrash.config.CaocConfig;

/**
 * Created by thp on 2019/5/22
 */
public class MyApplication extends Application {

    private static Context aContext;

    @Override
    public void onCreate() {
        super.onCreate();
        aContext = getApplicationContext();
        initSDK(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    /**
     * 初始化一些第三方框架
     */
    public static void initSDK(Application application) {

        // 初始化吐司工具类
        ToastUtils.init(application);
        // 初始化 EventBus
        EventBusManager.init();
        //崩溃日志框架
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
                .minTimeBetweenCrashesMs(2000) //default: 3000
                .restartActivity(HomeActivity.class) //default: null (your app's launch activity)
                .errorActivity(CustomErrorActivity.class) //default: null (default error activity)
                .apply();
        ImageView imageView = new ImageView(aContext);
        imageView.setImageResource(R.mipmap.ic_launcher);

        FloatWindow
                .with(aContext)
                .setView(imageView)
                .setWidth(Screen.width, 0.2f) //设置悬浮控件宽高
                .setHeight(Screen.width, 0.2f)
                .setX(Screen.width, 0.8f)
                .setY(Screen.height, 0.3f)
                .setMoveType(MoveType.active, 100, -100)
                .setMoveStyle(500, new BounceInterpolator())
                .setFilter(true, HomeActivity.class)
//                .setViewStateListener(mViewStateListener)
//                .setPermissionListener(mPermissionListener)
                .setDesktopShow(true)
                .build();
    }

    public static Context getContext() {
        return aContext;

    }

}
