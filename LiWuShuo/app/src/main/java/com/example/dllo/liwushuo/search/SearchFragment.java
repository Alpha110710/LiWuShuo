package com.example.dllo.liwushuo.search;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.SearchBean;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.category.GiftConditionSelectActivity;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.tool.NetTool;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dllo on 16/6/6.
 */
public class SearchFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private TextView fragmentSearchFastTv;
    private GridView fragmentSearchGridview;
    private NetTool netTool = new NetTool();
    private SimpleAdapter simpleAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void initView(View view) {
        fragmentSearchFastTv = (TextView) view.findViewById(R.id.fragment_search_fast_tv);
        fragmentSearchGridview = (GridView) view.findViewById(R.id.fragment_search_gridview);
    }

    @Override
    public void initData() {
        fragmentSearchFastTv.setOnClickListener(this);
        fragmentSearchGridview.setOnItemClickListener(this);

        //解析网格布局里的数据  分类
        initGetAnlysisGridview();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_search_fast_tv:
                //跳到礼物条件筛选礼物界面
                Intent intent = new Intent(context, GiftConditionSelectActivity.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    //解析网格布局里的数据  分类
    private void initGetAnlysisGridview() {
        final ArrayList<HashMap<String, String>> hashMaps = new ArrayList<HashMap<String, String>>();
        netTool.getAnalysis(URLValues.SEARCH, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                SearchBean searchBean = gson.fromJson(response, SearchBean.class);


                for (String s : searchBean.getData().getHot_words()) {
                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    hashMap.put("aaa", s);
                    hashMaps.add(hashMap);
                }

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

        //数据转换为hasmap直接设置给simpleAdapter
        simpleAdapter = new SimpleAdapter(context, hashMaps, R.layout.item_main_search_gridview, new String[]{"aaa"},
                new int[]{R.id.item_main_search_gridview_tv});
        fragmentSearchGridview.setAdapter(simpleAdapter);
    }

}
