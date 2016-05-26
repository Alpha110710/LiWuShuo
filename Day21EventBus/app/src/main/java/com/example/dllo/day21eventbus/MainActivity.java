package com.example.dllo.day21eventbus;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private FrameLayout fl1;
    private FrameLayout fl2;
    private OneFragment oneFragment;
    private static TwoFragment twoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fl1 = (FrameLayout) findViewById(R.id.fl1);
        fl2 = (FrameLayout) findViewById(R.id.fl2);

         oneFragment = new OneFragment();
        twoFragment = new TwoFragment();



        getSupportFragmentManager().beginTransaction().replace(R.id.fl1, oneFragment).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl2, twoFragment).commit();






    }
    public static TwoFragment getTwoFragment() {
        return twoFragment;
    }

    public OneFragment getOneFragment() {
        return oneFragment;
    }


}
