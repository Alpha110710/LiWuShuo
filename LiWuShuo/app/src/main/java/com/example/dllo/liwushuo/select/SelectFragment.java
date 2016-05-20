package com.example.dllo.liwushuo.select;

import android.view.View;
import android.widget.GridView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/19.
 */
public class SelectFragment extends BaseFragment {
    private GridView selectGridview;
    private SelectGridViewAdapter adapter;
    private ArrayList<SelectBean> selectBeans;

    @Override
    public int setLayout() {
        return R.layout.fragment_select;
    }

    @Override
    public void initView(View view) {
        selectGridview = findView(R.id.select_gridview);


    }

    @Override
    public void initData() {
        adapter = new SelectGridViewAdapter(context);
        initBeans();

        adapter.setSelectBeans(selectBeans);

        selectGridview.setAdapter(adapter);


    }

    private void initBeans() {
        selectBeans = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            SelectBean bean = new SelectBean();
            bean.setName("520必备味蕾之诗.最萌吃货进口零食礼盒");
            bean.setPrice("89.00");
            bean.setFavorites_count("13605");
            selectBeans.add(bean);
        }
    }
}
