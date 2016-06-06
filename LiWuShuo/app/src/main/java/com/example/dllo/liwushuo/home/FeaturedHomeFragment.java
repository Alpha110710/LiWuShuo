package com.example.dllo.liwushuo.home;


import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.category.RaidersDetailsUpActivity;
import com.example.dllo.liwushuo.home.adapter.CarouselHomeViewpagerAdapter;
import com.example.dllo.liwushuo.home.adapter.ListviewFeatureHomeAdapter;
import com.example.dllo.liwushuo.home.adapter.RecyclerviewFeatureHomeAdapter;
import com.example.dllo.liwushuo.home.bean.ListviewBean;
import com.example.dllo.liwushuo.home.bean.RecyclerviewBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.tool.App;
import com.example.dllo.liwushuo.tool.NetTool;
import com.example.dllo.liwushuo.view.XListView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 16/5/21.
 */
public class FeaturedHomeFragment extends BaseFragment implements AdapterView.OnItemClickListener, XListView.IXListViewListener {
    private ViewPager carouselHomeViewpager;
    private CarouselHomeViewpagerAdapter adapter;
    private Handler handler;
    private RecyclerView homeFeaturedRecyclerview;
    private RecyclerviewFeatureHomeAdapter recyclerviewFeatureHomeAdapter;
    private NetTool netTool = new NetTool();
    private XListView homeFeaturedListView;
    private ListviewFeatureHomeAdapter listviewFeatureHomeAdapter;
    private ArrayList<String> urlIds;
    private RecyclerviewBean recyclerviewBean;
    private ListviewBean listviewBean;
    private String nextUrl;


    @Override
    public int setLayout() {
        return R.layout.fragment_home_featured;
    }

    @Override
    public void initView(View view) {

        homeFeaturedListView = (XListView) view.findViewById(R.id.home_featured_listView);
    }

    @Override
    public void initData() {

        homeFeaturedListView.setOnItemClickListener(this);


        //listview模块 ,轮播模块和小正方形模块为listview的头
        listviewModule();

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_home_featured_listview_header, null);
        carouselHomeViewpager = (ViewPager) view.findViewById(R.id.carousel_home_viewpager);
        homeFeaturedRecyclerview = (RecyclerView) view.findViewById(R.id.home_featured_recyclerview);
        //轮播模块
        carouselModule();

        //小正方形模块
        recyclerModule();

        homeFeaturedListView.addHeaderView(view);
        homeFeaturedListView.setAdapter(listviewFeatureHomeAdapter);


        //recycler点击事件的处理
        initRecyclerOnclick();

        //实现下拉刷新和下拉加载需要这两个方法
        homeFeaturedListView.setXListViewListener(this);
        homeFeaturedListView.setPullLoadEnable(true);

    }


    //recycler点击事件的处理
    private void initRecyclerOnclick() {
        recyclerviewFeatureHomeAdapter.setHomeRecyclerVIewOnClickListener(new HomeRecyclerVIewOnClickListener() {
            @Override
            public void onClick(int position) {

                switch (position) {
                    case 1:
                        break;
                    case 2:
                        Intent intent1 = new Intent(context, HomeCalendarActivity.class);
                        startActivity(intent1);
                        break;
                    case 3:
                        break;
                    default:
                        //intent跳转  复用raiders小圆角矩形点击进入的activity  实体类url全部一致
                        Intent intent = new Intent();
                        intent.setClass(context, RaidersDetailsUpActivity.class);
                        recyclerviewBean = recyclerviewFeatureHomeAdapter.getRecyclerviewBean();

                        if (recyclerviewBean != null) {
                            String idBefore = recyclerviewBean.getData().getSecondary_banners().get(position).getTarget_url();
                            String id = Uri.parse(idBefore).getQueryParameter("topic_id");
                            intent.putExtra("raidersDetailUrl", id);
                            startActivity(intent);

                        }

                        break;
                }
            }
        });
    }


    //listview模块
    private void listviewModule() {
        listviewFeatureHomeAdapter = new ListviewFeatureHomeAdapter();
        netTool.getAnalysis(URLValues.HOME_CELL, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                listviewBean = gson.fromJson(response, ListviewBean.class);
                listviewFeatureHomeAdapter.setListviewBean(listviewBean);


                //遍历实体类将urlId加入到集合中
                urlIds = new ArrayList<String>();
                for (ListviewBean.DataBean.ItemsBean itemBean : listviewBean.getData().getItems()
                        ) {
                    urlIds.add(String.valueOf(itemBean.getId()));
                }

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //获取真实的pos  因为加头
        int pos = (int) parent.getAdapter().getItemId(position);
        //给下一个页面传值
        Intent intent = new Intent(context, HomeDetailActivity.class);
        if (urlIds != null) {
            intent.putStringArrayListExtra("urlId", urlIds);
            intent.putExtra("urlPos", pos);
        }
        startActivity(intent);
    }


    //下拉刷新方法
    @Override
    public void onRefresh() {
        netTool.getAnalysis(URLValues.HOME_CELL, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                listviewBean = gson.fromJson(response, ListviewBean.class);
                listviewFeatureHomeAdapter.setListviewBean(listviewBean);

                //遍历实体类将urlId加入到集合中
                urlIds = new ArrayList<String>();
                for (ListviewBean.DataBean.ItemsBean itemBean : listviewBean.getData().getItems()
                        ) {
                    urlIds.add(String.valueOf(itemBean.getId()));
                }
                onLoad();

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }

    //上拉加载方法
    @Override
    public void onLoadMore() {
        nextUrl = listviewBean.getData().getPaging().getNext_url();

        netTool.getAnalysis(nextUrl, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                listviewBean = gson.fromJson(response, ListviewBean.class);
                List<ListviewBean.DataBean.ItemsBean> itemsBeans = listviewBean.getData().getItems();
                listviewFeatureHomeAdapter.addItemBean(itemsBeans);
                onLoad();

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }

    //onload方法  取消刷新和加载
    private void onLoad() {
        homeFeaturedListView.stopRefresh();
        homeFeaturedListView.stopLoadMore();
        homeFeaturedListView.setRefreshTime("刚刚");
    }


}
