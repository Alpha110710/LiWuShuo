package com.example.dllo.liwushuo.select;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.dllo.liwushuo.MainActivity;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.tool.NetTool;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/19.
 */
public class SelectFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private GridView selectGridview;
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

        startActivity(intent);

    }
}
