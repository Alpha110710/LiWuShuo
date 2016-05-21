package com.example.dllo.liwushuo.home;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.liwushuo.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/21.
 */
public class CarouselHomeViewpagerAdapter extends PagerAdapter {
    private ArrayList<Integer> imgIds;
    private Context context;
    private ImageView itemHomeCarouseImg;

    public void setImgIds(ArrayList<Integer> imgIds) {
        this.imgIds = imgIds;
        notifyDataSetChanged();
    }

    public CarouselHomeViewpagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgIds == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_carouse, container, false);
        itemHomeCarouseImg = (ImageView) view.findViewById(R.id.item_home_carouse_img);
        //TODO: 轮播图图片设置不对
        itemHomeCarouseImg.setImageResource(R.mipmap.ic_about);
        container.addView(view);
        return  view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }


}
