package com.example.dllo.liwushuo.home.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dllo.liwushuo.category.RaidersDetailsUpActivity;
import com.example.dllo.liwushuo.home.bean.CarouselBean;
import com.example.dllo.liwushuo.base.App;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/5/21.
 */
public class CarouselHomeViewpagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {

    private Context context;
    private CarouselBean carouselBean;
    private ViewPager viewPager;
    private LinearLayout linearLayout;


    public void setCarouselBean(CarouselBean carouselBean) {
        this.carouselBean = carouselBean;
        notifyDataSetChanged();
    }

    public void setLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
        notifyDataSetChanged();
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        notifyDataSetChanged();
    }

    public CarouselHomeViewpagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return carouselBean == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        ImageView imageView = new ImageView(context);

        Picasso.with(App.context).load(carouselBean.getData().getBanners().get(position % carouselBean.getData().getBanners().size()).getImage_url()).fit()
                .into(imageView);

        try {
            container.addView(imageView);
        } catch (IllegalStateException e) {
            container.removeView(imageView);
            container.addView(imageView);
        }


        //给轮播图的imageView加监听事件跳转 到raiders上面圆角正方形的进入activity中  复用的
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position % carouselBean.getData().getBanners().size() != carouselBean.getData().getBanners().size() - 1) {
                    Intent intent = new Intent(context, RaidersDetailsUpActivity.class);
                    intent.putExtra("raidersDetailUrl", String.valueOf(carouselBean.getData().getBanners().get(position % carouselBean.getData().getBanners().size()).getTarget_id()));
                    context.startActivity(intent);
                }
            }
        });

        viewPager.addOnPageChangeListener(this);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container.getChildAt(position % carouselBean.getData().getBanners().size()) == object) {
            container.removeViewAt(position % carouselBean.getData().getBanners().size());
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int selectNum = position % carouselBean.getData().getBanners().size();
        for (int i = 0; i < carouselBean.getData().getBanners().size(); i++) {
            if (selectNum == i) {
                ((CheckBox) linearLayout.getChildAt(i)).setChecked(true);
            } else {
                ((CheckBox) linearLayout.getChildAt(i)).setChecked(false);

            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
