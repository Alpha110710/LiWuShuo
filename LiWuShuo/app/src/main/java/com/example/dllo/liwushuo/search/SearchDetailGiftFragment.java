package com.example.dllo.liwushuo.search;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.category.adapter.GiftDetailsGridviewAdapter;
import com.example.dllo.liwushuo.category.bean.GiftDetailBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.tool.FormatCodeUtil;
import com.example.dllo.liwushuo.tool.NetTool;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by dllo on 16/6/6.
 */
public class SearchDetailGiftFragment extends BaseFragment {

    private GridView searchDetailGiftGridview;
    //复用GiftDetailsGridviewAdapter适配器
    private GiftDetailsGridviewAdapter giftDetailsGridviewAdapter;
    private NetTool netTool = new NetTool();
    private TextView searchDetailGiftTv;


    @Override
    public int setLayout() {
        return R.layout.fragment_search_detail_gift;
    }

    @Override
    public void initView(View view) {
        searchDetailGiftGridview = (GridView) view.findViewById(R.id.search_detail_gift_gridview);
        searchDetailGiftTv = (TextView) view.findViewById(R.id.search_detail_gift_tv);
    }


    @Override
    public void initData() {
        giftDetailsGridviewAdapter = new GiftDetailsGridviewAdapter(context);
        searchDetailGiftGridview.setAdapter(giftDetailsGridviewAdapter);
        //bundle接值
        Bundle bundle = getArguments();
        String key = bundle.getString("key");
        String url = URLValues.SEARCH_GIFT + FormatCodeUtil.codingFormat(key);
        netTool.getAnalysis(url, new NetListener() {

            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                GiftDetailBean giftDetailBean = gson.fromJson(response, GiftDetailBean.class);
                giftDetailsGridviewAdapter.setGiftDetailBean(giftDetailBean);

                //判断是否解析到数据  无数据时textview显示
                if (giftDetailBean.getData().getItems().size() == 0) {
                    searchDetailGiftTv.setVisibility(View.VISIBLE);
                } else {
                    searchDetailGiftTv.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailed(VolleyError error) {
            }
        });


    }

}
