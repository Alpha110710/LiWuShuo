package com.example.dllo.liwushuo.tool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.dllo.liwushuo.register.BmobCollectBean;
import com.example.dllo.liwushuo.select.SelectDetailActivity;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by dllo on 16/6/12.
 */
public class CollectCheckBoxTool {
    public BmobQuery<BmobCollectBean> query = new BmobQuery<>();
    private Context context;


    public CollectCheckBoxTool(Context context) {
        this.context = context;
    }

    public void queryIsLike(Intent intent, final CheckBox checkBox) {
        query.addWhereEqualTo("id", intent.getStringExtra("id"));
        query.findObjects(context, new FindListener<BmobCollectBean>() {
            @Override
            public void onSuccess(List<BmobCollectBean> list) {
                if (list.size() == 0) {
                    checkBox.setChecked(false);
                } else {
                    checkBox.setChecked(true);
                }
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(context, "查询失败" + s, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void cancleLike(Intent intent) {
        Toast.makeText(context, "取消喜欢成功", Toast.LENGTH_SHORT).show();

        BmobQuery<BmobCollectBean> query = new BmobQuery<>();
        //条件查询
        query.addWhereEqualTo("id", intent.getStringExtra("id"));
        //查询到对应id的数据库内容
        query.findObjects(context, new FindListener<BmobCollectBean>() {
            @Override
            public void onSuccess(List<BmobCollectBean> list) {

                //遍历这个List
                for (BmobCollectBean bmobCollectBean : list) {
                    //进行删除
                    bmobCollectBean.delete(context, new DeleteListener() {
                        @Override
                        public void onSuccess() {
                            //删除成功
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
}
