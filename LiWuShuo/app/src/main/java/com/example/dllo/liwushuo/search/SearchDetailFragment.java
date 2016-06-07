package com.example.dllo.liwushuo.search;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/6.
 */
public class SearchDetailFragment extends BaseFragment {

    private TabLayout searchDetailTablayout;
    private ViewPager searchDetailViewPager;
    private SearchDetailViewpagerAdapter searchDetailViewpagerAdapter;
    private SearchDetailGiftFragment searchDetailGiftFragment;
    private SearchDetailRaidersFragment searchDetailRaidersFragment;
    private ArrayList<Fragment> fragments;

    @Override
    public int setLayout() {
        return R.layout.fragment_search_detail;
    }

    @Override
    public void initView(View view) {
        searchDetailTablayout = (TabLayout) view.findViewById(R.id.search_detail_tablayout);
        searchDetailViewPager = (ViewPager) view.findViewById(R.id.search_detail_viewPager);

    }

    @Override
    public void initData() {
        searchDetailViewpagerAdapter = new SearchDetailViewpagerAdapter(getChildFragmentManager());

        fragments = new ArrayList<>();
        searchDetailGiftFragment = new SearchDetailGiftFragment();
        searchDetailRaidersFragment = new SearchDetailRaidersFragment();
        fragments.add(searchDetailGiftFragment);
        fragments.add(searchDetailRaidersFragment);

        searchDetailViewpagerAdapter.setFragments(fragments);
        searchDetailViewPager.setAdapter(searchDetailViewpagerAdapter);
        searchDetailTablayout.setupWithViewPager(searchDetailViewPager);

        //接editText的值  然后再传给下一集fragment
        Bundle bundle = getArguments();
        String key = bundle.getString("key");

        bundle.putString("key", key);
        searchDetailGiftFragment.setArguments(bundle);

    }
}
