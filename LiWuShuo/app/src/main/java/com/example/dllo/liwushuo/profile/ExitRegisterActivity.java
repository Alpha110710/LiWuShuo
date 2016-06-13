package com.example.dllo.liwushuo.profile;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;
import com.example.dllo.liwushuo.register.RegisterActivity;

import cn.bmob.v3.BmobUser;

/**
 * Created by dllo on 16/6/12.
 */
public class ExitRegisterActivity extends BaseActivity implements View.OnClickListener {

    private TextView exitRegisterExitTv;
    private ImageView exitRegisterBackBarImg;



    @Override
    public void initActivity() {
        setContentView(R.layout.activity_exit_register);
        exitRegisterExitTv = (TextView) findViewById(R.id.exit_register_exit_tv);
        exitRegisterBackBarImg = (ImageView) findViewById(R.id.exit_register_back_bar_img);


        exitRegisterExitTv.setOnClickListener(this);
        exitRegisterBackBarImg.setOnClickListener(this);

//        bmobUser = BmobUser.getCurrentUser(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exit_register_exit_tv:
                BmobUser.logOut(this);
                finish();

                break;
            case R.id.exit_register_back_bar_img:
                finish();
                break;

        }
    }



}
