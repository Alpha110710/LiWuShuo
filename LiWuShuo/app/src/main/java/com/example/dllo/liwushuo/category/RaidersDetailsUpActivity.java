package com.example.dllo.liwushuo.category;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;
import com.example.dllo.liwushuo.category.adapter.ListviewRaidersUpAdapter;
import com.example.dllo.liwushuo.category.bean.RaidersUpBean;
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
 * 本页activity共用四次  很重要  接受id  传入下一页webId集合   和name标题
 */
public class RaidersDetailsUpActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, XListView.IXListViewListener {

    private ImageView raidersDetailUpBackImg;
    private TextView raidersDetailUpBarTv;
    private ImageView raidersDetailUpShareImg;
    private XListView raidersDetailListview;
    private NetTool netTool = new NetTool();
    private ListviewRaidersUpAdapter adapter;
    private ArrayList<String> urlIds;
    private String id, url, nextUrl;
    private RaidersUpBean raidersUpBean;


    @Override
    public void initActivity() {
        setContentView(R.layout.activity_raiders_up_details);
        raidersDetailUpBackImg = (ImageView) findViewById(R.id.raiders_detail_up_back_img);
        raidersDetailUpBarTv = (TextView) findViewById(R.id.raiders_detail_up_bar_tv);
        raidersDetailUpShareImg = (ImageView) findViewById(R.id.raiders_detail_up_share_img);
        raidersDetailListview = (XListView) findViewById(R.id.raiders_detail_up_listview);
        adapter = new ListviewRaidersUpAdapter(this);

        raidersDetailListview.setAdapter(adapter);

        raidersDetailUpBackImg.setOnClickListener(this);
        raidersDetailUpShareImg.setOnClickListener(this);
        raidersDetailListview.setOnItemClickListener(this);

        //设置解析数据listview上,和bar的标题
        getRaidersDetailAnlysis();

        //刷新
        raidersDetailListview.setXListViewListener(this);
        raidersDetailListview.setPullRefreshEnable(true);
        raidersDetailListview.setPullLoadEnable(true);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.raiders_detail_up_back_img:
                finish();
                break;
            case R.id.raiders_detail_up_share_img:
                PopTool popTool = new PopTool(this, R.id.raiders_detail_up_share_img);
                popTool.showSharePopupWindow();
                break;
        }
    }

    //设置解析数据listview上,和bar的标题
    private void getRaidersDetailAnlysis() {
        Intent intent = getIntent();
        id = intent.getStringExtra("raidersDetailUrl");
        url = URLValues.ITEM_RAIDERS_UP_BEFORE + id + URLValues.ITEM_RAIDERS_UP_AFTER;
        netTool.getAnalysis(url, new NetListener() {
            @Override
            public void onSuccessed(String response) {

                Gson gson = new Gson();
                raidersUpBean = gson.fromJson(response, RaidersUpBean.class);

                raidersDetailUpBarTv.setText(raidersUpBean.getData().getTitle());

                adapter.setRaidersUpBean(raidersUpBean);
                //遍历实体类将urlId加入到集合中
                urlIds = new ArrayList<>();
                for (RaidersUpBean.DataBean.PostsBean postsBean : raidersUpBean.getData().getPosts()
                        ) {
                    urlIds.add(String.valueOf(postsBean.getId()));
                }

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

//        String name = intent.getStringExtra("raidersDetailName");
//        raidersDetailBarTv.setText(name);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //给下一个页面传值
        Intent intent = new Intent(this, HomeDetailActivity.class);
        if (urlIds != null) {
            intent.putStringArrayListExtra("urlId", urlIds);
            intent.putExtra("urlPos", position);
        }
        startActivity(intent);
    }

    //刷新
    @Override
    public void onRefresh() {
        netTool.getAnalysis(url, new NetListener() {
            @Override
            public void onSuccessed(String response) {

                Gson gson = new Gson();
                raidersUpBean = gson.fromJson(response, RaidersUpBean.class);

                raidersDetailUpBarTv.setText(raidersUpBean.getData().getTitle());

                adapter.setRaidersUpBean(raidersUpBean);
                //遍历实体类将urlId加入到集合中
                urlIds = new ArrayList<>();
                for (RaidersUpBean.DataBean.PostsBean postsBean : raidersUpBean.getData().getPosts()
                        ) {
                    urlIds.add(String.valueOf(postsBean.getId()));
                }

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }

    @Override
    public void onLoadMore() {
        nextUrl = raidersUpBean.getData().getPaging().getNext_url();

        netTool.getAnalysis(nextUrl, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                raidersUpBean = gson.fromJson(response, RaidersUpBean.class);
                List<RaidersUpBean.DataBean.PostsBean> itemsBeans = raidersUpBean.getData().getPosts();
                adapter.addBean(itemsBeans);

                raidersDetailListview.stopLoadMore();

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }
}
