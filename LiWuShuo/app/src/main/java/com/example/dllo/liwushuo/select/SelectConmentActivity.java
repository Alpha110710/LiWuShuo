package com.example.dllo.liwushuo.select;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;
import com.example.dllo.liwushuo.net.NetListener;
import com.example.dllo.liwushuo.tool.NetTool;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/6/6.
 */
public class SelectConmentActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView selectConmentBackImg;
    private ListView selectConmentListview;
    private TextView selectConmentSendConmentTv;
    private EditText selectConmentWriteConmentEt;
    private NetTool netTool = new NetTool();
    private SelectConmentListviewAdapter selectConmentListviewAdapter;
    private SelectConmentBean selectConmentBean;

    @Override
    public void initActivity() {
        setContentView(R.layout.activity_select_conment);
        selectConmentBackImg = (ImageView) findViewById(R.id.select_conment_back_img);
        selectConmentListview = (ListView) findViewById(R.id.select_conment_listview);
        selectConmentSendConmentTv = (TextView) findViewById(R.id.select_conment_send_conment_tv);
        selectConmentWriteConmentEt = (EditText) findViewById(R.id.select_conment_write_conment_et);

        selectConmentBackImg.setOnClickListener(this);
        selectConmentSendConmentTv.setOnClickListener(this);
        selectConmentWriteConmentEt.setOnClickListener(this);
        selectConmentListview.setOnItemClickListener(this);
        selectConmentListviewAdapter = new SelectConmentListviewAdapter(this);
        selectConmentListview.setAdapter(selectConmentListviewAdapter);

        //接收SelectDetailActivity传来的评论id
        Intent intent = getIntent();
        String conmentId = intent.getStringExtra("conmentId");
        String conmentUrl = "http://api.liwushuo.com/v2/items/" + conmentId + "/comments?limit=20&offset=0";
        netTool.getAnalysis(conmentUrl, new NetListener() {
            @Override
            public void onSuccessed(String response) {
                Gson gson = new Gson();
                selectConmentBean = gson.fromJson(response, SelectConmentBean.class);
                selectConmentListviewAdapter.setSelectConmentBean(selectConmentBean);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_conment_back_img:
                finish();
                break;
            case R.id.select_conment_send_conment_tv:


                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectConmentWriteConmentEt.setText("回复" + selectConmentBean.getData().getComments().get(position).getUser().getNickname() + ": ");
        selectConmentWriteConmentEt.setSelection(selectConmentWriteConmentEt.getText().length());
    }
}
