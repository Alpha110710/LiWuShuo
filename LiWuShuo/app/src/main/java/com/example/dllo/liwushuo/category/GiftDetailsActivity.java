package com.example.dllo.liwushuo.category;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;
import com.example.dllo.liwushuo.category.adapter.GiftDetailsGridviewAdapter;
import com.example.dllo.liwushuo.category.bean.GiftBean;
import com.example.dllo.liwushuo.category.bean.GiftDetailBean;
import com.example.dllo.liwushuo.category.bean.GiftSelectWebBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.select.SelectBean;
import com.example.dllo.liwushuo.select.SelectDetailActivity;
import com.example.dllo.liwushuo.select.SelectGridViewAdapter;
import com.example.dllo.liwushuo.tool.NetTool;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/5/31.
 * 小圆圈点进的activity
 */
public class GiftDetailsActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private GridView giftDetailGridview;
    private GiftDetailsGridviewAdapter giftDetailsGridviewAdapter;
    private NetTool netTool = new NetTool();
    private TextView giftDetailBarTv;
    private ImageView giftDetailBackImg, giftDetailSortImg;
    private GiftDetailBean giftDetailBean;



    @Override
    public void initActivity() {
        setContentView(R.layout.activity_gift_details);
        giftDetailGridview = (GridView) findViewById(R.id.gift_detail_gridview);
        giftDetailBarTv = (TextView) findViewById(R.id.gift_detail_bar_tv);
        giftDetailBackImg = (ImageView) findViewById(R.id.gift_detail_back_img);
        giftDetailSortImg = (ImageView) findViewById(R.id.gift_detail_sort_img);

        giftDetailSortImg.setOnClickListener(this);
        giftDetailBackImg.setOnClickListener(this);
        giftDetailGridview.setOnItemClickListener(this);


        giftDetailsGridviewAdapter = new GiftDetailsGridviewAdapter(this);
        giftDetailGridview.setAdapter(giftDetailsGridviewAdapter);


        Intent intent = getIntent();
        String giftDetailUrl = intent.getStringExtra("giftDetailUrl");
        String giftDetailName = intent.getStringExtra("giftDetailName");
        giftDetailBarTv.setText(giftDetailName);

        String url = URLValues.ITEM_GIFT_BEFORE + giftDetailUrl + URLValues.ITEM_GIFT_AFTER;
        netTool.getAnalysis(url, new NetListener() {
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gift_detail_back_img:
                finish();
                break;
            case R.id.gift_detail_sort_img:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent intent = new Intent(this, SelectDetailActivity.class);
            intent.putExtra("url", giftDetailBean.getData().getItems().get(position).getPurchase_url());
            intent.putExtra("name", giftDetailBean.getData().getItems().get(position).getName());
            startActivity(intent);

    }
}
