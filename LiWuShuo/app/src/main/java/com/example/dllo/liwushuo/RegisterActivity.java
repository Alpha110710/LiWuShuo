package com.example.dllo.liwushuo;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.liwushuo.base.BaseActivity;

/**
 * Created by dllo on 16/5/28.
 */
public class RegisterActivity extends BaseActivity {

    private ImageView viewById;
    private ImageView viewById1;
    private EditText viewById2;
    private ImageView viewById3;
    private ImageView viewById4;
    private Button viewById5;
    private TextView viewById6;
    private CheckBox viewById7;
    private ImageView viewById8;
    private ImageView viewById9;
    private ImageView viewById10;

    @Override
    public void initActivity() {
        setContentView(R.layout.register);
        viewById = (ImageView) findViewById(R.id.register_back_img);
        viewById1 = (ImageView) findViewById(R.id.register_phone_img);
        viewById2 = (EditText) findViewById(R.id.register_teltnum_et);
        viewById3 = (ImageView) findViewById(R.id.register_password_img);
        viewById4 = (ImageView) findViewById(R.id.register_message_img);
        viewById5 = (Button) findViewById(R.id.register_getcode);
        viewById6 = (TextView) findViewById(R.id.register_button);
        viewById7 = (CheckBox) findViewById(R.id.register_select_cb);
        viewById8 = (ImageView) findViewById(R.id.register_sina_img);
        viewById9 = (ImageView) findViewById(R.id.register_wechat_img);
        viewById10 = (ImageView) findViewById(R.id.register_qq_img);
    }
}
