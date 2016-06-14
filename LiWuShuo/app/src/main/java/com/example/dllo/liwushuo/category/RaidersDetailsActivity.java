package com.example.dllo.liwushuo.category;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;
import com.example.dllo.liwushuo.home.HomeDetailActivity;
import com.example.dllo.liwushuo.home.adapter.ListviewNormalHomeAdapter;
import com.example.dllo.liwushuo.home.bean.NormalListviewBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.tool.NetTool;
import com.example.dllo.liwushuo.tool.PopTool;
import com.example.dllo.liwushuo.view.XListView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/30.
 */
public class RaidersDetailsActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, XListView.IXListViewListener {

    private ImageView raidersDetailBackImg;
    private TextView raidersDetailBarTv;
    private ImageView raidersDetailSortImg;
    private XListView raidersDetailListview;
    private NetTool netTool = new NetTool();
    private ListviewNormalHomeAdapter adapter;
    private ArrayList<String> urlIds;
    private PopTool popTool;
    private String url, id, nextUrl;
    private NormalListviewBean normalListviewBean;


    @Override
    public void initActivity() {
        setContentView(R.layout.activity_raiders_details);
        raidersDetailBackImg = (ImageView) findViewById(R.id.raiders_detail_back_img);
        raidersDetailBarTv = (TextView) findViewById(R.id.raiders_detail_bar_tv);
        raidersDetailSortImg = (ImageView) findViewById(R.id.raiders_detail_sort_img);
        raidersDetailListview = (XListView) findViewById(R.id.raiders_detail_listview);
        adapter = new ListviewNormalHomeAdapter(this);

        raidersDetailListview.setAdapter(adapter);
        urlIds = new ArrayList<String>();
        raidersDetailBackImg.setOnClickListener(this);
        raidersDetailSortImg.setOnClickListener(this);
        raidersDetailListview.setOnItemClickListener(this);
        popTool = new PopTool(this, R.id.raiders_detail_sort_img, 3);

        //解析bar的标题
        Intent intent = getIntent();
        id = intent.getStringExtra("raidersDetailUrl");
        url = URLValues.ITEM_RAIDERS_BEFORE + id + URLValues.ITEM_RAIDERS_AFTER;
        String name = intent.getStringExtra("raidersDetailName");
        raidersDetailBarTv.setText(name);

        //设置解析数据listview上
        getRaidersDetailSortAnlysis(url);
        popTool.setSortOnClickListener(new PopTool.SortOnClickListener() {
            @Override
            public void getHotUrl() {

                String hotUrl = "http://api.liwushuo.com/v2/channels/" + id + "/items?limit=20&gender=2&offset=0&generation=2&order_by=likes_count";
                Log.d("RaidersDetailsActivity", hotUrl);
                getRaidersDetailSortAnlysis(hotUrl);
            }

            @Override
            public void getDefaultUrl() {
                getRaidersDetailSortAnlysis(url);
            }

            //用不上的方法
            @Override
            public void getPriceHighToLow() {

            }

            //用不上的方法
            @Override
            public void getPriceLowToHigh() {

            }
        });

        //刷新
        raidersDetailListview.setXListViewListener(this);
        raidersDetailListview.setPullRefreshEnable(true);
        raidersDetailListview.setPullLoadEnable(true);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.raiders_detail_back_img:
                finish();
                break;
            case R.id.raiders_detail_sort_img:
                popTool.showShortSortPopupWindow();
                break;
        }
    }

    //设置解析数据listview上,和bar的标题
    private void getRaidersDetailSortAnlysis(String url) {

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //遍历实体类将urlId加入到集合中
        for (NormalListviewBean.DataBean.ItemsBean itemBean : normalListviewBean.getData().getItems()
                ) {
            urlIds.add(String.valueOf(itemBean.getId()));
        }

        if (urlIds.size() > position - 1) {
            //给下一个页面传值
            Intent intent = new Intent(this, HomeDetailActivity.class);
            intent.putStringArrayListExtra("urlId", urlIds);
            intent.putExtra("urlPos", position - 1);
            startActivity(intent);
        }
    }

    //刷新
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

                raidersDetailListview.stopLoadMore();

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }
}
