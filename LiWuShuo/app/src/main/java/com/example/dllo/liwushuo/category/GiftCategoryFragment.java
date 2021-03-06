package com.example.dllo.liwushuo.category;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
public class GiftCategoryFragment extends BaseFragment implements View.OnClickListener {

    private ListView fragmentCategoryGiftListviewLeft;
    private ListView fragmentCategoryGiftListviewRight;
    private TextView fragmentCategoryGiftArtifactTv;
    private GiftCategoryListviewLeftAdapter leftAdapter;
    private GiftCategoryListviewRightAdapter rightAdapter;
    private NetTool netTool = new NetTool();
    private int lastPos = 0;


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

        fragmentCategoryGiftArtifactTv.setOnClickListener(this);

        getGiftBean();
        fragmentCategoryGiftListviewLeft.setAdapter(leftAdapter);
        fragmentCategoryGiftListviewRight.setAdapter(rightAdapter);
        //设置左右listview关系变化
        setLeftRightListviewChange();




    }


    //设置左右listview关系变化
    private void setLeftRightListviewChange() {

        fragmentCategoryGiftListviewLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //给适配器传入当前点击位置
                leftAdapter.setSelectPos(position);
                //右侧listview设置当前位置与左侧位置保持一致
                fragmentCategoryGiftListviewRight.setSelection(position);
                //刷新
                rightAdapter.notifyDataSetInvalidated();
            }
        });
        //右侧listview滑动监听
        fragmentCategoryGiftListviewRight.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //
                if (lastPos != firstVisibleItem) {
                    lastPos = firstVisibleItem;

                    fragmentCategoryGiftListviewLeft.smoothScrollToPosition(firstVisibleItem);
                    leftAdapter.setSelectPos(firstVisibleItem);

                }
            }
        });
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


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_category_gift_artifact_tv:
                Intent intent = new Intent(context, GiftConditionSelectActivity.class);
                startActivity(intent);
                break;
        }
    }
}
