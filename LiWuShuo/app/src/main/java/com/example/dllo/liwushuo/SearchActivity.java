package com.example.dllo.liwushuo;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Transition;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.base.BaseActivity;
import com.example.dllo.liwushuo.category.GiftConditionSelectActivity;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.search.SearchDetailFragment;
import com.example.dllo.liwushuo.search.SearchFragment;
import com.example.dllo.liwushuo.tool.NetTool;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dllo on 16/6/4.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mainSearchBarBackImg;
    private EditText mainSearchBarEt;
    private TextView mainSearchBarTv;
    private FrameLayout mainSearchFlayout;
    private SearchFragment searchFragment;
    private SearchDetailFragment searchDetailFragment;

    @Override
    public void initActivity() {
        setContentView(R.layout.activity_search);
        mainSearchBarBackImg = (ImageView) findViewById(R.id.main_search_bar_back_img);
        mainSearchBarEt = (EditText) findViewById(R.id.main_search_bar_et);
        mainSearchBarTv = (TextView) findViewById(R.id.main_search_bar_tv);
        mainSearchFlayout = (FrameLayout) findViewById(R.id.main_search_flayout);

        mainSearchBarBackImg.setOnClickListener(this);
        mainSearchBarTv.setOnClickListener(this);

        searchFragment = new SearchFragment();
        searchDetailFragment = new SearchDetailFragment();
        replaceFragment(searchFragment);





    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transition = manager.beginTransaction();
        transition.replace(R.id.main_search_flayout, fragment);
        transition.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.main_search_bar_back_img:
                finish();
                break;
            case R.id.main_search_bar_tv:
                if (!mainSearchBarEt.getText().toString().equals("")) {
                    replaceFragment(searchDetailFragment);
                } else {
                    Toast.makeText(this, "请输入关键字进行搜索", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


}
