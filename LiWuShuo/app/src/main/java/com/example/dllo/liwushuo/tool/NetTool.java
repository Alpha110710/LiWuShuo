package com.example.dllo.liwushuo.tool;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.liwushuo.home.adapter.CarouselHomeViewpagerAdapter;
import com.example.dllo.liwushuo.home.adapter.RecyclerviewFeatureHomeAdapter;
import com.example.dllo.liwushuo.home.bean.CarouselBean;
import com.example.dllo.liwushuo.home.bean.RecyclerviewBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.select.SelectBean;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dllo on 16/5/23.
 */
public class NetTool {
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    public NetTool() {
        requestQueue = VolleySingleton.getInstance().getRequestQueue();
        imageLoader = VolleySingleton.getInstance().getImageLoader();
    }

    public void getAnalysis(String url, final NetListener listener){
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                listener.onSuccessed(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFailed(error);
            }
        });
        requestQueue.add(request);
    }

    //解析homeFeature轮播图
    public void anlysisCarousel(final CarouselHomeViewpagerAdapter adapter) {

        getAnalysis(URLValues.CAROUSEL, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                CarouselBean carouselBean = gson.fromJson(response, CarouselBean.class);
                adapter.setCarouselBean(carouselBean);

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }

    //解析homeFeature的recyclerview
    public void anlysisRecyclerview(final RecyclerviewFeatureHomeAdapter adapter){
        getAnalysis(URLValues.SPECIAL, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                RecyclerviewBean recyclerviewBean = gson.fromJson(response, RecyclerviewBean.class);
                adapter.setRecyclerviewBean(recyclerviewBean);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }

    public static String formatData(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        timeStamp = timeStamp * 1000;
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }

    //解析SelectFragment页面
    public void anlysisSelectFragment(){
        getAnalysis(URLValues.SELECT, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                SelectBean selectBean = gson.fromJson(response, SelectBean.class);
                EventBus eventBus = EventBus.getDefault();
                eventBus.post(selectBean);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }



}
