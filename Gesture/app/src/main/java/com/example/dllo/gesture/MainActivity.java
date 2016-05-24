package com.example.dllo.gesture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*
  1.motionevent
  2.ontouchlistener(ontouch)
  3.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn_finish);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
