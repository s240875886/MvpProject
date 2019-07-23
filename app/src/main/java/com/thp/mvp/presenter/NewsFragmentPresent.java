package com.thp.mvp.presenter;

import com.thp.mvp.base.BaseView;
import com.thp.mvp.networking.NetService;
import com.thp.mvp.presenter.constract.NewsFragmentConstract;


import javax.inject.Inject;


/**
 * Created by thp on 2019/5/24
 */
public class NewsFragmentPresent implements NewsFragmentConstract.Present {

    private NetService netService;
    private NewsFragmentConstract.View view;

    @Inject
    public NewsFragmentPresent(NetService netService, BaseView baseView) {
        this.netService = netService;
        this.view = (NewsFragmentConstract.View) baseView;
    }


    @Override
    public void initNewsData(String type, String id, int startPage) {
        netService.getNewsList(type, id, startPage);
    }

    @Override
    public void attachView(NewsFragmentConstract.View baseView) {

    }

    @Override
    public void detachView() {

    }
}
