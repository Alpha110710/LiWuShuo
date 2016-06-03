package com.example.dllo.liwushuo.tool;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.liwushuo.R;

import static com.example.dllo.liwushuo.R.id.item_home_share_popup_wechat_timeline_tv;

/**
 * Created by dllo on 16/6/3.
 */
public class PopTool implements View.OnClickListener {

    private PopupWindow popupWindow;

    private Activity activity;
    private int id;


    public PopTool(Activity activity, int id) {
        this.id = id;
        this.activity = activity;
        popupWindow = new PopupWindow(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT) {
            //构造方法中重写dismiss方法
            @Override
            public void dismiss() {
                super.dismiss();
                // 重写dismiss方法 改回父容器背景透明度
                WindowManager.LayoutParams params1 = PopTool.this.activity.getWindow().getAttributes();
                params1.alpha = 1;
                PopTool.this.activity.getWindow().setAttributes(params1);

            }

        };


    }

    public void showSharePopupWindow() {
        //设置焦点 实现当点击window外面消失
        popupWindow.setFocusable(true);
        //设置背景 实现当点击window外面消失
//        ColorDrawable drawable = new ColorDrawable(0xb0000000);//半透明
        ColorDrawable drawable = new ColorDrawable(0XFFFFFFFF);//白色
        popupWindow.setBackgroundDrawable(drawable);
        View view = LayoutInflater.from(activity).inflate(R.layout.item_home_detail_share_popup, null);
        view.findViewById(R.id.item_home_share_popup_hyperlink_tv).setOnClickListener(this);
        view.findViewById(R.id.item_home_share_popup_sina_tv).setOnClickListener(this);
        view.findViewById(R.id.item_home_share_popup_tencent_qq_tv).setOnClickListener(this);
        view.findViewById(R.id.item_home_share_popup_tencent_tv).setOnClickListener(this);
        view.findViewById(item_home_share_popup_wechat_timeline_tv).setOnClickListener(this);
        view.findViewById(R.id.item_home_share_popup_wechat_tv).setOnClickListener(this);
        view.findViewById(R.id.item_home_share_popup_cancel_tv).setOnClickListener(this);

        //设置自定义view布局
        popupWindow.setContentView(view);
        //设置动画
        popupWindow.setAnimationStyle(R.style.anim_home_share_popup);
        //在底部显示
        popupWindow.showAtLocation(activity.findViewById(id),
                Gravity.BOTTOM, 0, 0);
        //设置父容器背景透明度
        final WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.alpha = 0.7f;
        activity.getWindow().setAttributes(params);
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//             @Override
//                public void onDismiss() {
//                    WindowManager.LayoutParams params1 = activity.getWindow().getAttributes();
//                    params1.alpha = 1;
//                    activity.getWindow().setAttributes(params1);
//                }
//            });
    }

    public void dismissPopupWindow() {
        popupWindow.dismiss();
    }

    //popupWindow点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_home_share_popup_hyperlink_tv:
                Toast.makeText(activity, "hy", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_home_share_popup_sina_tv:

                Toast.makeText(activity, "item_home_share_popup_sina_tv", Toast.LENGTH_SHORT).show();

                break;
            case R.id.item_home_share_popup_tencent_qq_tv:
                Toast.makeText(activity, "item_home_share_popup_tencent_qq_tv", Toast.LENGTH_SHORT).show();

                break;
            case R.id.item_home_share_popup_tencent_tv:
                Toast.makeText(activity, "item_home_share_popup_tencent_tv", Toast.LENGTH_SHORT).show();

                break;
            case R.id.item_home_share_popup_wechat_timeline_tv:
                Toast.makeText(activity, "item_home_share_popup_wechat_timeline_tv", Toast.LENGTH_SHORT).show();

                break;
            case R.id.item_home_share_popup_wechat_tv:
                Toast.makeText(activity, "item_home_share_popup_wechat_tv", Toast.LENGTH_SHORT).show();

                break;
            case R.id.item_home_share_popup_cancel_tv:
                popupWindow.dismiss();
                break;
        }
    }

}
