package com.example.dllo.liwushuo.home.adapter;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.home.bean.CarouselBean;
import com.example.dllo.liwushuo.tool.App;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/21.
 */
public class CarouselHomeViewpagerAdapter extends PagerAdapter {

    private Context context;
    private CarouselBean carouselBean;
    private ImageView itemHomeCarouseImg;

    public void setCarouselBean(CarouselBean carouselBean) {
        this.carouselBean = carouselBean;
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
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_carouse, container, false);
        itemHomeCarouseImg = (ImageView) view.findViewById(R.id.item_home_carouse_img);
        Picasso.with(App.context).load(carouselBean.getData().getBanners().get(position % 4).getImage_url()).fit()
                .into(itemHomeCarouseImg);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }


}
