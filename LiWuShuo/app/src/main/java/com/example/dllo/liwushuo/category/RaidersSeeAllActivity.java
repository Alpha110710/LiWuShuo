package com.example.dllo.liwushuo.category;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;
import com.example.dllo.liwushuo.category.adapter.RaidersSeeAllLiseviewAdapter;
import com.example.dllo.liwushuo.category.bean.RaidersTopicBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.tool.NetTool;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/6/4.
 */
public class RaidersSeeAllActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView raidersSeeAllBackImg;
    private ListView raiderSeeAllListview;
    private NetTool netTool = new NetTool();
    private RaidersSeeAllLiseviewAdapter raidersSeeAllLiseviewAdapter;
    private RaidersTopicBean raidersTopicBean;

    @Override
    public void initActivity() {
        setContentView(R.layout.activity_raiders_see_all);
        raidersSeeAllBackImg = (ImageView) findViewById(R.id.raiders_see_all_back_img);
        raiderSeeAllListview = (ListView) findViewById(R.id.raider_see_all_listview);

        raidersSeeAllBackImg.setOnClickListener(this);
        raiderSeeAllListview.setOnItemClickListener(this);

        raidersSeeAllLiseviewAdapter = new RaidersSeeAllLiseviewAdapter(this);
        raiderSeeAllListview.setAdapter(raidersSeeAllLiseviewAdapter);
        netTool.getAnalysis(URLValues.RAIDERS_TOPIC_ALL, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                raidersTopicBean = gson.fromJson(response, RaidersTopicBean.class);
                raidersSeeAllLiseviewAdapter.setRaidersTopicBean(raidersTopicBean);

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.raiders_see_all_back_img:
                finish();
                break;


        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this, RaidersDetailsUpActivity.class);
        intent.putExtra("raidersDetailUrl", String.valueOf(raidersTopicBean.getData().getCollections().get(position).getId()));
        startActivity(intent);
    }
}
