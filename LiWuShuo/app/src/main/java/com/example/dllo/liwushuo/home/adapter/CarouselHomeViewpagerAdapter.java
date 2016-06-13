package com.example.dllo.liwushuo.home.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.category.RaidersDetailsUpActivity;
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
    private CheckBox itemHomeCarouseCb1, itemHomeCarouseCb2, itemHomeCarouseCb3, itemHomeCarouseCb4, itemHomeCarouseCb5, itemHomeCarouseCb6;

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
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_carouse, container, false);
        itemHomeCarouseImg = (ImageView) view.findViewById(R.id.item_home_carouse_img);
        itemHomeCarouseCb1 = (CheckBox) view.findViewById(R.id.item_home_carouse_cb1);
        itemHomeCarouseCb2 = (CheckBox) view.findViewById(R.id.item_home_carouse_cb2);
        itemHomeCarouseCb3 = (CheckBox) view.findViewById(R.id.item_home_carouse_cb3);
        itemHomeCarouseCb4 = (CheckBox) view.findViewById(R.id.item_home_carouse_cb4);
        itemHomeCarouseCb5 = (CheckBox) view.findViewById(R.id.item_home_carouse_cb5);
        itemHomeCarouseCb6 = (CheckBox) view.findViewById(R.id.item_home_carouse_cb6);
        switch (position % carouselBean.getData().getBanners().size()) {
            case 0:
                itemHomeCarouseCb6.setChecked(false);
                itemHomeCarouseCb1.setChecked(true);
                break;
            case 1:
                itemHomeCarouseCb1.setChecked(false);
                itemHomeCarouseCb2.setChecked(true);
                break;
            case 2:
                itemHomeCarouseCb2.setChecked(false);
                itemHomeCarouseCb3.setChecked(true);
                break;
            case 3:
                itemHomeCarouseCb3.setChecked(false);
                itemHomeCarouseCb4.setChecked(true);
                break;
            case 4:
                itemHomeCarouseCb4.setChecked(false);
                itemHomeCarouseCb5.setChecked(true);
                break;
            default:
                itemHomeCarouseCb5.setChecked(false);
                itemHomeCarouseCb6.setChecked(true);
                break;

        }

        Picasso.with(App.context).load(carouselBean.getData().getBanners().get(position % carouselBean.getData().getBanners().size()).getImage_url()).fit()
                .into(itemHomeCarouseImg);

        //给轮播图的imageView加监听事件跳转 到raiders上面圆角正方形的进入activity中  复用的
        itemHomeCarouseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position % carouselBean.getData().getBanners().size() != carouselBean.getData().getBanners().size() - 1) {
                    Intent intent = new Intent(context, RaidersDetailsUpActivity.class);
                    intent.putExtra("raidersDetailUrl", String.valueOf(carouselBean.getData().getBanners().get(position % carouselBean.getData().getBanners().size()).getTarget_id()));
                    context.startActivity(intent);
                }
            }
        });


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }


}
