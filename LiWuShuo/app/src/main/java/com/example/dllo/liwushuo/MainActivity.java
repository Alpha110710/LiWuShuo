package com.example.dllo.liwushuo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.dllo.liwushuo.base.BaseActivity;
import com.example.dllo.liwushuo.category.CategoryFragment;
import com.example.dllo.liwushuo.home.HomeFragment;
import com.example.dllo.liwushuo.profile.ProfileFragment;
import com.example.dllo.liwushuo.select.SelectFragment;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar homeToolBar;
    private ImageView menologyToolbarImg;
    private TextView liwushuoToolbarTv, hotToolbarTv;
    private LinearLayout raidersGiftToolbarLayout;
    private ImageButton raidersGiftToolbarImgbtn;
    private ImageView searchToolbarImg;
    private RadioButton home_radiobutton, select_radiobutton, category_radiobutton, profile_radiobutton;
    private HomeFragment homeFragment;
    private SelectFragment selectFragment;
    private CategoryFragment categoryFragment;
    private ProfileFragment profileFragment;

    @Override
    public void initActivity() {
        setContentView(R.layout.activity_main);
        homeToolBar = findView(R.id.home_toolbar);
        menologyToolbarImg = findView(R.id.menology_toolbar_img);
        liwushuoToolbarTv = findView(R.id.liwushuo_toolbar_tv);
        hotToolbarTv = findView(R.id.hot_toolbar_tv);
        raidersGiftToolbarLayout = findView(R.id.raiders_gift_toolbar_layout);
        raidersGiftToolbarImgbtn = findView(R.id.raiders_gift_toolbar_imgbtn);
        searchToolbarImg = findView(R.id.search_toolbar_img);
        home_radiobutton = findView(R.id.home_radiobutton);
        select_radiobutton = findView(R.id.select_radiobutton);
        category_radiobutton = findView(R.id.category_radiobutton);
        profile_radiobutton = findView(R.id.profile_radiobutton);
        home_radiobutton.setOnClickListener(this);
        select_radiobutton.setOnClickListener(this);
        category_radiobutton.setOnClickListener(this);
        profile_radiobutton.setOnClickListener(this);

        homeFragment = new HomeFragment();
        selectFragment = new SelectFragment();
        categoryFragment = new CategoryFragment();
        profileFragment = new ProfileFragment();

        replaceFrameLayout(homeFragment);

    }

    private void replaceFrameLayout(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.radio_fl, fragment);
        transaction.commit();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
        }
    }
}
