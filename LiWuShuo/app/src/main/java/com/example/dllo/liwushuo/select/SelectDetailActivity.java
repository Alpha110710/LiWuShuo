package com.example.dllo.liwushuo.select;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;

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

    @Override
    public void initActivity() {
        setContentView(R.layout.activity_select_detail);
        selectDetailBackImg = (ImageView) findViewById(R.id.select_detail_back_img);
        selectDetailFavoritesImg = (CheckBox) findViewById(R.id.select_detail_favorites_img);
        selectDetailBarTv = (TextView) findViewById(R.id.select_detail_bar_tv);
        selectDetailCommentsImg = (ImageView) findViewById(R.id.select_detail_comments_img);
        selectDetailWebview = (WebView) findViewById(R.id.select_detail_webview);
        selectDetailShareImg = (ImageView) findViewById(R.id.select_detail_share_img);

        //调下面的方法,连接淘宝网
        getWebviewUrl();

        selectDetailBackImg.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_detail_back_img:
                finish();
                break;
        }
    }

    //连接淘宝网络,接到SelectFragment传来的实体内部类
    private void getWebviewUrl(){
        Intent intent = getIntent();
        final SelectBean.DataBean.ItemsBean itemsBean = (SelectBean.DataBean.ItemsBean) intent.getSerializableExtra("selectDetailUrl");
        String url = itemsBean.getData().getPurchase_url();
        selectDetailWebview.loadUrl(url);
        selectDetailWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                selectDetailWebview.loadUrl(url);
                selectDetailBarTv.setText(itemsBean.getData().getName());
                return true;
            }
        });
    }

}
