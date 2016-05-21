package com.example.dllo.liwushuo.home;



import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.category.CategoryViewpagerAdapter;

import java.util.ArrayList;


/**
 * Created by dllo on 16/5/21.
 */
public class FeaturedHomeFragment extends BaseFragment {

    private ViewPager carouselHomeViewpager;
    private CarouselHomeViewpagerAdapter adapter;
    private ArrayList<Integer> imgIds;
    private Handler handler;
    @Override
    public int setLayout() {
        return R.layout.fragment_home_featured;
    }

    @Override
    public void initView(View view) {
        carouselHomeViewpager = findView(R.id.carousel_home_viewpager);

    }

    @Override
    public void initData() {
        adapter = new CarouselHomeViewpagerAdapter(context);
        imgIds = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            imgIds.add(i);
        }
        adapter.setImgIds(imgIds);
        carouselHomeViewpager.setAdapter(adapter);
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
                while (true){
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("FeaturedHomeFragment", "ondestory");
    }
}
