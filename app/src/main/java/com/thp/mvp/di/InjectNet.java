package com.thp.mvp.di;


import android.content.Context;

import com.thp.mvp.di.component.DaggerBaseComponent;
import com.thp.mvp.di.component.DaggerNetComponent;
import com.thp.mvp.di.component.NetComponent;
import com.thp.mvp.di.module.NetModule;

/**
 * Created by Phil on 2017/2/20.
 */

public class InjectNet {

    private static NetComponent injectNet;

    public static NetComponent getNetComponent(Context mContext) {
        if (injectNet == null) {
            injectNet = DaggerNetComponent.builder()
                    .netModule(new NetModule(mContext))
                    .build();
        }
        return injectNet;
    }

}
