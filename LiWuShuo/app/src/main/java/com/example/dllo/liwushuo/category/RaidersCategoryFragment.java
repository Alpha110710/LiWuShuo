package com.example.dllo.liwushuo.category;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.category.adapter.RaidersCategoryListviewAdapter;
import com.example.dllo.liwushuo.category.adapter.RaidersCategoryListviewGridviewAdapter;
import com.example.dllo.liwushuo.category.adapter.RaidersCategoryRecyclerAdapter;
import com.example.dllo.liwushuo.category.bean.RaidersDownBean;
import com.example.dllo.liwushuo.category.bean.RaidersTopicBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.tool.NetTool;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/5/20.
 */
public class RaidersCategoryFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView categoryRaidersTopicRecyclerview;
    private RaidersCategoryRecyclerAdapter raidersCategoryRecyclerAdapter;
    private ListView categoryRaidersDownListview;
    private RaidersCategoryListviewAdapter raidersCategoryListviewAdapter;
    private NetTool netTool = new NetTool();
    private RaidersDownBean raidersDownBean;
    private RaidersCategoryListviewGridviewAdapter raidersCategoryListviewGridviewAdapter;
    private RaidersTopicBean raidersTopicBean;
    private TextView categoryRaidersLookallTv;

    @Override
    public int setLayout() {
        return R.layout.fragment_category_raiders;
    }

    @Override
    public void initView(View view) {
        categoryRaidersDownListview = (ListView) view.findViewById(R.id.category_raiders_down_listview);
    }

    @Override
    public void initData() {
        //gridview
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_category_raiders_header, null);
        categoryRaidersTopicRecyclerview = (RecyclerView) view.findViewById(R.id.category_raiders_topic_recyclerview);
        categoryRaidersLookallTv = (TextView) view.findViewById(R.id.category_raiders_lookall_tv);
        //查看全部点击事件跳转RaidersSeeAllActivity
        categoryRaidersLookallTv.setOnClickListener(this);

        raidersCategoryRecyclerAdapter = new RaidersCategoryRecyclerAdapter(context);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRaidersTopicRecyclerview.setLayoutManager(manager);
        categoryRaidersTopicRecyclerview.setAdapter(raidersCategoryRecyclerAdapter);
        //解析recycler攻略上方的横滑图片
        netTool.anlysisRaidersTopic();


        //listview
        raidersCategoryListviewAdapter = new RaidersCategoryListviewAdapter(context);
        categoryRaidersDownListview.setAdapter(raidersCategoryListviewAdapter);
        categoryRaidersDownListview.addHeaderView(view);

        //获取解析攻略页面下方的数据
        getAnlysisCategoryDown();

        //TODO:初始化listviewGridview适配器:但是并没有用上
        raidersCategoryListviewGridviewAdapter = new RaidersCategoryListviewGridviewAdapter(context);

        //recyclerview监听事件
        raidersCategoryRecyclerAdapter.setRecyclerviewOnClickListener(new RecyclerviewOnClickListener() {
            @Override
            public void onClick(int position) {
                //myRaidersListviewViewHolder.pos 为大分组的位置
                // position为 小图标的位置
                raidersTopicBean = raidersCategoryRecyclerAdapter.getRaidersTopicBean();
                Intent intent = new Intent(context, RaidersDetailsUpActivity.class);
                intent.putExtra("raidersDetailUrl", String.valueOf(raidersTopicBean.getData().getCollections().get(position).getId()));
                context.startActivity(intent);
            }
        });

    }


    private void getAnlysisCategoryDown() {
        netTool.getAnalysis(URLValues.RAIDERS_DOWN, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                raidersDownBean = gson.fromJson(response, RaidersDownBean.class);
                raidersCategoryListviewAdapter.setRaidersDownBean(raidersDownBean);
                raidersCategoryListviewGridviewAdapter.setRaidersDownBean(raidersDownBean);

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        raidersCategoryRecyclerAdapter.unResgisterBus();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.category_raiders_lookall_tv:
                Intent intent = new Intent();
                intent.setClass(context, RaidersSeeAllActivity.class);
                startActivity(intent);
                break;
        }
    }
}
