package com.thp.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


import com.thp.mvp.ui.fragment.NewsFragment;
import com.thp.mvp.ui.fragment.TestFragmentB;
import com.thp.mvp.ui.fragment.TestFragmentC;
import com.thp.mvp.ui.fragment.TestFragmentD;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    private List<Fragment> fragments = new ArrayList<>();

    public MainAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(NewsFragment.newInstance());
        fragments.add(TestFragmentB.newInstance());
        fragments.add(TestFragmentC.newInstance());
        fragments.add(TestFragmentD.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}