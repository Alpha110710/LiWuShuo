package com.example.dllo.liwushuo.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.home.adapter.ListviewNormalHomeAdapter;

/**
 * Created by dllo on 16/5/21.
 */
public class NormalHomeFragment extends BaseFragment {

    private ListView normalHomeListview;
    private ListviewNormalHomeAdapter adapter;

    public static Fragment createFragment(String data){
        Fragment fragment = new NormalHomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data", data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_home_normal;
    }

    @Override
    public void initView(View view) {
        normalHomeListview = findView(R.id.normal_home_listview);
        adapter = new ListviewNormalHomeAdapter(context);
        normalHomeListview.setAdapter(adapter);


    }

    @Override
    public void initData() {
        Bundle bundle = getArguments();
        String str = bundle.getString("data");


    }
}
