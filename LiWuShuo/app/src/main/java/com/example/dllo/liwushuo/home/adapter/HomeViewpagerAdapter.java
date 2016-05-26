package com.example.dllo.liwushuo.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.dllo.liwushuo.home.FeaturedHomeFragment;
import com.example.dllo.liwushuo.home.NormalHomeFragment;
import com.example.dllo.liwushuo.home.bean.RollChannelBean;
import com.example.dllo.liwushuo.net.URLValues;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/21.
 */
public class HomeViewpagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> fragments;
    private ArrayList<RollChannelBean.DataBean.ChannelsBean> titles;
    private RollChannelBean rollChannelBean;

    public void setTitles(ArrayList<RollChannelBean.DataBean.ChannelsBean> titles) {
        this.titles = titles;

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
        if(position == 0){
            return new FeaturedHomeFragment();
        }else {
            String url = URLValues.ITEM_BEFORE+ rollChannelBean.getData().getChannels().get(position).getId()+URLValues.ITEM_AFTER;
          //  fragments.add(NormalHomeFragment.createFragment(url));

            return NormalHomeFragment.createFragment(url);
        }
    }

    @Override
    public int getCount() {
        return rollChannelBean == null ? 0 : rollChannelBean.getData().getChannels().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position).getName();
    }

    public void setBean(RollChannelBean rollChannelBean) {
        this.rollChannelBean = rollChannelBean;
        notifyDataSetChanged();
    }
}
