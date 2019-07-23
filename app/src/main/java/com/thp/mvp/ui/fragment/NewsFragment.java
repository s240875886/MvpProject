package com.thp.mvp.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.thp.mvp.R;
import com.thp.mvp.base.BaseMvpFragment;
import com.thp.mvp.base.MyFragment;
import com.thp.mvp.model.NewsInfo;
import com.thp.mvp.networking.NetApi;
import com.thp.mvp.networking.RetrofitApi;
import com.thp.mvp.networking.RetrofitClient;
import com.thp.mvp.presenter.NewsFragmentPresent;
import com.thp.mvp.presenter.constract.NewsFragmentConstract;
import com.thp.mvp.ui.activity.HomeActivity;
import com.thp.mvp.ui.adapter.ViewPagerAdapter;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * create by thp
 * 新闻页面
 */
public class NewsFragment extends BaseMvpFragment<NewsFragmentPresent> implements NewsFragmentConstract.View {


    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @Inject
    NewsFragmentPresent present;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected int getTitleId() {
        return R.id.tb_test_a_title;
    }
    @Override
    protected void initView() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    protected void initInject() {
        getBaseComponent(this).inject(this);
        present.initNewsData("1", "2", 3);
    }

    @Override
    protected void initData() {


    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }


    public void onViewClicked32() {
        RetrofitClient client = new RetrofitClient(RetrofitApi.NEWS_HOST, mActivity);
        client.create(NetApi.class).getNewsList("headline", "T1348647909107", 1 * 20)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Map<String, List<NewsInfo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Map<String, List<NewsInfo>> stringListMap) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void showErrorMsg(String msg) {

    }
}