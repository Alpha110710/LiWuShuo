package com.example.dllo.liwushuo.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.home.adapter.ListviewNormalHomeAdapter;
import com.example.dllo.liwushuo.home.bean.NormalListviewBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.tool.NetTool;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/5/21.
 */
public class NormalHomeFragment extends BaseFragment {

    private ListView normalHomeListview;
    private ListviewNormalHomeAdapter adapter;
    private NetTool netTool = new NetTool();

    public static Fragment createFragment(String url){
        Fragment fragment = new NormalHomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data", url);
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
        String url = bundle.getString("data");
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


    }


}
