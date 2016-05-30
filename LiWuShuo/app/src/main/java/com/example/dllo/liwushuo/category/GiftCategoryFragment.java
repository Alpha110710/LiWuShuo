package com.example.dllo.liwushuo.category;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.category.adapter.GiftCategoryListviewLeftAdapter;
import com.example.dllo.liwushuo.category.adapter.GiftCategoryListviewRightAdapter;
import com.example.dllo.liwushuo.category.bean.GiftBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.tool.NetTool;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/5/20.
 */
public class GiftCategoryFragment extends BaseFragment {

    private ListView fragmentCategoryGiftListviewLeft;
    private ListView fragmentCategoryGiftListviewRight;
    private TextView fragmentCategoryGiftArtifactTv;
    private GiftCategoryListviewLeftAdapter leftAdapter;
    private GiftCategoryListviewRightAdapter rightAdapter;
    private NetTool netTool = new NetTool();

    @Override
    public int setLayout() {
        return R.layout.fragment_category_gift;
    }

    @Override
    public void initView(View view) {
        fragmentCategoryGiftListviewLeft = (ListView) view.findViewById(R.id.fragment_category_gift_listview_left);
        fragmentCategoryGiftListviewRight = (ListView) view.findViewById(R.id.fragment_category_gift_listview_right);
        fragmentCategoryGiftArtifactTv = (TextView) view.findViewById(R.id.fragment_category_gift_artifact_tv);
    }

    @Override
    public void initData() {
        leftAdapter = new GiftCategoryListviewLeftAdapter(context);
        rightAdapter = new GiftCategoryListviewRightAdapter(context);

        getGiftBean();
        fragmentCategoryGiftListviewLeft.setAdapter(leftAdapter);
        fragmentCategoryGiftListviewRight.setAdapter(rightAdapter);

    }

    private void getGiftBean() {
        netTool.getAnalysis(URLValues.GIFT_LISTVIEW, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                GiftBean giftBean = gson.fromJson(response, GiftBean.class);
                leftAdapter.setGiftBean(giftBean);
                rightAdapter.setGiftBean(giftBean);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }


}
