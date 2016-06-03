package com.example.dllo.liwushuo.home;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;
import com.example.dllo.liwushuo.home.adapter.ConmentListviewAdapter;
import com.example.dllo.liwushuo.home.bean.ConmentBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.net.URLValues;
import com.example.dllo.liwushuo.tool.NetTool;
import com.example.dllo.liwushuo.view.NoScrollListview;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/6/2.
 */
public class ConmentActivity extends BaseActivity implements View.OnClickListener {

    private ListView homeConmentDownListview;
    private ImageView homeConmentBackImg;
    private NetTool netTool = new NetTool();
    private ConmentBean conmentBeanDown, conmentBeanUp;
    private ConmentListviewAdapter conmentListviewAdapter;
    private NoScrollListview conmentListviewHead;
    private LinearLayout conmentMoreLlayout;
    private ConmentListviewAdapter conmentListviewHeadAdapter;
    private String contentDownId;
    private LinearLayout homeConmentWriteConmentRlayout;
    private PopupWindow writeConmentPop;
    private EditText writeConmentPopEt;


    @Override
    public void initActivity() {
        //绑布局组件
        setContentView(R.layout.activity_conment);
        homeConmentDownListview = (ListView) findViewById(R.id.home_conment_down_listview);
        homeConmentBackImg = (ImageView) findViewById(R.id.home_conment_back_img);
        homeConmentWriteConmentRlayout = (LinearLayout) findViewById(R.id.home_conment_write_conment_rlayout);
        //绑popupwindow显示的布局组件
        View view = LayoutInflater.from(this).inflate(R.layout.item_home_conment_listview_head, null);
        conmentListviewHead = (NoScrollListview) view.findViewById(R.id.conment_listview_head);
        conmentMoreLlayout = (LinearLayout) view.findViewById(R.id.conment_more_llayout);

        //设置监听事件
        homeConmentBackImg.setOnClickListener(this);
        //下面listview监听时间
        homeConmentDownListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //popupwindow显示
                initWriteConmentPop();
                writeConmentPopEt.setText("回复" + conmentBeanDown.getData().getComments().get(position - 1).getUser().getNickname() + ":");
                writeConmentPop.showAtLocation(homeConmentWriteConmentRlayout, Gravity.BOTTOM, 0, 0);

            }
        });
        //显示更多评论组件监听
        conmentMoreLlayout.setOnClickListener(this);
        homeConmentWriteConmentRlayout.setOnClickListener(this);
        //设置下面评论的adapter
        conmentListviewAdapter = new ConmentListviewAdapter(this);
        homeConmentDownListview.addHeaderView(view);
        homeConmentDownListview.setAdapter(conmentListviewAdapter);

        // //接收url homedetailAcitivity传入的 id
        Intent intent = getIntent();
        contentDownId = intent.getStringExtra("contentDownId");
        String contentDownUrl = URLValues.CONTENT_DOWN_BEFORE + contentDownId + URLValues.CONTENT_DOWN_AFTER;

        netTool.getAnalysis(contentDownUrl, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                conmentBeanDown = gson.fromJson(response, ConmentBean.class);
                conmentListviewAdapter.setConmentBean(conmentBeanDown);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });//设置好了

        //设置上面的更多评论
        conmentListviewHeadAdapter = new ConmentListviewAdapter(this);
        conmentListviewHead.setAdapter(conmentListviewHeadAdapter);
        String headUrl = "http://api.liwushuo.com/v2/posts/" + contentDownId + "/hot_comments?limit=20&offset=0&dataset=top";
        netTool.getAnalysis(headUrl, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                conmentBeanUp = gson.fromJson(response, ConmentBean.class);
                conmentListviewHeadAdapter.setConmentBean(conmentBeanUp);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });//设置好了

        //上面listview监听事件
        conmentListviewHead.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                initWriteConmentPop();
                writeConmentPopEt.setText("回复" + conmentBeanUp.getData().getComments().get(position).getUser().getNickname() + ":");
                writeConmentPop.showAtLocation(homeConmentWriteConmentRlayout, Gravity.BOTTOM, 0, 0);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_conment_back_img:
                finish();
                break;
            //点击跳转更多评论
            case R.id.conment_more_llayout:
                Intent intent = new Intent();
                intent.setClass(this, MoreContentActivity.class);
                intent.putExtra("conmentMoreId", contentDownId);
                startActivity(intent);
                break;
            //下面的点击出popupwindow 写评论
            case R.id.home_conment_write_conment_rlayout:
                initWriteConmentPop();
                writeConmentPop.showAtLocation(homeConmentWriteConmentRlayout, Gravity.BOTTOM, 0, 0);
                break;
            //取消评论
            case R.id.write_conment_pop_cancel_tv:
                writeConmentPop.dismiss();
                break;
            //发送
            case R.id.write_conment_pop_send_tv:

                break;

        }
    }

    //popupwindow方法
    private void initWriteConmentPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_write_conment_pop, null);
        writeConmentPop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        view.findViewById(R.id.write_conment_pop_cancel_tv).setOnClickListener(this);
        view.findViewById(R.id.write_conment_pop_send_tv).setOnClickListener(this);
        writeConmentPopEt = (EditText) view.findViewById(R.id.write_conment_pop_et);

        ColorDrawable colorDrawable = new ColorDrawable(0xFFFFFFFF);
        writeConmentPop.setBackgroundDrawable(colorDrawable);
        writeConmentPop.setAnimationStyle(R.style.anim_home_share_popup);
        writeConmentPop.setOutsideTouchable(true);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.7f;
        getWindow().setAttributes(params);

        writeConmentPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });


    }



}
