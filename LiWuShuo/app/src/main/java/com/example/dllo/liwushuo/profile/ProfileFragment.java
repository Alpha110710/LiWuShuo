package com.example.dllo.liwushuo.profile;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.category.GiftCategoryFragment;
import com.example.dllo.liwushuo.category.RaidersCategoryFragment;
import com.example.dllo.liwushuo.register.RegisterActivity;

import java.util.ArrayList;

import cn.bmob.v3.BmobUser;

/**
 * Created by dllo on 16/5/19.
 */
public class ProfileFragment extends BaseFragment implements View.OnClickListener {
    private TabLayout profileTablayout;
    private ViewPager profileViewpager;
    private ProfileViewpagerAdapter adapter;
    private GiftProfileFragment giftProfileFragment;
    private RaidersProfileFragment raidersProfileFragment;
    private ArrayList<Fragment> fragments;
    private TextView proileName;
    private ImageView profileMeAvatarGirl;

    @Override
    public int setLayout() {
        return R.layout.fragment_profile;
    }

    @Override
    public void initView(View view) {
        profileTablayout = findView(R.id.profile_tablayout);
        profileViewpager = findView(R.id.profile_viewpager);
        proileName = findView(R.id.proile_name);
        profileMeAvatarGirl = findView(R.id.profile_me_avatar_girl);



    }

    @Override
    public void initData() {

        proileName.setOnClickListener(this);
        profileMeAvatarGirl.setOnClickListener(this);

        giftProfileFragment = new GiftProfileFragment();
        raidersProfileFragment = new RaidersProfileFragment();
        adapter = new ProfileViewpagerAdapter(getChildFragmentManager());

        fragments = new ArrayList<>();
        fragments.add(giftProfileFragment);
        fragments.add(raidersProfileFragment);
        adapter.setFragments(fragments);
        profileViewpager.setAdapter(adapter);
        profileTablayout.setupWithViewPager(profileViewpager);

        refreshFragment();




    }

    @Override
    public void onResume() {
        super.onResume();
        refreshFragment();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.proile_name:
                BmobUser bmobUser = BmobUser.getCurrentUser(context);
                if (bmobUser == null){
                    Intent intent = new Intent(context, RegisterActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.profile_me_avatar_girl:
                BmobUser bmobUser1 = BmobUser.getCurrentUser(context);
                if (bmobUser1 == null){
                    Intent intent = new Intent(context, RegisterActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(context, ExitRegisterActivity.class);
                    startActivity(intent);
                }

        }
    }

    //刷新fragment
    void refreshFragment(){
        String username = (String) BmobUser.getObjectByKey(context, "username");
        if (username == null){
            proileName.setText("未登录");

        }else {
            proileName.setText(username);

        }
    }
}
