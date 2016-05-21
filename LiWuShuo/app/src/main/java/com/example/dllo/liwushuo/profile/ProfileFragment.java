package com.example.dllo.liwushuo.profile;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.category.GiftCategoryFragment;
import com.example.dllo.liwushuo.category.RaidersCategoryFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/19.
 */
public class ProfileFragment extends BaseFragment {
    private TabLayout profileTablayout;
    private ViewPager profileViewpager;
    private ProfileViewpagerAdapter adapter;
    private GiftProfileFragment giftProfileFragment;
    private RaidersProfileFragment raidersProfileFragment;
    private ArrayList<Fragment> fragments;
    @Override
    public int setLayout() {
        return R.layout.fragment_profile;
    }

    @Override
    public void initView(View view) {
        profileTablayout = findView(R.id.profile_tablayout);
        profileViewpager = findView(R.id.profile_viewpager);

    }

    @Override
    public void initData() {
        giftProfileFragment = new GiftProfileFragment();
        raidersProfileFragment = new RaidersProfileFragment();
        adapter = new ProfileViewpagerAdapter(getChildFragmentManager());

        fragments = new ArrayList<>();
        fragments.add(giftProfileFragment);
        fragments.add(raidersProfileFragment);
        adapter.setFragments(fragments);
        profileViewpager.setAdapter(adapter);
        profileTablayout.setupWithViewPager(profileViewpager);



    }
}
