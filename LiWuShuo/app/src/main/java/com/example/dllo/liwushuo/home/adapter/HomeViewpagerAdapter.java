package com.example.dllo.liwushuo.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.dllo.liwushuo.home.bean.RollChannelBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/21.
 */
public class HomeViewpagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private ArrayList<RollChannelBean.DataBean.ChannelsBean> titles;

    public void setTitles(ArrayList<RollChannelBean.DataBean.ChannelsBean> titles) {
        this.titles = titles;
        Log.d("HomeViewpagerAdapter", titles.get(3).getName());
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }


    public HomeViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments == null ? null : fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position).getName();
    }
}
