package com.example.dllo.liwushuo.home;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;
import com.example.dllo.liwushuo.home.bean.HomeDetailBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.tool.NetTool;
import com.google.gson.Gson;

import java.util.ArrayList;

import static com.example.dllo.liwushuo.R.id.item_home_share_popup_wechat_timeline_tv;

/**
 * Created by dllo on 16/6/2.
 */
public class HomeDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView homeDetailBackImg;
    private TextView homeDetailBarTv;
    private CheckBox homeDetailLike;
    private TextView homeDetailShare;
    private TextView homeDetailComment;
    private WebView homeDetailWebview;
    private ArrayList<String> urlIds;
    private int pos;
    private NetTool netTool = new NetTool();
    private Gson gson = new Gson();
    private HomeDetailBean homeDetailBean;
    private PopupWindow homeSharePopup;
    private TextView itemHomeSharePopupWechatTimelineTv, itemHomeSharePopupWechatTv, itemHomeSharePopupSinaTv, itemHomeSharePopupTencentTv, itemHomeSharePopupTencentQqTv, itemHomeSharePopupHyperlinkTv, itemomeSharePopupCancelTv;

    @Override
    public void initActivity() {
        setContentView(R.layout.acitivity_home_detail);
        homeDetailBackImg = (ImageView) findViewById(R.id.home_detail_back_img);
        homeDetailBarTv = (TextView) findViewById(R.id.home_detail_bar_tv);
        homeDetailLike = (CheckBox) findViewById(R.id.home_detail_like);
        homeDetailShare = (TextView) findViewById(R.id.home_detail_share);
        homeDetailComment = (TextView) findViewById(R.id.home_detail_comment);
        homeDetailWebview = (WebView) findViewById(R.id.home_detail_webview);

        homeDetailBackImg.setOnClickListener(this);
        homeDetailLike.setOnClickListener(this);
        homeDetailShare.setOnClickListener(this);
        homeDetailComment.setOnClickListener(this);


        //intent接数据 接到url集合  和pos
        Intent intent = getIntent();
        urlIds = intent.getStringArrayListExtra("urlId");
        pos = intent.getIntExtra("urlPos", 0);

        String homeDetailUrl = URLValues.HOME_DETAIL_BEFORE + urlIds.get(pos) + URLValues.HOME_DETAIL_AFTER;

        netTool.getAnalysis(homeDetailUrl, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                homeDetailBean = gson.fromJson(response, HomeDetailBean.class);
                //设置喜欢数  分享数  评论数
                homeDetailLike.setText(String.valueOf(homeDetailBean.getData().getLikes_count()));
                homeDetailComment.setText(String.valueOf(homeDetailBean.getData().getComments_count()));
                homeDetailShare.setText(String.valueOf(homeDetailBean.getData().getShares_count()));

                //加载webview
                homeDetailWebview.loadUrl(homeDetailBean.getData().getUrl());
                homeDetailWebview.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_detail_back_img:
                finish();
                break;
            case R.id.home_detail_like:
                break;
            case R.id.home_detail_share:
                //实例化popupwindow
                initHomeSharePopup();
                homeSharePopup.showAtLocation(homeDetailShare, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.home_detail_comment:
                break;
            case R.id.item_home_share_popup_hyperlink_tv:
                Toast.makeText(this, "hy", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_home_share_popup_sina_tv:

                Toast.makeText(this, "item_home_share_popup_sina_tv", Toast.LENGTH_SHORT).show();

                break;
            case R.id.item_home_share_popup_tencent_qq_tv:
                Toast.makeText(this, "item_home_share_popup_tencent_qq_tv", Toast.LENGTH_SHORT).show();

                break;
            case R.id.item_home_share_popup_tencent_tv:
                Toast.makeText(this, "item_home_share_popup_tencent_tv", Toast.LENGTH_SHORT).show();

                break;
            case R.id.item_home_share_popup_wechat_timeline_tv:
                Toast.makeText(this, "item_home_share_popup_wechat_timeline_tv", Toast.LENGTH_SHORT).show();

                break;
            case R.id.item_home_share_popup_wechat_tv:
                Toast.makeText(this, "item_home_share_popup_wechat_tv", Toast.LENGTH_SHORT).show();

                break;
            case R.id.item_home_share_popup_cancel_tv:
                homeSharePopup.dismiss();
                break;


        }
    }

    private void initHomeSharePopup() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_home_detail_share_popup, null);
        homeSharePopup = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        itemHomeSharePopupHyperlinkTv = (TextView) view.findViewById(R.id.item_home_share_popup_hyperlink_tv);
        itemHomeSharePopupSinaTv = (TextView) view.findViewById(R.id.item_home_share_popup_sina_tv);
        itemHomeSharePopupTencentQqTv = (TextView) view.findViewById(R.id.item_home_share_popup_tencent_qq_tv);
        itemHomeSharePopupTencentTv = (TextView) view.findViewById(R.id.item_home_share_popup_tencent_tv);
        itemHomeSharePopupWechatTimelineTv = (TextView) view.findViewById(item_home_share_popup_wechat_timeline_tv);
        itemHomeSharePopupWechatTv = (TextView) view.findViewById(R.id.item_home_share_popup_wechat_tv);
        itemomeSharePopupCancelTv = (TextView) view.findViewById(R.id.item_home_share_popup_cancel_tv);

        itemHomeSharePopupWechatTimelineTv.setOnClickListener(this);
        itemHomeSharePopupHyperlinkTv.setOnClickListener(this);
        itemHomeSharePopupSinaTv.setOnClickListener(this);
        itemHomeSharePopupTencentQqTv.setOnClickListener(this);
        itemHomeSharePopupTencentTv.setOnClickListener(this);
        itemHomeSharePopupWechatTv.setOnClickListener(this);
        itemomeSharePopupCancelTv.setOnClickListener(this);

        ColorDrawable colorDrawable = new ColorDrawable(0x00FFFFFF);
        homeSharePopup.setBackgroundDrawable(colorDrawable);
        homeSharePopup.setOutsideTouchable(true);
        homeSharePopup.setAnimationStyle(R.style.anim_home_share_popup);

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = 0.7f;
        getWindow().setAttributes(layoutParams);

        homeSharePopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });
    }
}
