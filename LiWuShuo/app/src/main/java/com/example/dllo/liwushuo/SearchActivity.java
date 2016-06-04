package com.example.dllo.liwushuo;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.base.BaseActivity;
import com.example.dllo.liwushuo.category.GiftConditionSelectActivity;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.tool.NetTool;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dllo on 16/6/4.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView mainSearchBarBackImg;
    private EditText mainSearchBarEt;
    private TextView mainSearchBarTv;
    private GridView mainSearchBarGridview;
    private TextView mainSearchBarFastTv;
    private NetTool netTool = new NetTool();
    private SimpleAdapter simpleAdapter;

    @Override
    public void initActivity() {
        setContentView(R.layout.activity_search);
        mainSearchBarBackImg = (ImageView) findViewById(R.id.main_search_bar_back_img);
        mainSearchBarEt = (EditText) findViewById(R.id.main_search_bar_et);
        mainSearchBarTv = (TextView) findViewById(R.id.main_search_bar_tv);
        mainSearchBarGridview = (GridView) findViewById(R.id.main_search_gridview);
        mainSearchBarFastTv = (TextView) findViewById(R.id.main_search_fast_tv);

        mainSearchBarBackImg.setOnClickListener(this);
        mainSearchBarTv.setOnClickListener(this);
        mainSearchBarFastTv.setOnClickListener(this);
        mainSearchBarGridview.setOnItemClickListener(this);

        //解析网格布局里的数据  分类
        initGetAnlysisGridview();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_search_fast_tv:
                //跳到礼物条件筛选礼物界面
                Intent intent = new Intent(this, GiftConditionSelectActivity.class);
                startActivity(intent);
                break;
            case R.id.main_search_bar_back_img:
                finish();
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
        simpleAdapter = new SimpleAdapter(this, hashMaps, R.layout.item_main_search_gridview, new String[]{"aaa"},
                new int[]{R.id.item_main_search_gridview_tv});
        mainSearchBarGridview.setAdapter(simpleAdapter);
    }


}
