package com.example.dllo.liwushuo.search;

import android.view.View;
import android.widget.GridView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.select.SelectGridViewAdapter;

/**
 * Created by dllo on 16/6/6.
 */
public class SearchDetailGiftFragment extends BaseFragment {

    private GridView searchDetailGiftGridview;
    //复用SelectGridViewAdapter 适配器
    private SelectGridViewAdapter selectGridViewAdapter;


    @Override
    public int setLayout() {
        return R.layout.fragment_search_detail_gift;
    }

    @Override
    public void initView(View view) {
        searchDetailGiftGridview = (GridView) view.findViewById(R.id.search_detail_gift_gridview);

    }

    @Override
    public void initData() {

    }
}
