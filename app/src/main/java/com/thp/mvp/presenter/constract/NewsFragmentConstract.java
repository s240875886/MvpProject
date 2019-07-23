package com.thp.mvp.presenter.constract;

import com.thp.mvp.base.BasePresenter;
import com.thp.mvp.base.BaseView;


/**
 * Created by thp on 2019/6/10
 */
public class NewsFragmentConstract {
    public interface View extends BaseView {

    }

    public interface Present extends BasePresenter<View> {
        void initNewsData(String type, String id, int startPage);

    }
}
