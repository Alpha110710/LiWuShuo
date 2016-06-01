package com.example.dllo.liwushuo.category;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;
import com.example.dllo.liwushuo.category.adapter.CreateSimpleAdapter;
import com.example.dllo.liwushuo.category.adapter.GiftConditionSelectPopupGridviewAdapter;
import com.example.dllo.liwushuo.category.adapter.GiftDetailsGridviewAdapter;
import com.example.dllo.liwushuo.category.bean.GiftConditionPopupBean;
import com.example.dllo.liwushuo.category.bean.GiftDetailBean;
import com.example.dllo.liwushuo.category.bean.GiftSelectWebBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.select.SelectDetailActivity;
import com.example.dllo.liwushuo.tool.App;
import com.example.dllo.liwushuo.tool.NetTool;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

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
    private GiftSelectWebBean giftSelectWebBean;
    private RadioButton giftConditionSelectObjRb, giftConditionSelectOccasionRb, giftConditionSelectHabitRb, giftConditionSelectPriceRb;
    private PopupWindow conditionPopup;
    private GridView giftConditionSelectPopupGridview;
    private GiftConditionPopupBean giftConditionPopupBean;
    private GiftConditionSelectPopupGridviewAdapter giftConditionSelectPopupGridviewAdapter;


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


        //复用giftDetailActivity适配器
        giftDetailsGridviewAdapter = new GiftDetailsGridviewAdapter(this);
        giftConditionSelectGridview.setAdapter(giftDetailsGridviewAdapter);


        //TODO:当筛选条件不同对应url也不同
        netTool.getAnalysis(URLValues.SELECT_GIFT, new NetListener() {
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


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String urlWebId = URLValues.SELECT_GIFT_WEB + String.valueOf(giftDetailBean.getData().getItems().get(position).getId());

        Intent intent = new Intent(this, SelectDetailActivity.class);
        //intent跳转传入对应id
        intent.putExtra("urlWebId", urlWebId);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gift_condition_select_back_img:
                finish();
                break;
            case R.id.gift_condition_select_sort_img:
                break;
            case R.id.gift_condition_select_obj_rb:
                initConditionPopup(0);
                conditionPopup.showAsDropDown(giftConditionSelectObjRb);
                break;
            case R.id.gift_condition_select_occasion_rb:
                initConditionPopup(1);
                conditionPopup.showAsDropDown(giftConditionSelectObjRb);

                break;
            case R.id.gift_condition_select_habit_rb:
                initConditionPopup(2);
                conditionPopup.showAsDropDown(giftConditionSelectObjRb);

                break;

            case R.id.gift_condition_select_price_rb:
                initConditionPopup(3);
                conditionPopup.showAsDropDown(giftConditionSelectObjRb);

                break;

        }
    }


    private void initConditionPopup(final int i) {
        final RadioButton[] radioButtons = {giftConditionSelectObjRb, giftConditionSelectOccasionRb, giftConditionSelectHabitRb, giftConditionSelectPriceRb};

        final ArrayList<String> beans = new ArrayList<>();
        beans.add("全部");
        for (int i1 = 0; i1 < giftConditionPopupBean.getData().getFilters().get(i).getChannels().size(); i1++) {
            beans.add(giftConditionPopupBean.getData().getFilters().get(i).getChannels().get(i1).getName());
        }


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
                Toast.makeText(GiftConditionSelectActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();
                giftConditionSelectPopupGridviewAdapter.setPos(position);
                giftConditionSelectPopupGridviewAdapter.notifyDataSetChanged();

                radioButtons[i].setText(beans.get(position));


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

    private void refreshUrl(int i, int pos) {
        //TODO:拼url
//        int url = URLValues.SCREEN_REFRESH_HEAD_TARET + giftConditionPopupBean.getData().getFilters().get(i).getKey()
    }

}
