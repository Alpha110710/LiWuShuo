package com.example.dllo.liwushuo.home;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.home.adapter.GridviewPopupFeatureAdapter;
import com.example.dllo.liwushuo.home.adapter.HomeViewpagerAdapter;
import com.example.dllo.liwushuo.home.bean.RollChannelBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.tool.NetTool;
import com.google.gson.Gson;

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
    private CheckBox homeTablayoutSelect;
    private PopupWindow homePopup;
    private TextView homeChangeTv;
    private NetTool netTool;
    private GridviewPopupFeatureAdapter gridviewPopupFeatureAdapter;
    private RollChannelBean rollChannelBean;


    @Override
    public int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        homeTablayout = (TabLayout) view.findViewById(R.id.home_tablayout);
        homeViewpager = findView(R.id.home_viewpager);
        homeTablayoutSelect = findView(R.id.home_tablayout_select);
        homeChangeTv = findView(R.id.home_change_tv);

    }

    @Override
    public void initData() {


        adapter = new HomeViewpagerAdapter(getChildFragmentManager());
        featuredHomeFragment = new FeaturedHomeFragment();

        //设置解析标题
        anlysisTitles();

        homeViewpager.setAdapter(adapter);
        homeTablayout.setupWithViewPager(homeViewpager);
        homeTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        homeTablayoutSelect.setOnClickListener(this);


        homeChangeTv.setVisibility(View.GONE);
        homeTablayout.setVisibility(View.VISIBLE);

        //设置popupwindow
        initHomePopup();


    }


    //    设置popupwindow
    private void initHomePopup() {
        gridviewPopupFeatureAdapter = new GridviewPopupFeatureAdapter(context);
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_select_popup, null);
        //参数1 view 参数4焦点
        homePopup = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        GridView gridView = (GridView) view.findViewById(R.id.home_popup_gridview_select);
        gridView.setAdapter(gridviewPopupFeatureAdapter);

        homePopup.setAnimationStyle(R.style.anim_popupwindow);
        gridView.setOnItemClickListener(this);
        //设置popupwindow点击任意地址不可见需设置这个方法
        homePopup.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_about));
        homePopup.setOutsideTouchable(true);

        homePopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                homeTablayoutSelect.setChecked(false);
                homeChangeTv.setVisibility(View.GONE);
                homeTablayout.setVisibility(View.VISIBLE);
            }
        });

    }

    //gridView监听事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //checkBox监听事件 使popupwindow显示
            case R.id.home_tablayout_select:
                homePopup.showAsDropDown(homeTablayout);
                homeChangeTv.setVisibility(View.VISIBLE);
                homeTablayout.setVisibility(View.GONE);

                break;
        }


    }


    //解析Tablauoyt标题
    private RollChannelBean anlysisTitles() {

        netTool = new NetTool();
        netTool.getAnalysis(URLValues.ROLL_CHANNEL, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                rollChannelBean = gson.fromJson(response, RollChannelBean.class);

                //给网格布局适配器发值
                gridviewPopupFeatureAdapter.setRollChannelBean(rollChannelBean);

                //将实体类中的数据设
                // 置给title,tablauout的标题
                adapter.setTitles((ArrayList<RollChannelBean.DataBean.ChannelsBean>) rollChannelBean.getData().getChannels());

//                //直接设置fragment数量
//                fragments = new ArrayList<>();
//                fragments.add(featuredHomeFragment);
//                rollChannelBean.getData().getChannels().get(homeViewpager.getCurrentItem()) .setEditable(false);
//
//
//                //数量与title数量差1
//                for (int i = 1; i < rollChannelBean.getData().getChannels().size() ; i++) {
//                    String url = URLValues.ITEM_BEFORE+ rollChannelBean.getData().getChannels().get(i).getId()+URLValues.ITEM_AFTER;
//                    fragments.add(NormalHomeFragment.createFragment(url));
//
//                }
//                adapter.setFragments(fragments);
                adapter.setBean(rollChannelBean);


            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

        return rollChannelBean;

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position < rollChannelBean.getData().getChannels().size()) {
            gridviewPopupFeatureAdapter.setRedLine(position);
            homePopup.dismiss();
            //设置对应频道的当前界面
            homeViewpager.setCurrentItem(position);
            homeChangeTv.setVisibility(View.GONE);
            homeTablayout.setVisibility(View.VISIBLE);
        }

    }

}
