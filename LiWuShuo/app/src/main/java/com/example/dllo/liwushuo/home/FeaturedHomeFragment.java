package com.example.dllo.liwushuo.home;


import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.home.adapter.CarouselHomeViewpagerAdapter;
import com.example.dllo.liwushuo.home.adapter.ListviewFeatureHomeAdapter;
import com.example.dllo.liwushuo.home.adapter.RecyclerviewFeatureHomeAdapter;
import com.example.dllo.liwushuo.home.bean.ListviewBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.tool.App;
import com.example.dllo.liwushuo.tool.NetTool;
import com.google.gson.Gson;


/**
 * Created by dllo on 16/5/21.
 */
public class FeaturedHomeFragment extends BaseFragment {
    private ViewPager carouselHomeViewpager;
    private CarouselHomeViewpagerAdapter adapter;
    private Handler handler;
    private RecyclerView homeFeaturedRecyclerview;
    private RecyclerviewFeatureHomeAdapter recyclerviewFeatureHomeAdapter;

    private NetTool netTool = new NetTool();
    private ListView homeFeaturedListView;
    private ListviewFeatureHomeAdapter listviewFeatureHomeAdapter;


    @Override
    public int setLayout() {
        return R.layout.fragment_home_featured;
    }

    @Override
    public void initView(View view) {

        homeFeaturedListView = (ListView) view.findViewById(R.id.home_featured_listView);
    }

    @Override
    public void initData() {


        listviewFeatureHomeAdapter = new ListviewFeatureHomeAdapter();
        netTool.getAnalysis(URLValues.HOME_CELL, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                ListviewBean listviewBean = gson.fromJson(response, ListviewBean.class);
                listviewFeatureHomeAdapter.setListviewBean(listviewBean);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_home_featured_listview_header, null);
        carouselHomeViewpager = (ViewPager) view.findViewById(R.id.carousel_home_viewpager);
        homeFeaturedRecyclerview = (RecyclerView) view.findViewById(R.id.home_featured_recyclerview);
        //轮播模块
        carouselModule();

        //小正方形模块
        recyclerModule();
        homeFeaturedListView.addHeaderView(view);
        homeFeaturedListView.setAdapter(listviewFeatureHomeAdapter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    //initData()轮播图模块
    private void carouselModule() {
        adapter = new CarouselHomeViewpagerAdapter(context);

        //解析轮播图
        netTool.anlysisCarousel(adapter);
        //设置适配器

        carouselHomeViewpager.setAdapter(adapter);
        //设置轮播图
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                carouselHomeViewpager.setCurrentItem(msg.what);
                return false;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(i++);
                }
            }
        }).start();
    }

    //initData的recyclerview模块
    private void recyclerModule() {
        recyclerviewFeatureHomeAdapter = new RecyclerviewFeatureHomeAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(App.context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homeFeaturedRecyclerview.setLayoutManager(manager);
        homeFeaturedRecyclerview.setAdapter(recyclerviewFeatureHomeAdapter);
        netTool.anlysisRecyclerview(recyclerviewFeatureHomeAdapter);
    }


}
