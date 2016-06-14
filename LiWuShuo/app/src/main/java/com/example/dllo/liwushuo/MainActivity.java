package com.example.dllo.liwushuo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.dllo.liwushuo.base.BaseActivity;
import com.example.dllo.liwushuo.category.CategoryFragment;
import com.example.dllo.liwushuo.home.HomeCalendarActivity;
import com.example.dllo.liwushuo.home.HomeFragment;
import com.example.dllo.liwushuo.profile.ProfileFragment;
import com.example.dllo.liwushuo.search.SearchActivity;
import com.example.dllo.liwushuo.select.SelectFragment;
import com.example.dllo.liwushuo.welcome.BoyAndGirlActivity;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar homeToolBar;
    private ImageView menologyToolbarImg, liwushuoToolbarTv;
    private TextView hotToolbarTv;
    private LinearLayout raidersGiftToolbarLayout;
    private CheckBox raidersGiftToolbarCb;
    private ImageView searchToolbarImg;
    private RadioButton home_radiobutton, select_radiobutton, category_radiobutton, profile_radiobutton;
    private HomeFragment homeFragment;
    private SelectFragment selectFragment;
    private CategoryFragment categoryFragment;
    private ProfileFragment profileFragment;

    @Override
    public void initActivity() {

        SharedPreferences sharedPreferences = getSharedPreferences("welcome", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("isFirst", true)){
            Intent intent = new Intent(this, BoyAndGirlActivity.class);
            startActivity(intent);
            finish();
        }


        setContentView(R.layout.activity_main);
        homeToolBar = findView(R.id.home_toolbar);
        menologyToolbarImg = findView(R.id.menology_toolbar_img);
        liwushuoToolbarTv = findView(R.id.liwushuo_toolbar_tv);
        hotToolbarTv = findView(R.id.hot_toolbar_tv);
        raidersGiftToolbarLayout = findView(R.id.raiders_gift_toolbar_layout);
        raidersGiftToolbarCb =  (CheckBox)findViewById(R.id.raiders_gift_toolbar_cb);
        searchToolbarImg = findView(R.id.search_toolbar_img);
        home_radiobutton = findView(R.id.home_radiobutton);
        select_radiobutton = findView(R.id.select_radiobutton);
        category_radiobutton = findView(R.id.category_radiobutton);
        profile_radiobutton = findView(R.id.profile_radiobutton);
        home_radiobutton.setOnClickListener(this);
        select_radiobutton.setOnClickListener(this);
        category_radiobutton.setOnClickListener(this);
        profile_radiobutton.setOnClickListener(this);
        searchToolbarImg.setOnClickListener(this);
        menologyToolbarImg.setOnClickListener(this);

        homeFragment = new HomeFragment();
        selectFragment = new SelectFragment();
        categoryFragment = new CategoryFragment();
        profileFragment = new ProfileFragment();
        addAllFragment();
        replaceFrameLayout(homeFragment);

        //点击checkbox不同状态, 设置第三页面的viewpager
        raidersGiftToolbarCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                if (checkBox.isChecked()){
                    categoryFragment.categoryViewpager.setCurrentItem(1);
                }else {
                    categoryFragment.categoryViewpager.setCurrentItem(0);
                }
            }
        });

    }

    private void addAllFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.radio_fl, homeFragment).add(R.id.radio_fl, selectFragment, "select").add(R.id.radio_fl, categoryFragment, "category").add(R.id.radio_fl, profileFragment, "profileFragment")
                .commit();
    }

    private void replaceFrameLayout(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(homeFragment).hide(selectFragment).hide(categoryFragment).hide(profileFragment)
                .show(fragment).commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_radiobutton:
                homeToolBar.setVisibility(View.VISIBLE);
                liwushuoToolbarTv.setVisibility(View.VISIBLE);
                hotToolbarTv.setVisibility(View.GONE);
                raidersGiftToolbarLayout.setVisibility(View.GONE);
                menologyToolbarImg.setVisibility(View.VISIBLE);

                replaceFrameLayout(homeFragment);
                break;
            case R.id.select_radiobutton:
                homeToolBar.setVisibility(View.VISIBLE);
                liwushuoToolbarTv.setVisibility(View.GONE);
                hotToolbarTv.setVisibility(View.VISIBLE);
                raidersGiftToolbarLayout.setVisibility(View.GONE);
                menologyToolbarImg.setVisibility(View.GONE);

                replaceFrameLayout(selectFragment);
                break;
            case R.id.category_radiobutton:
                homeToolBar.setVisibility(View.VISIBLE);
                liwushuoToolbarTv.setVisibility(View.GONE);
                hotToolbarTv.setVisibility(View.GONE);
                raidersGiftToolbarLayout.setVisibility(View.VISIBLE);
                menologyToolbarImg.setVisibility(View.GONE);

                replaceFrameLayout(categoryFragment);
                break;
            case R.id.profile_radiobutton:
                homeToolBar.setVisibility(View.GONE);

                replaceFrameLayout(profileFragment);
                break;

            case R.id.search_toolbar_img:
                Intent intent = new Intent();
                intent.setClass(this, SearchActivity.class);
                startActivity(intent);
                break;

            case R.id.menology_toolbar_img:
                Intent intent1 = new Intent(this, HomeCalendarActivity.class);
                startActivity(intent1);
                break;
        }
    }

    public void changecb(boolean flag) {
        if (flag) {
            raidersGiftToolbarCb.setChecked(false);

        } else {
            raidersGiftToolbarCb.setChecked(true);
        }
    }
}
