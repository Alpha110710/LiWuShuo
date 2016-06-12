package com.example.dllo.liwushuo.register;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseActivity;
import com.example.dllo.liwushuo.tool.App;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/5/28.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private ImageView registerBackImg, registerPhoneImg, registerPasswordImg, registerMessageImg, viewBregisterSinaImgyId, registerWechatImg, registerQqImg;
    private EditText registerTeltnumEt, registerPasswordEt;
    private Button registerGetcode;
    private TextView registerButton;
    private CheckBox registerSelectCb;
    private BmobUser bmobUser;
    private boolean isLogin = true;


    @Override
    public void initActivity() {
        setContentView(R.layout.register);
        registerBackImg = (ImageView) findViewById(R.id.register_back_img);
        registerPhoneImg = (ImageView) findViewById(R.id.register_phone_img);
        registerTeltnumEt = (EditText) findViewById(R.id.register_teltnum_et);
        registerPasswordEt = (EditText) findViewById(R.id.register_password_et);
        registerPasswordImg = (ImageView) findViewById(R.id.register_password_img);
        registerMessageImg = (ImageView) findViewById(R.id.register_message_img);
        registerGetcode = (Button) findViewById(R.id.register_getcode);
        registerButton = (TextView) findViewById(R.id.register_button);
        registerSelectCb = (CheckBox) findViewById(R.id.register_select_cb);
        viewBregisterSinaImgyId = (ImageView) findViewById(R.id.register_sina_img);
        registerWechatImg = (ImageView) findViewById(R.id.register_wechat_img);
        registerQqImg = (ImageView) findViewById(R.id.register_qq_img);

        registerButton.setOnClickListener(this);
        registerSelectCb.setOnClickListener(this);
        registerBackImg.setOnClickListener(this);

        bmobUser = new BmobUser();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_button:
                bmobUser = new BmobUser();
                bmobUser.setUsername(registerTeltnumEt.getText().toString());
                bmobUser.setPassword(registerPasswordEt.getText().toString());
                Log.d("RegisterActivity", registerTeltnumEt.getText().toString());
                if (isLogin) {
                    bmobUser.login(App.context, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(RegisterActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {

                    bmobUser.signUp(App.context, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(int i, String s) {

                            Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            case R.id.register_select_cb:
                isLogin = !isLogin;
                if (isLogin) {
                    registerButton.setText("登录");
                    registerSelectCb.setText("注册新用户");
                } else {
                    registerButton.setText("注册");
                    registerSelectCb.setText("登录");

                }

                break;
            case R.id.register_back_img:
                finish();
                break;
        }
    }
}
