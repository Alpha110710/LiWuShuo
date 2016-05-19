package com.example.dllo.liwushuo;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.liwushuo.base.BaseActivity;

/**
 * Created by dllo on 16/5/18.
 */
public class WelcomeActivity extends BaseActivity {

    private TextView countdownTv;
    private CountDownTimer countDownTimer;
    private LinearLayout countDownLayout;

    @Override
    public void initActivity() {
        setContentView(R.layout.activity_welcome);
        countdownTv = findView(R.id.countdown_tv);
        countDownLayout = findView(R.id.countdown_layout);

        countDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished/1000 > 3){
                    countDownLayout.setBackgroundResource(R.mipmap.splash);
                    countdownTv.setVisibility(View.GONE);
                }
                if (millisUntilFinished/1000 < 4 ){
                    countDownLayout.setBackgroundResource(R.mipmap.welcome_two);
                    int a = (int) (millisUntilFinished/1000);
                    countdownTv.setVisibility(View.VISIBLE);
                    countdownTv.setText(a + "s跳转");
                }
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();

    }
}
