package com.example.dllo.liwushuo.home;

import android.animation.ObjectAnimator;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by dllo on 16/5/19.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private TabLayout homeTablayout;
    private ViewPager homeViewpager;
    private FeaturedHomeFragment featuredHomeFragment;
    private HomeViewpagerAdapter adapter;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private CheckBox homeTablayoutSelect;
    private PopupWindow homePopup;
    private TextView homeChangeTv;

    @Override
    public int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        homeTablayout = findView(R.id.home_tablayout);
        homeViewpager = findView(R.id.home_viewpager);
        homeTablayoutSelect = findView(R.id.home_tablayout_select);
        homeChangeTv = findView(R.id.home_change_tv);
    }

    @Override
    public void initData() {
        adapter = new HomeViewpagerAdapter(getChildFragmentManager());
        featuredHomeFragment = new FeaturedHomeFragment();

        //设置fragments和titles
        initFragments();


        adapter.setFragments(fragments);
        adapter.setTitles(titles);

        adapter.setFragments(fragments);

        homeViewpager.setAdapter(adapter);
        homeTablayout.setupWithViewPager(homeViewpager);
        homeTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        homeTablayoutSelect.setOnClickListener(this);

        ArrayList<HashMap<String, Object>> lstitem = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("text", "NO." + i);
            lstitem.add(map);
        }

        //加循环使网格正好为4的倍数
        for (int i = 0; i < 3; i++) {
            if (lstitem.size() % 4 != 0) {
                HashMap<String, Object> l = new HashMap<>();
                l.put("text", "");
                lstitem.add(l);
            }
        }


        initHomePopup(lstitem);
        homeChangeTv.setVisibility(View.GONE);
        homeTablayout.setVisibility(View.VISIBLE);


    }


    //fragment设置
    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(featuredHomeFragment);

        for (int i = 0; i < 10; i++) {
            fragments.add(NormalHomeFragment.createFragment("第" + i + "个界面"));

        }
        titles = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            titles.add(i + "");
        }
    }


    //设置popupwindow
    private void initHomePopup(ArrayList<HashMap<String, Object>> lstItem) {
        homePopup = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_select_popup, null);
        GridView gridView = (GridView) view.findViewById(R.id.home_popup_gridview_select);
        SimpleAdapter adapter = new SimpleAdapter(context, lstItem, R.layout.item_home_popup_gridview, new String[]{"text"}, new int[]{R.id.item_home_popup_gridview_tv});
        gridView.setAdapter(adapter);
        homePopup.setContentView(view);
        homePopup.setAnimationStyle(R.style.anim_popupwindow);
        gridView.setOnItemClickListener(this);
    }

    //
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //checkBox监听事件 使popupwindow显示
            case R.id.home_tablayout_select:
                if (homePopup.isShowing()) {
                    homePopup.dismiss();
                    homeChangeTv.setVisibility(View.GONE);
                    homeTablayout.setVisibility(View.VISIBLE);

                } else {
                    homePopup.showAsDropDown(homeTablayout);
                    homeChangeTv.setVisibility(View.VISIBLE);
                    homeTablayout.setVisibility(View.GONE);
                }
                break;
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (homePopup.isShowing()) {
            homePopup.dismiss();
            homeChangeTv.setVisibility(View.GONE);
            homeTablayout.setVisibility(View.VISIBLE);
        }
    }

    //gridView监听事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(context, "position:" + position, Toast.LENGTH_SHORT).show();
    }
}
