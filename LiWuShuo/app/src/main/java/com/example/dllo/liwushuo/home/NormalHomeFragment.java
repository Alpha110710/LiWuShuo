package com.example.dllo.liwushuo.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.home.adapter.ListviewFeatureHomeAdapter;
import com.example.dllo.liwushuo.home.adapter.ListviewNormalHomeAdapter;
import com.example.dllo.liwushuo.home.bean.ListviewBean;
import com.example.dllo.liwushuo.home.bean.NormalListviewBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.tool.NetTool;
import com.example.dllo.liwushuo.view.XListView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/21.
 */
public class NormalHomeFragment extends BaseFragment implements AdapterView.OnItemClickListener, XListView.IXListViewListener {

    private XListView normalHomeListview;
    private ListviewNormalHomeAdapter adapter;
    private NetTool netTool = new NetTool();
    private ArrayList<String> urlIds;
    private NormalListviewBean normalListviewBean;
    private String url, nextUrl;
    private CheckBoxBroadCastReceiver checkBoxBroadCastReceiver;

    public static Fragment createFragment(String url) {
        Fragment fragment = new NormalHomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_home_normal;
    }

    @Override
    public void initView(View view) {
        normalHomeListview = findView(R.id.normal_home_listview);
        adapter = new ListviewNormalHomeAdapter(context);
        normalHomeListview.setAdapter(adapter);


    }

    @Override
    public void initData() {

        normalHomeListview.setOnItemClickListener(this);
        urlIds = new ArrayList<>();


        Bundle bundle = getArguments();
        url = bundle.getString("data");
        netTool.getAnalysis(url, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                normalListviewBean = gson.fromJson(response, NormalListviewBean.class);
                adapter.setNormalListviewBean(normalListviewBean);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });


        normalHomeListview.setXListViewListener(this);
        normalHomeListview.setPullLoadEnable(true);
        normalHomeListview.setPullRefreshEnable(true);

        //注册广播
        checkBoxBroadCastReceiver = new CheckBoxBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.dllo.liwushuo.profile.checkBox");
        context.registerReceiver(checkBoxBroadCastReceiver, intentFilter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        context.unregisterReceiver(checkBoxBroadCastReceiver);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //遍历实体类将urlId加入到集合中

        for (NormalListviewBean.DataBean.ItemsBean itemBean : normalListviewBean.getData().getItems()
                ) {
            urlIds.add(String.valueOf(itemBean.getId()));
        }
        if (urlIds.size() > position - 1) {
            //给下一个页面传值
            Intent intent = new Intent(context, HomeDetailActivity.class);
            intent.putStringArrayListExtra("urlId", urlIds);
            intent.putExtra("urlPos", position - 1);
            startActivity(intent);
        }

    }


    //下拉刷新
    @Override
    public void onRefresh() {
        netTool.getAnalysis(url, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                normalListviewBean = gson.fromJson(response, NormalListviewBean.class);
                adapter.setNormalListviewBean(normalListviewBean);


            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

    }

    //上拉加载
    @Override
    public void onLoadMore() {
        nextUrl = normalListviewBean.getData().getPaging().getNext_url();

        netTool.getAnalysis(nextUrl, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                normalListviewBean = gson.fromJson(response, NormalListviewBean.class);
                List<NormalListviewBean.DataBean.ItemsBean> itemsBeans = normalListviewBean.getData().getItems();
                adapter.addItemBean(itemsBeans);

                normalHomeListview.stopLoadMore();

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
            adapter = new ListviewNormalHomeAdapter(context);
            adapter.setNormalListviewBean(normalListviewBean);
            normalHomeListview.setAdapter(adapter);

        }
    }

}
