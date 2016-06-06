package com.example.dllo.liwushuo.home;

import android.view.View;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;

/**
 * Created by dllo on 16/6/6.
 */
public class HomeCalendarActivity extends BaseActivity implements View.OnClickListener {
    @Override
    public void initActivity() {
        setContentView(R.layout.activity_home_calendar);
        findViewById(R.id.home_calendar_back_img).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_calendar_back_img:
                finish();
                break;
        }
    }
}
