package com.example.dllo.liwushuo.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;

import com.example.dllo.liwushuo.MainActivity;
import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;

/**
 * Created by dllo on 16/6/14.
 */
public class BoyAndGirlActivity extends BaseActivity {

    private TextView boyAndGirlTv;

    @Override
    public void initActivity() {
        setContentView(R.layout.acticity_boy_and_girl);
        boyAndGirlTv = (TextView) findViewById(R.id.boy_and_girl_tv);

        boyAndGirlTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoyAndGirlActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("welcome", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isFirst", false).commit();

    }


}
