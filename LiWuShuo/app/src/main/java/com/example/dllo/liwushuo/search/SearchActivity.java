package com.example.dllo.liwushuo.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;

/**
 * Created by dllo on 16/6/4.
 */
public class SearchActivity extends BaseActivity implements OnClickListener, TextWatcher {

    private ImageView mainSearchBarBackImg;
    private EditText mainSearchBarEt;
    private TextView mainSearchBarTv;
    private FrameLayout mainSearchFlayout;
    private SearchFragment searchFragment;


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
        replaceFragment(searchFragment);

        //接口用于操作fragment中的点击gridview事件
        searchFragment.setGridViewOnClickListener(new SearchFragment.GridViewOnClickListener() {
            @Override
            public void skip(String key) {
                //设置输入框上的值与点击的gridview值一致
                mainSearchBarEt.setText(key);
                // 设置光标位置在最后
                mainSearchBarEt.setSelection(key.length());

                //创建fragment并且传入当前输入的值
                SearchDetailFragment searchDetailFragment = new SearchDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("key", key);
                searchDetailFragment.setArguments(bundle);
                replaceFragment(searchDetailFragment);
            }
        });

        //监听输入框的内容
        mainSearchBarEt.addTextChangedListener(this);


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
                //判断输入框是否为空
                if (!mainSearchBarEt.getText().toString().equals("")) {
                    //不空的话 点击跳转  并且传入输入框内容
                    SearchDetailFragment searchDetailFragment = new SearchDetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("key", mainSearchBarEt.getText().toString());
                    searchDetailFragment.setArguments(bundle);
                    replaceFragment(searchDetailFragment);

                } else {
                    Toast.makeText(this, "请输入关键字进行搜索", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //edittext内容监听事件
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        //监听最后的输入框如果为空 则回到searchActivity
        if (s.toString().equals("")) {
            replaceFragment(searchFragment);

        }
    }


}
