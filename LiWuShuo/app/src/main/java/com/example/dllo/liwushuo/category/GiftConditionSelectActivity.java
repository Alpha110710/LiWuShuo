package com.example.dllo.liwushuo.category;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;
import com.example.dllo.liwushuo.category.adapter.GiftDetailsGridviewAdapter;
import com.example.dllo.liwushuo.category.bean.GiftDetailBean;
import com.example.dllo.liwushuo.category.bean.GiftSelectWebBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.select.SelectDetailActivity;
import com.example.dllo.liwushuo.tool.NetTool;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/5/31.
 */
public class GiftConditionSelectActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private GridView giftConditionSelectGridview;
    private ImageView giftConditionSelectBackImg;
    private ImageView giftConditionSelectSortImg;
    private GiftDetailsGridviewAdapter giftDetailsGridviewAdapter;
    private NetTool netTool = new NetTool();
    private GiftDetailBean giftDetailBean;
    private GiftSelectWebBean giftSelectWebBean;


    @Override
    public void initActivity() {
        setContentView(R.layout.activity_gift_condition_select);
        giftConditionSelectGridview = (GridView) findViewById(R.id.gift_condition_select_gridview);
        giftConditionSelectBackImg = (ImageView) findViewById(R.id.gift_condition_select_back_img);
        giftConditionSelectSortImg = (ImageView) findViewById(R.id.gift_condition_select_sort_img);

        giftConditionSelectGridview.setOnItemClickListener(this);
        giftConditionSelectBackImg.setOnClickListener(this);

        //复用giftDetailActivity适配器
        giftDetailsGridviewAdapter = new GiftDetailsGridviewAdapter(this);
        giftConditionSelectGridview.setAdapter(giftDetailsGridviewAdapter);


        //TODO:当筛选条件不同对应url也不同
        netTool.getAnalysis(URLValues.SELECT_GIFT, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                giftDetailBean = gson.fromJson(response, GiftDetailBean.class);
                giftDetailsGridviewAdapter.setGiftDetailBean(giftDetailBean);


            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String urlWebId = URLValues.SELECT_GIFT_WEB + String.valueOf(giftDetailBean.getData().getItems().get(position).getId());

        Intent intent = new Intent(this, SelectDetailActivity.class);
        //intent跳转传入对应id
        intent.putExtra("urlWebId", urlWebId);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gift_condition_select_back_img:
                finish();
                break;
            case R.id.gift_condition_select_sort_img:
                break;
        }
    }
}
