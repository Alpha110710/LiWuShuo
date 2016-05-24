package com.example.dllo.gesture;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by dllo on 16/5/24.
 */
public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        btn3 = (Button) findViewById(R.id.btn_finish_third);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
