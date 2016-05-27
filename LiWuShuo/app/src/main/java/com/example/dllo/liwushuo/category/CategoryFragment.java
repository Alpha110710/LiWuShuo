package com.example.dllo.liwushuo.category;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.dllo.liwushuo.MainActivity;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.category.adapter.CategoryViewpagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/19.
 */
public class CategoryFragment extends BaseFragment {
    public ViewPager categoryViewpager;
    private CategoryViewpagerAdapter adapter;
    private ArrayList<Fragment> fragments;

    @Override
    public int setLayout() {
        return R.layout.fragment_category;
    }

    @Override
    public void initView(View view) {
        categoryViewpager = findView(R.id.category_viewpager);
    }

    @Override
    public void initData() {
        adapter = new CategoryViewpagerAdapter(getChildFragmentManager());
        fragments = new ArrayList<>();
        GiftCategoryFragment giftCategoryFragment = new GiftCategoryFragment();
        RaidersCategoryFragment raidersCategoryFragment = new RaidersCategoryFragment();

        fragments.add(raidersCategoryFragment);
        fragments.add(giftCategoryFragment);
        adapter.setFragments(fragments);

        categoryViewpager.setAdapter(adapter);
        judgeViewpager();


    }


    //根据不同的viewPager状态,设置不同的bar
    private void judgeViewpager() {
        categoryViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {


                    ((MainActivity) getActivity()).changecb(true);
                }
                if (position == 1) {

                    ((MainActivity) getActivity()).changecb(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
