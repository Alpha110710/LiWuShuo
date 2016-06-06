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
import android.widget.ListView;
import android.widget.PopupWindow;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;
import com.example.dllo.liwushuo.home.adapter.ConmentListviewAdapter;
import com.example.dllo.liwushuo.home.bean.ConmentBean;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.tool.NetTool;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/6/3.
 */
public class MoreContentActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView homeConmentMoreBackImg;
    private ListView homeConmentMoreListview;
    private NetTool netTool = new NetTool();
    private ConmentBean conmentBean;
    private ConmentListviewAdapter adapter;
    private PopupWindow writeConmentPop;
    private EditText writeConmentPopEt;


    @Override
    public void initActivity() {
        setContentView(R.layout.activity_home_conment_more);
        homeConmentMoreBackImg = (ImageView) findViewById(R.id.home_conment_more_back_img);
        homeConmentMoreListview = (ListView) findViewById(R.id.home_conment_more_listview);

        homeConmentMoreBackImg.setOnClickListener(this);
        homeConmentMoreListview.setOnItemClickListener(this);

        // //接收url ContentActivity 传入的 id
        Intent intent = getIntent();
        String conmentMoreId = intent.getStringExtra("conmentMoreId");

        adapter = new ConmentListviewAdapter(this);
        homeConmentMoreListview.setAdapter(adapter);
        String conmentMoreUrl = "http://api.liwushuo.com/v2/posts/" + conmentMoreId + "/hot_comments?limit=20&offset=0&dataset=all";
        netTool.getAnalysis(conmentMoreUrl, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                conmentBean = gson.fromJson(response, ConmentBean.class);
                adapter.setConmentBean(conmentBean);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_conment_more_back_img:
                finish();
                break;
            case R.id.write_conment_pop_cancel_tv:
                writeConmentPop.dismiss();
                break;
            case R.id.write_conment_pop_send_tv:

                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        initWriteConmentPop();
        writeConmentPopEt.setText("回复" + conmentBean.getData().getComments().get(position).getUser().getNickname() + ":");
        writeConmentPop.showAtLocation(homeConmentMoreListview, Gravity.BOTTOM, 0, 0);


    }
}
