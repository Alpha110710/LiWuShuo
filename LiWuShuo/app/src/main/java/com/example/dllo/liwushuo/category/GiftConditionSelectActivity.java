package com.example.dllo.liwushuo.category;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;
import com.example.dllo.liwushuo.category.adapter.GiftConditionSelectPopupGridviewAdapter;
import com.example.dllo.liwushuo.category.adapter.GiftDetailsGridviewAdapter;
import com.example.dllo.liwushuo.category.bean.GiftConditionPopupBean;
import com.example.dllo.liwushuo.category.bean.GiftDetailBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.select.SelectDetailActivity;
import com.example.dllo.liwushuo.tool.NetTool;
import com.example.dllo.liwushuo.tool.PopTool;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/31.
 */
public class GiftConditionSelectActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private GridView giftConditionSelectGridview;
    private ImageView giftConditionSelectBackImg;
    private ImageView giftConditionSelectSortImg;
    private GiftDetailsGridviewAdapter giftDetailsGridviewAdapter;
    private NetTool netTool = new NetTool();
    private GiftDetailBean giftDetailBean;
    private RadioButton giftConditionSelectObjRb, giftConditionSelectOccasionRb, giftConditionSelectHabitRb, giftConditionSelectPriceRb;
    private PopupWindow conditionPopup;
    private GridView giftConditionSelectPopupGridview;
    private GiftConditionPopupBean giftConditionPopupBean;
    private GiftConditionSelectPopupGridviewAdapter giftConditionSelectPopupGridviewAdapter;
    private int radioPos;
    private PopTool popTool;

    private String screenRefresh;
    private String littlePopParams = "";
    //选礼神奇点击进入筛选条件点击完的重新刷新下面小四方块url  参数拼的是"http://api.liwushuo.com/v2/search/item_filter每一个子key
    private String[] url = new String[4];

    @Override
    public void initActivity() {
        setContentView(R.layout.activity_gift_condition_select);
        giftConditionSelectGridview = (GridView) findViewById(R.id.gift_condition_select_gridview);
        giftConditionSelectBackImg = (ImageView) findViewById(R.id.gift_condition_select_back_img);
        giftConditionSelectSortImg = (ImageView) findViewById(R.id.gift_condition_select_sort_img);
        giftConditionSelectObjRb = (RadioButton) findViewById(R.id.gift_condition_select_obj_rb);
        giftConditionSelectOccasionRb = (RadioButton) findViewById(R.id.gift_condition_select_occasion_rb);
        giftConditionSelectHabitRb = (RadioButton) findViewById(R.id.gift_condition_select_habit_rb);
        giftConditionSelectPriceRb = (RadioButton) findViewById(R.id.gift_condition_select_price_rb);


        giftConditionSelectGridview.setOnItemClickListener(this);
        giftConditionSelectBackImg.setOnClickListener(this);
        giftConditionSelectObjRb.setOnClickListener(this);
        giftConditionSelectOccasionRb.setOnClickListener(this);
        giftConditionSelectHabitRb.setOnClickListener(this);
        giftConditionSelectPriceRb.setOnClickListener(this);
        giftConditionSelectSortImg.setOnClickListener(this);
        popTool = new PopTool(this, R.id.gift_condition_select_sort_img, 1);

        //数组初始化
        for (int i1 = 0; i1 < url.length; i1++) {
            url[i1] = "";
        }


        //复用giftDetailActivity适配器
        giftDetailsGridviewAdapter = new GiftDetailsGridviewAdapter(this);
        giftConditionSelectGridview.setAdapter(giftDetailsGridviewAdapter);


        getItemAndRefreshAnlysis(URLValues.SELECT_GIFT);


        netTool.getAnalysis(URLValues.SELECT_POPUP, new NetListener() {
            @Override
            public void onSuccessed(String response) {

                Gson gson = new Gson();
                giftConditionPopupBean = gson.fromJson(response, GiftConditionPopupBean.class);

                giftConditionSelectObjRb.setText(giftConditionPopupBean.getData().getFilters().get(0).getName());
                giftConditionSelectOccasionRb.setText(giftConditionPopupBean.getData().getFilters().get(1).getName());
                giftConditionSelectHabitRb.setText(giftConditionPopupBean.getData().getFilters().get(2).getName());
                giftConditionSelectPriceRb.setText(giftConditionPopupBean.getData().getFilters().get(3).getName());


            }


            @Override
            public void onFailed(VolleyError error) {
                giftConditionSelectObjRb.setText("对象");
                giftConditionSelectOccasionRb.setText("场合");
                giftConditionSelectHabitRb.setText("个性");
                giftConditionSelectPriceRb.setText("价格");
            }
        });

        popTool.setSortOnClickListener(new PopTool.SortOnClickListener() {
            @Override
            public void getHotUrl() {
                littlePopParams = "&sort=hot";
                if (screenRefresh == null) {
                    String hotUrl = "http://api.liwushuo.com/v2/search/item_by_type?target=&limit=20&scene=&price=&sort=hot&personality=&offset=0";
                    getItemAndRefreshAnlysis(hotUrl);
                } else {
                    getItemAndRefreshAnlysis(screenRefresh + littlePopParams);
                }
            }

            @Override
            public void getDefaultUrl() {
                littlePopParams = "";
                if (screenRefresh == null) {
                    String defaultUrl = "http://api.liwushuo.com/v2/search/item_by_type?limit=20&offset=0";
                    getItemAndRefreshAnlysis(defaultUrl);
                } else {
                    getItemAndRefreshAnlysis(screenRefresh + littlePopParams);
                }
            }

            @Override
            public void getPriceHighToLow() {
                littlePopParams = "&sort=price:asc";
                if (screenRefresh == null) {
                    String priceHighToLow = "http://api.liwushuo.com/v2/search/item_by_type?target=&limit=20&scene=&price=&sort=price:asc&personality=&offset=0";
                    getItemAndRefreshAnlysis(priceHighToLow);
                } else {
                    getItemAndRefreshAnlysis(screenRefresh + littlePopParams);
                }

            }

            @Override
            public void getPriceLowToHigh() {
                littlePopParams = "&sort=price:desc";
                if (screenRefresh == null) {
                    String priceLowToHigh = "http://api.liwushuo.com/v2/search/item_by_type?target=&limit=20&scene=&price=&sort=price:desc&personality=&offset=0";
                    getItemAndRefreshAnlysis(priceLowToHigh);
                } else {
                    getItemAndRefreshAnlysis(screenRefresh + littlePopParams);
                }

            }
        });


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String urlWebId = URLValues.SELECT_GIFT_WEB + String.valueOf(giftDetailBean.getData().getItems().get(position).getId());

        Intent intent = new Intent(this, SelectDetailActivity.class);
        //intent跳转传入对应id  只传一个selectDetailActivity用到的id就可以了
        intent.putExtra("urlWebId", urlWebId);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.gift_condition_select_back_img:
                finish();
                break;

            //点击出现排序pop
            case R.id.gift_condition_select_sort_img:
                popTool.showSortPopupWindow();


                break;
            case R.id.gift_condition_select_obj_rb:
                initConditionPopup(0);
                conditionPopup.showAsDropDown(giftConditionSelectObjRb);
                radioPos = 0;
                break;
            case R.id.gift_condition_select_occasion_rb:
                initConditionPopup(1);
                conditionPopup.showAsDropDown(giftConditionSelectObjRb);
                radioPos = 1;

                break;
            case R.id.gift_condition_select_habit_rb:
                initConditionPopup(2);
                conditionPopup.showAsDropDown(giftConditionSelectObjRb);
                radioPos = 2;

                break;

            case R.id.gift_condition_select_price_rb:
                initConditionPopup(3);
                conditionPopup.showAsDropDown(giftConditionSelectObjRb);
                radioPos = 3;

                break;


        }
    }


    //popupwindow代码
    private void initConditionPopup(final int i) {




        if (giftConditionPopupBean != null) {
            final RadioButton radioButtons[] = {giftConditionSelectObjRb, giftConditionSelectOccasionRb, giftConditionSelectHabitRb, giftConditionSelectPriceRb};


            final ArrayList<String> beans = new ArrayList<>();
            beans.add("全部");
            for (int i1 = 0; i1 < giftConditionPopupBean.getData().getFilters().get(i).getChannels().size(); i1++) {
                beans.add(giftConditionPopupBean.getData().getFilters().get(i).getChannels().get(i1).getName());
            }

            //popupwindow的一系列处理
            View view = LayoutInflater.from(this).inflate(R.layout.item_gift_condition_select_popup, null);
            conditionPopup = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, 600, true);

            giftConditionSelectPopupGridview = (GridView) view.findViewById(R.id.gift_condition_select_popup_gridview);
            giftConditionSelectPopupGridviewAdapter = new GiftConditionSelectPopupGridviewAdapter(this);
            giftConditionSelectPopupGridviewAdapter.setBeans(beans);
            giftConditionSelectPopupGridview.setAdapter(giftConditionSelectPopupGridviewAdapter);

            ColorDrawable colorDrawable = new ColorDrawable(0x00FFFFFF);
            conditionPopup.setBackgroundDrawable(colorDrawable);
            conditionPopup.setOutsideTouchable(true);
            conditionPopup.setAnimationStyle(R.style.anim_popupwindow);
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.alpha = 0.7f;
            getWindow().setAttributes(params);


            giftConditionSelectPopupGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    giftConditionSelectPopupGridviewAdapter.setPos(position);
                    giftConditionSelectPopupGridviewAdapter.notifyDataSetChanged();

                    radioButtons[i].setText(beans.get(position));

                    //点击刷新
                    choiceUrl(radioPos, position);
                    screenRefresh = "http://api.liwushuo.com/v2/search/item_by_type?limit=20&target=" + url[0] + "&scene="
                            + url[1] + "&price=" + url[3] + "&personality=" + url[2] + "&offset=0" + littlePopParams;

                    getItemAndRefreshAnlysis(screenRefresh);

                    conditionPopup.dismiss();

                }
            });


            conditionPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    radioButtons[i].setSelected(false);
                    WindowManager.LayoutParams params = getWindow().getAttributes();
                    params.alpha = 1f;
                    getWindow().setAttributes(params);
                }
            });

        }
    }


    //调用此方法即可获得筛选后的url
    private void choiceUrl(int radioPos, int position) {

        if (position == 0) {
            url[radioPos] = "";
        } else {
            url[radioPos] = giftConditionPopupBean.getData().getFilters().get(radioPos).getChannels().get(position - 1).getKey();

        }

    }


    //解析挑选礼物下面圆角图片的item数据
    private void getItemAndRefreshAnlysis(String url) {
        netTool.getAnalysis(url, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                giftDetailBean = gson.fromJson(response, GiftDetailBean.class);
                giftDetailsGridviewAdapter.setGiftDetailBean(giftDetailBean);

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }

}
