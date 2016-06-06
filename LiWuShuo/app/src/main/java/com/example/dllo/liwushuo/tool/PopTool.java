package com.example.dllo.liwushuo.tool;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.liwushuo.R;

import java.security.acl.Group;

import static com.example.dllo.liwushuo.R.id.item_home_share_popup_wechat_timeline_tv;

/**
 * Created by dllo on 16/6/3.
 */
public class PopTool implements View.OnClickListener {

    private PopupWindow popupWindow;
    private SortOnClickListener sortOnClickListener;
    private Activity activity;
    private int id;
    private int style;
    private ImageView sortDefaultImg, sortHotImg, sortPriceHighToLowImg, sortPriceLowToHighImg;
    private int pos = 0;

    public PopTool(Activity activity, int id, int style) {
        this.activity = activity;
        this.id = id;
        this.style = style;

        popupWindow = new PopupWindow(440, WindowManager.LayoutParams.WRAP_CONTENT);


    }

    public PopTool(Activity activity, int id) {
        this.id = id;
        this.activity = activity;
        popupWindow = new PopupWindow(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT) {
            //构造方法中重写dismiss方法
            @Override
            public void dismiss() {
                super.dismiss();
                // 重写dismiss方法 改回父容器背景透明度
                WindowManager.LayoutParams params1 = PopTool.this.activity.getWindow().getAttributes();
                params1.alpha = 1;
                PopTool.this.activity.getWindow().setAttributes(params1);

            }

        };


    }

    public void setSortOnClickListener(SortOnClickListener sortOnClickListener) {
        this.sortOnClickListener = sortOnClickListener;
    }

    public void showSortPopupWindow() {
        popupWindow.setFocusable(true);
        ColorDrawable colorDrawable = new ColorDrawable(0X00FFFFFF);
        popupWindow.setBackgroundDrawable(colorDrawable);
        View view = LayoutInflater.from(activity).inflate(R.layout.item_gift_condition_sort_popup, null);
        popupWindow.setContentView(view);

        view.findViewById(R.id.sort_default_rlayout).setOnClickListener(this);
        view.findViewById(R.id.sort_hot_rlayout).setOnClickListener(this);
        view.findViewById(R.id.sort_price_high_to_low_rlayout).setOnClickListener(this);
        view.findViewById(R.id.sort_price_low_to_high_rlayout).setOnClickListener(this);

        sortDefaultImg = (ImageView) view.findViewById(R.id.sort_default_img);
        sortHotImg = (ImageView) view.findViewById(R.id.sort_hot_img);
        sortPriceHighToLowImg = (ImageView) view.findViewById(R.id.sort_price_high_to_low_img);
        sortPriceLowToHighImg = (ImageView) view.findViewById(R.id.sort_price_low_to_high_img);

        popupWindow.showAsDropDown(activity.findViewById(id));
        //设置四个小对号  可见不可见
        if (pos == 0) {
            sortDefaultImg.setVisibility(View.VISIBLE);
        } else {
            sortDefaultImg.setVisibility(View.GONE);
        }
        if (pos == 1) {
            sortHotImg.setVisibility(View.VISIBLE);
        } else {
            sortHotImg.setVisibility(View.GONE);
        }
        if (pos == 2) {
            sortPriceHighToLowImg.setVisibility(View.VISIBLE);
        } else {
            sortPriceHighToLowImg.setVisibility(View.GONE);
        }
        if (pos == 3) {
            sortPriceLowToHighImg.setVisibility(View.VISIBLE);
        } else {
            sortPriceLowToHighImg.setVisibility(View.GONE);
        }

    }


    public void showShortSortPopupWindow() {
        popupWindow.setFocusable(true);
        ColorDrawable colorDrawable = new ColorDrawable(0X00FFFFFF);
        popupWindow.setBackgroundDrawable(colorDrawable);
        View view = LayoutInflater.from(activity).inflate(R.layout.item_gift_condition_sort_popup, null);
        popupWindow.setContentView(view);

        view.findViewById(R.id.sort_default_rlayout).setOnClickListener(this);
        view.findViewById(R.id.sort_hot_rlayout).setOnClickListener(this);
        RelativeLayout sort_price_high_to_low_rlayout = (RelativeLayout) view.findViewById(R.id.sort_price_high_to_low_rlayout);
        RelativeLayout sort_price_low_to_high_rlayout = (RelativeLayout) view.findViewById(R.id.sort_price_low_to_high_rlayout);
        TextView sort_hot_line_tv = (TextView) view.findViewById(R.id.sort_hot_line_tv);
        TextView sort_price_low_to_high_line_tv = (TextView) view.findViewById(R.id.sort_price_low_to_high_line_tv);
        sort_price_high_to_low_rlayout.setVisibility(View.GONE);
        sort_price_low_to_high_rlayout.setVisibility(View.GONE);
        sort_hot_line_tv.setVisibility(View.GONE);
        sort_price_low_to_high_line_tv.setVisibility(View.GONE);

        sortDefaultImg = (ImageView) view.findViewById(R.id.sort_default_img);
        sortHotImg = (ImageView) view.findViewById(R.id.sort_hot_img);


        popupWindow.showAsDropDown(activity.findViewById(id));
        //设置四个小对号  可见不可见
        if (pos == 0) {
            sortDefaultImg.setVisibility(View.VISIBLE);
        } else {
            sortDefaultImg.setVisibility(View.GONE);
        }
        if (pos == 1) {
            sortHotImg.setVisibility(View.VISIBLE);
        } else {
            sortHotImg.setVisibility(View.GONE);
        }


    }


    public void showSharePopupWindow() {
        //设置焦点 实现当点击window外面消失
        popupWindow.setFocusable(true);
        //设置背景 实现当点击window外面消失
//        ColorDrawable drawable = new ColorDrawable(0xb0000000);//半透明
        ColorDrawable drawable = new ColorDrawable(0XFFFFFFFF);//白色
        popupWindow.setBackgroundDrawable(drawable);
        View view = LayoutInflater.from(activity).inflate(R.layout.item_home_detail_share_popup, null);
        view.findViewById(R.id.item_home_share_popup_hyperlink_tv).setOnClickListener(this);
        view.findViewById(R.id.item_home_share_popup_sina_tv).setOnClickListener(this);
        view.findViewById(R.id.item_home_share_popup_tencent_qq_tv).setOnClickListener(this);
        view.findViewById(R.id.item_home_share_popup_tencent_tv).setOnClickListener(this);
        view.findViewById(item_home_share_popup_wechat_timeline_tv).setOnClickListener(this);
        view.findViewById(R.id.item_home_share_popup_wechat_tv).setOnClickListener(this);
        view.findViewById(R.id.item_home_share_popup_cancel_tv).setOnClickListener(this);

        //设置自定义view布局
        popupWindow.setContentView(view);
        //设置动画
        popupWindow.setAnimationStyle(R.style.anim_home_share_popup);
        //在底部显示
        popupWindow.showAtLocation(activity.findViewById(id),
                Gravity.BOTTOM, 0, 0);
        //设置父容器背景透明度
        final WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.alpha = 0.7f;
        activity.getWindow().setAttributes(params);


    }


    //popupWindow点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_home_share_popup_hyperlink_tv:
                Toast.makeText(activity, "hy", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_home_share_popup_sina_tv:

                Toast.makeText(activity, "item_home_share_popup_sina_tv", Toast.LENGTH_SHORT).show();

                break;
            case R.id.item_home_share_popup_tencent_qq_tv:
                Toast.makeText(activity, "item_home_share_popup_tencent_qq_tv", Toast.LENGTH_SHORT).show();

                break;
            case R.id.item_home_share_popup_tencent_tv:
                Toast.makeText(activity, "item_home_share_popup_tencent_tv", Toast.LENGTH_SHORT).show();

                break;
            case R.id.item_home_share_popup_wechat_timeline_tv:
                Toast.makeText(activity, "item_home_share_popup_wechat_timeline_tv", Toast.LENGTH_SHORT).show();

                break;
            case R.id.item_home_share_popup_wechat_tv:
                Toast.makeText(activity, "item_home_share_popup_wechat_tv", Toast.LENGTH_SHORT).show();

                break;
            case R.id.item_home_share_popup_cancel_tv:
                popupWindow.dismiss();
                break;

            //sort的组件
            case R.id.sort_default_rlayout:
                sortOnClickListener.getDefaultUrl();
                popupWindow.dismiss();
                pos = 0;
                break;
            case R.id.sort_hot_rlayout:
                sortOnClickListener.getHotUrl();
                popupWindow.dismiss();
                pos = 1;

                break;
            case R.id.sort_price_high_to_low_rlayout:
                sortOnClickListener.getPriceHighToLow();
                popupWindow.dismiss();
                pos = 2;

                break;
            case R.id.sort_price_low_to_high_rlayout:
                sortOnClickListener.getPriceLowToHigh();
                popupWindow.dismiss();
                pos = 3;
                break;
        }
    }

    public interface SortOnClickListener {
        void getHotUrl();

        void getDefaultUrl();

        void getPriceHighToLow();

        void getPriceLowToHigh();

    }

}
