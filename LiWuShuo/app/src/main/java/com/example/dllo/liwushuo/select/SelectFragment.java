package com.example.dllo.liwushuo.select;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.MainActivity;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.tool.NetTool;
import com.example.dllo.liwushuo.view.XGridView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/19.
 */
public class SelectFragment extends BaseFragment implements XGridView.IXGridViewListener, AdapterView.OnItemClickListener {
    private XGridView selectGridview;
    private SelectGridViewAdapter adapter;
    private NetTool netTool = new NetTool();
    private SelectBean selectBean;


    @Override
    public int setLayout() {
        return R.layout.fragment_select;
    }

    @Override
    public void initView(View view) {
        selectGridview = findView(R.id.select_gridview);

    }

    @Override
    public void initData() {
        adapter = new SelectGridViewAdapter(context);
        selectGridview.setAdapter(adapter);
        //解析
        netTool.anlysisSelectFragment();
        selectGridview.setOnItemClickListener(this);

        //下拉刷新
        selectGridview.setXGridViewListener(this);
        selectGridview.setPullLoadEnable(true);
        selectGridview.setPullRefreshEnable(true);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消适配器的EventBus注册
        adapter.unRegisterBus();
    }

    //进行页面跳转进入淘宝fragment
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectBean = adapter.getSelectBean();
        SelectBean.DataBean.ItemsBean itemsBean = selectBean.getData().getItems().get(position);
        Intent intent = new Intent(context, SelectDetailActivity.class);
        intent.putExtra("url", selectBean.getData().getItems().get(position).getData().getPurchase_url());
        intent.putExtra("name", selectBean.getData().getItems().get(position).getData().getName());
        intent.putExtra("id", String.valueOf(selectBean.getData().getItems().get(position).getData().getId()));
        intent.putExtra("price", selectBean.getData().getItems().get(position).getData().getPrice());
        intent.putExtra("imgUrl", selectBean.getData().getItems().get(position).getData().getCover_image_url());
        intent.putExtra("likeNum", String.valueOf(selectBean.getData().getItems().get(position).getData().getFavorites_count()));

        startActivity(intent);

    }


    //刷新
    @Override
    public void onRefresh() {
        netTool.getAnalysis(URLValues.SELECT, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                selectBean = gson.fromJson(response, SelectBean.class);
                adapter.setSelectBean(selectBean);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }

    //下拉刷新  已经好用了
    @Override
    public void onLoadMore() {
        selectBean = adapter.getSelectBean();
        String nextUrl = selectBean.getData().getPaging().getNext_url();

        netTool.getAnalysis(nextUrl, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                SelectBean selectBean = gson.fromJson(response, SelectBean.class);
                List<SelectBean.DataBean.ItemsBean> itemsBeen = selectBean.getData().getItems();
                adapter.addItems(itemsBeen);

               selectGridview.stopLoadMore();

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }
}
