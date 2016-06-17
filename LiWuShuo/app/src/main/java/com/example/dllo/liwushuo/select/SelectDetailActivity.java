package com.example.dllo.liwushuo.select;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;
import com.example.dllo.liwushuo.category.bean.GiftSelectWebBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.register.BmobCollectBean;
import com.example.dllo.liwushuo.register.RegisterActivity;
import com.example.dllo.liwushuo.tool.CollectCheckBoxTool;
import com.example.dllo.liwushuo.tool.NetTool;
import com.example.dllo.liwushuo.tool.PopTool;
import com.google.gson.Gson;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindCallback;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/5/25.
 */
public class SelectDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView selectDetailBackImg;
    private CheckBox selectDetailFavoritesCb;
    private TextView selectDetailBarTv;
    private ImageView selectDetailCommentsImg;
    private WebView selectDetailWebview;
    private ImageView selectDetailShareImg;
    private SelectBean selectBean;
    private NetTool netTool = new NetTool();
    private GiftSelectWebBean giftSelectWebBean;
    private String idConment, url, name, price, imgUrl, likeNum;
    private BmobCollectBean collectBean;
    private Intent intent;
    private CollectCheckBoxTool checkBoxTool;


    @Override
    public void initActivity() {
        setContentView(R.layout.activity_select_detail);
        selectDetailBackImg = (ImageView) findViewById(R.id.select_detail_back_img);
        selectDetailFavoritesCb = (CheckBox) findViewById(R.id.select_detail_favorites_cb);
        selectDetailBarTv = (TextView) findViewById(R.id.select_detail_bar_tv);
        selectDetailCommentsImg = (ImageView) findViewById(R.id.select_detail_comments_img);
        selectDetailWebview = (WebView) findViewById(R.id.select_detail_webview);
        selectDetailShareImg = (ImageView) findViewById(R.id.select_detail_share_img);

        selectDetailShareImg.setOnClickListener(this);
        selectDetailCommentsImg.setOnClickListener(this);
        selectDetailFavoritesCb.setOnClickListener(this);

        checkBoxTool = new CollectCheckBoxTool(this);

        //调下面的方法,连接淘宝网
        getWebviewUrl();

        selectDetailBackImg.setOnClickListener(this);

        //查询是否已经标记为喜欢的方法
        checkBoxTool.queryIsLike(this.intent.getStringExtra("id"), selectDetailFavoritesCb);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_detail_back_img:
                finish();
                break;
            case R.id.select_detail_share_img:
                PopTool popTool = new PopTool(this, R.id.select_detail_share_img);
                popTool.showSharePopupWindow();
                break;
            case R.id.select_detail_comments_img:
                Intent intent = new Intent(this, SelectConmentActivity.class);
                intent.putExtra("conmentId", idConment);
                startActivity(intent);
                break;
            case R.id.select_detail_favorites_cb:
                //url, name, id, price, imgUrl, likeNum, like;
                idConment = this.intent.getStringExtra("id");
                price = this.intent.getStringExtra("price");
                name = this.intent.getStringExtra("name");
                imgUrl = this.intent.getStringExtra("imgUrl");
                likeNum = this.intent.getStringExtra("likeNum");
                url = this.intent.getStringExtra("url");

                BmobUser bmobUser = BmobUser.getCurrentUser(this);
                if (bmobUser == null) {
                    Intent intent1 = new Intent(this, RegisterActivity.class);
                    startActivity(intent1);
                    selectDetailFavoritesCb.setChecked(false);
                } else {
                    if (selectDetailFavoritesCb.isChecked()) {
                        collectBean = new BmobCollectBean();

                        //存储数据库 url, name, id, price, imgUrl, likeNum,
                        collectBean.setId(idConment);
                        collectBean.setImgUrl(imgUrl);
                        collectBean.setName(name);
                        collectBean.setLikeNum(likeNum);
                        collectBean.setPrice(price);
                        collectBean.setUrl(url);

                        collectBean.setUserName(bmobUser.getUsername());
                        collectBean.setLike(true);
                        //像Bmob数据库存入数据
                        collectBean.save(this, new SaveListener() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(SelectDetailActivity.this, "喜欢成功", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Toast.makeText(SelectDetailActivity.this, "喜欢失败" + s, Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {

                        checkBoxTool.cancleLike(this.intent.getStringExtra("id"));
                    }
                }
                break;
        }
    }

    //连接淘宝网络,接到SelectFragment传来的实体内部类
    private void getWebviewUrl() {
        //if判断  用于解析gift挑选界面的webview跳转  需要拼接id
        final Intent intent = getIntent();
        if (intent.getStringExtra("urlWebId") != null) {

            netTool.getAnalysis(intent.getStringExtra("urlWebId"), new NetListener() {
                @Override
                public void onSuccessed(String response) {
                    Gson gson = new Gson();
                    giftSelectWebBean = gson.fromJson(response, GiftSelectWebBean.class);

                    selectDetailBarTv.setText(giftSelectWebBean.getData().getName());
                    idConment = String.valueOf(giftSelectWebBean.getData().getId());

                    //webView优先使用缓存
                    selectDetailWebview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                    //网页中有js数据时
                    selectDetailWebview.getSettings().setJavaScriptEnabled(true);

                    selectDetailWebview.loadUrl(giftSelectWebBean.getData().getUrl());
                    selectDetailWebview.setWebViewClient(new WebViewClient() {
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

            //else用于select界面和礼物分类小圆圈图片界面的点击进入跳webview  只有接到对应url和name
            this.intent = intent;
        } else {
            final Intent intent1 = getIntent();
            url = intent1.getStringExtra("url");
            //webView优先使用缓存
            selectDetailWebview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            //网页中有js数据时
            selectDetailWebview.getSettings().setJavaScriptEnabled(true);

            selectDetailWebview.loadUrl(url);
            selectDetailWebview.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);

                    return true;
                }
            });
            if (intent1.getStringExtra("name") != null) {
                selectDetailBarTv.setText(intent1.getStringExtra("name"));
            }
            //收到拼接评论的 id
            idConment = intent1.getStringExtra("id");
            this.intent = intent1;


        }


    }


}
