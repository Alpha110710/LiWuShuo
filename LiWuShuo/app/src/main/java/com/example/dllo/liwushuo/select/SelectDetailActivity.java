package com.example.dllo.liwushuo.select;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;
import com.example.dllo.liwushuo.category.bean.GiftSelectWebBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.tool.NetTool;
import com.example.dllo.liwushuo.tool.PopTool;
import com.google.gson.Gson;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by dllo on 16/5/25.
 */
public class SelectDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView selectDetailBackImg;
    private CheckBox selectDetailFavoritesImg;
    private TextView selectDetailBarTv;
    private ImageView selectDetailCommentsImg;
    private WebView selectDetailWebview;
    private ImageView selectDetailShareImg;
    private SelectBean selectBean;
    private NetTool netTool = new NetTool();
    private GiftSelectWebBean giftSelectWebBean;


    @Override
    public void initActivity() {
        setContentView(R.layout.activity_select_detail);
        selectDetailBackImg = (ImageView) findViewById(R.id.select_detail_back_img);
        selectDetailFavoritesImg = (CheckBox) findViewById(R.id.select_detail_favorites_img);
        selectDetailBarTv = (TextView) findViewById(R.id.select_detail_bar_tv);
        selectDetailCommentsImg = (ImageView) findViewById(R.id.select_detail_comments_img);
        selectDetailWebview = (WebView) findViewById(R.id.select_detail_webview);
        selectDetailShareImg = (ImageView) findViewById(R.id.select_detail_share_img);

        selectDetailShareImg.setOnClickListener(this);

        //调下面的方法,连接淘宝网
        getWebviewUrl();

        selectDetailBackImg.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_detail_back_img:
                finish();
                break;
            case R.id.select_detail_share_img:
                PopTool popTool = new PopTool(this, R.id.select_detail_share_img );
                popTool.showSharePopupWindow();
                break;
        }
    }

    //连接淘宝网络,接到SelectFragment传来的实体内部类
    private void getWebviewUrl() {
        final Intent intent = getIntent();

        //if判断  用于解析gift挑选界面的webview跳转  需要拼接id
        if (intent.getStringExtra("urlWebId") != null) {

            netTool.getAnalysis(intent.getStringExtra("urlWebId"), new NetListener() {
                @Override
                public void onSuccessed(String response) {
                    Gson gson = new Gson();
                    giftSelectWebBean = gson.fromJson(response, GiftSelectWebBean.class);

                    selectDetailWebview.loadUrl(giftSelectWebBean.getData().getPurchase_url());

                    selectDetailWebview.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            view.loadUrl(url);
                            if (intent.getStringExtra("name") != null) {
                                selectDetailBarTv.setText(giftSelectWebBean.getData().getName());
                            }
                            return true;
                        }
                    });
                }

                @Override
                public void onFailed(VolleyError error) {

                }
            });

            //else用于select界面和礼物分类小圆圈图片界面的点击进入跳webview  只有接到对应url和name
        } else {
            selectDetailWebview.loadUrl(intent.getStringExtra("url"));

            selectDetailWebview.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);

                    if (intent.getStringExtra("name") != null) {
                        selectDetailBarTv.setText(intent.getStringExtra("name"));
                    }
                    return true;
                }
            });
        }


    }


}
