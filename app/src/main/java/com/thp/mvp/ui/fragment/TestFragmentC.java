package com.thp.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thp.mvp.R;
import com.thp.mvp.base.MyFragment;
import com.thp.mvp.ui.activity.HomeActivity;


/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 项目炫酷效果示例
 */
public final class TestFragmentC extends MyFragment<HomeActivity> {



    public static TestFragmentC newInstance() {
        return new TestFragmentC();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_c;
    }

    @Override
    protected int getTitleId() {
        return R.id.tb_test_c_title;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}