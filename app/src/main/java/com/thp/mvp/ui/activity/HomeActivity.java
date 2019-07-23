package com.thp.mvp.ui.activity;

import com.thp.mvp.R;
import com.thp.mvp.base.MyActivity;
import com.thp.mvp.ui.adapter.MainAdapter;
import com.thp.widget.NoScrollViewPager;
import com.yhao.floatwindow.FloatWindow;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import butterknife.BindView;

public class HomeActivity extends MyActivity {

    @BindView(R.id.mViewPager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator alphaIndicator;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected int getTitleId() {
        return 0;
    }

    @Override
    protected void initView() {
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mainAdapter);
        mViewPager.addOnPageChangeListener(mainAdapter);

        alphaIndicator.setViewPager(mViewPager);

        /*设置底部红点和消息数量*/
//        alphaIndicator.getTabView(0).showNumber(6);
//        alphaIndicator.getTabView(1).showNumber(888);
//        alphaIndicator.getTabView(2).showNumber(88);
//        alphaIndicator.getTabView(3).showPoint();
        FloatWindow.get().show();
    }

    @Override
    protected void initData() {

    }

}
