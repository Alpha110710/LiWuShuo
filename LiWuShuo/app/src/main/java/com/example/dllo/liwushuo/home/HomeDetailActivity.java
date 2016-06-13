package com.example.dllo.liwushuo.home;

import android.content.Intent;
import android.util.Log;
import android.view.View;
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
import com.example.dllo.liwushuo.register.BmobRaidersBean;
import com.example.dllo.liwushuo.register.RegisterActivity;
import com.example.dllo.liwushuo.tool.CollectCheckBoxTool;
import com.example.dllo.liwushuo.tool.NetTool;
import com.example.dllo.liwushuo.tool.PopTool;
import com.google.gson.Gson;

import java.util.ArrayList;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

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
    private CollectCheckBoxTool checkBoxTool;
    private BmobRaidersBean collectRaidersBean;
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
        checkBoxTool = new CollectCheckBoxTool(this);
        checkBoxTool.queryAllLike();


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
                //设置checkBox喜欢状态
                checkBoxTool.queryIsLikeRaiders(String.valueOf(homeDetailBean.getData().getId()), homeDetailLike);


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
                //设置checkBox点击事件 点击收藏  或取消
                BmobUser bmobUser = BmobUser.getCurrentUser(this);
                if (bmobUser == null) {
                    Intent intent = new Intent(this, RegisterActivity.class);
                    this.startActivity(intent);
                    homeDetailLike.setChecked(false);
                } else {

                    if (homeDetailLike.isChecked()) {
                        //像网络数据库传入数据 title isLike imgUrl raiders id
                        collectRaidersBean = new BmobRaidersBean();

                        collectRaidersBean.setId(String.valueOf(homeDetailBean.getData().getId()));
                        collectRaidersBean.setImgurl(homeDetailBean.getData().getCover_image_url());
                        collectRaidersBean.setRaiders(true);
                        collectRaidersBean.setTitle(homeDetailBean.getData().getTitle());
                        collectRaidersBean.setUserName(bmobUser.getUsername());

                        collectRaidersBean.save(this, new SaveListener() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(HomeDetailActivity.this, "喜欢成功", Toast.LENGTH_SHORT).show();
                                //查询方法
                                checkBoxTool.queryAllLike();
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Toast.makeText(HomeDetailActivity.this, "喜欢失败" + s, Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        //取消喜欢
                        checkBoxTool.cancleLikeRaiders(String.valueOf(homeDetailBean.getData().getId()));
                        checkBoxTool.queryAllLike();
                    }
                }


                break;
            case R.id.home_detail_share:
                //popupwindow的调用
                PopTool popTool = new PopTool(this, R.id.home_detail_share);
                popTool.showSharePopupWindow();
                break;
            case R.id.home_detail_comment:
                Intent intent = new Intent();
                intent.setClass(this, HomeConmentActivity.class);
                intent.putExtra("contentDownId", String.valueOf(homeDetailBean.getData().getId()));
                startActivity(intent);
                break;


        }
    }


}
