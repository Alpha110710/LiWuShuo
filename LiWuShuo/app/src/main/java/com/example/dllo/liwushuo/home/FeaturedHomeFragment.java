package com.example.dllo.liwushuo.home;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.category.RaidersDetailsUpActivity;
import com.example.dllo.liwushuo.home.adapter.CarouselHomeViewpagerAdapter;
import com.example.dllo.liwushuo.home.adapter.ListviewFeatureHomeAdapter;
import com.example.dllo.liwushuo.home.adapter.RecyclerviewFeatureHomeAdapter;
import com.example.dllo.liwushuo.home.bean.CarouselBean;
import com.example.dllo.liwushuo.home.bean.ListviewBean;
import com.example.dllo.liwushuo.home.bean.RecyclerviewBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.base.App;
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
    private CheckBoxBroadCastReceiver boxBroadCastReceiver;
    private LinearLayout carouselHomeViewpagerLlayout;
    private boolean threadAlive = true;
    private boolean userTouch = false;
    private int sleepTick;


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
        urlIds = new ArrayList<String>();


        //listview模块 ,轮播模块和小正方形模块为listview的头
        listviewModule();

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_home_featured_listview_header, null);
        carouselHomeViewpager = (ViewPager) view.findViewById(R.id.carousel_home_viewpager);
        homeFeaturedRecyclerview = (RecyclerView) view.findViewById(R.id.home_featured_recyclerview);
        carouselHomeViewpagerLlayout = (LinearLayout) view.findViewById(R.id.carousel_home_viewpager_llayout);

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
        homeFeaturedListView.setPullRefreshEnable(true);

        //注册广播
        boxBroadCastReceiver = new CheckBoxBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.dllo.liwushuo.profile.checkBox");
        context.registerReceiver(boxBroadCastReceiver, intentFilter);


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        context.unregisterReceiver(boxBroadCastReceiver);
        threadAlive = false;//让线程走完销毁
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
        listviewFeatureHomeAdapter = new ListviewFeatureHomeAdapter(context);
        netTool.getAnalysis(URLValues.HOME_CELL, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                listviewBean = gson.fromJson(response, ListviewBean.class);
                listviewFeatureHomeAdapter.setListviewBean(listviewBean);

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
//        netTool.anlysisCarousel(adapter);
        netTool.getAnalysis(URLValues.CAROUSEL, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                CarouselBean carouselBean = gson.fromJson(response, CarouselBean.class);
                adapter.setCarouselBean(carouselBean);

                /**
                 * 小圆点
                 */
                carouselHomeViewpagerLlayout.removeAllViews();
                for (int i = 0; i < carouselBean.getData().getBanners().size(); i++) {
                    CheckBox checkBox = new CheckBox(context);
                    Drawable drawable = getResources().getDrawable(R.drawable.selector_carouse_point);
                    checkBox.setBackground(drawable);
//                    ImageView imageView = new ImageView(context);
//                    imageView.setImageResource(R.mipmap.btn_check_normal);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(18, 18);
                    params.setMargins(8, 0, 8, 0);
                    params.weight = 1;
                    carouselHomeViewpagerLlayout.addView(checkBox, params);
                }
                ((CheckBox) carouselHomeViewpagerLlayout.getChildAt(0)).setChecked(true);

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

        //设置适配器
        carouselHomeViewpager.setAdapter(adapter);
        adapter.setViewPager(carouselHomeViewpager);
        adapter.setLinearLayout(carouselHomeViewpagerLlayout);
        //设置轮播图
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                //先获取当前的位置,再讲ViewPager刷新到下一页
                int current = carouselHomeViewpager.getCurrentItem();
                carouselHomeViewpager.setCurrentItem(current + 1);
                return false;
            }
        });

        //开启线程去执行轮播
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (threadAlive) {
                    //每隔3s刷新一次ViewPager的数据
                    for (sleepTick = 0; sleepTick < 3; sleepTick++)
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    if (!userTouch) {
                        handler.sendEmptyMessage(0);
                    }
                }
            }
        }).start();

        //当用户点击的时候就不会再触发发轮播图了
        //轮播图就会暂停轮播
        carouselHomeViewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //当用户触摸了轮播图的时候
                        userTouch = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        //当用户手指离开轮播图的时候
                        userTouch = false;
                        sleepTick = 0;//每次当用户抬起手指,都会重新开始计时
                        break;
                }
                return false;
            }
        });


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
        //原本为0 为了设置new图片不可见
        listviewBean.getData().getItems().get(pos).setStatus(1);
        listviewFeatureHomeAdapter.setListviewBean(listviewBean);

        //遍历实体类将urlId加入到集合中
        for (ListviewBean.DataBean.ItemsBean itemBean : listviewBean.getData().getItems()
                ) {
            urlIds.add(String.valueOf(itemBean.getId()));
        }

        if (urlIds.size() > pos) {

            //给下一个页面传值
            Intent intent = new Intent(context, HomeDetailActivity.class);
            intent.putStringArrayListExtra("urlId", urlIds);
            intent.putExtra("urlPos", pos);
            startActivity(intent);
        }
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

                homeFeaturedListView.stopLoadMore();


            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }

    //内部类接受广播设置checkBox标记为喜欢
    class CheckBoxBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            listviewFeatureHomeAdapter = new ListviewFeatureHomeAdapter(context);
            listviewFeatureHomeAdapter.setListviewBean(listviewBean);
            homeFeaturedListView.setAdapter(listviewFeatureHomeAdapter);

        }
    }


}
