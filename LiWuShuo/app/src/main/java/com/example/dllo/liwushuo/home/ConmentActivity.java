package com.example.dllo.liwushuo.home;

import android.widget.ImageView;
import android.widget.ListView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;

/**
 * Created by dllo on 16/6/2.
 */
public class ConmentActivity extends BaseActivity {

    private ListView homeConmentDownListview;
    private ImageView homeConmentBackImg;

    @Override
    public void initActivity() {
        setContentView(R.layout.activity_conment);
        homeConmentDownListview = (ListView) findViewById(R.id.home_conment_down_listview);
        homeConmentBackImg = (ImageView) findViewById(R.id.home_conment_back_img);
    }
}
