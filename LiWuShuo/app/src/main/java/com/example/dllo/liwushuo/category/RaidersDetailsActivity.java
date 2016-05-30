package com.example.dllo.liwushuo.category;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;
import com.example.dllo.liwushuo.home.adapter.ListviewNormalHomeAdapter;
import com.example.dllo.liwushuo.home.bean.NormalListviewBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.tool.App;
import com.example.dllo.liwushuo.tool.NetTool;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/5/30.
 */
public class RaidersDetailsActivity extends BaseActivity implements View.OnClickListener {

    private ImageView raidersDetailBackImg;
    private TextView raidersDetailBarTv;
    private ImageView raidersDetailSortImg;
    private ListView raidersDetailListview;
    private NetTool netTool = new NetTool();
    private ListviewNormalHomeAdapter adapter;


    @Override
    public void initActivity() {
        setContentView(R.layout.activity_raiders_details);
        raidersDetailBackImg = (ImageView) findViewById(R.id.raiders_detail_back_img);
        raidersDetailBarTv = (TextView) findViewById(R.id.raiders_detail_bar_tv);
        raidersDetailSortImg = (ImageView) findViewById(R.id.raiders_detail_sort_img);
        raidersDetailListview = (ListView) findViewById(R.id.raiders_detail_listview);
        adapter = new ListviewNormalHomeAdapter(this);

        raidersDetailListview.setAdapter(adapter);

        raidersDetailBackImg.setOnClickListener(this);

        //设置解析数据listview上,和bar的标题
        getRaidersDetailAnlysis();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.raiders_detail_back_img:
                finish();
                break;
            case R.id.raiders_detail_sort_img:
                break;
        }
    }

    //设置解析数据listview上,和bar的标题
    private void getRaidersDetailAnlysis(){
        Intent intent = getIntent();
        String id = intent.getStringExtra("raidersDetailUrl");
        String url = URLValues.ITEM_RAIDERS_BEFORE + id + URLValues.ITEM_RAIDERS_AFTER;

        netTool.getAnalysis(url, new NetListener() {
            @Override
            public void onSuccessed(String response) {

                Gson gson = new Gson();
                NormalListviewBean normalListviewBean = gson.fromJson(response, NormalListviewBean.class);

                adapter.setNormalListviewBean(normalListviewBean);

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

        String name = intent.getStringExtra("raidersDetailName");
        raidersDetailBarTv.setText(name);
    }
}
