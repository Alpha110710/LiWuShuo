package com.example.dllo.liwushuo.profile;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.register.BmobCollectBean;
import com.example.dllo.liwushuo.register.RegisterActivity;
import com.example.dllo.liwushuo.select.SelectDetailActivity;
import com.example.dllo.liwushuo.tool.CollectCheckBoxTool;
import com.example.dllo.liwushuo.view.NoScorllGridview;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by dllo on 16/5/21.
 */
public class GiftProfileFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private GridView profileGiftGridview;
    private ProfileGiftGridviewAdapter giftGridviewAdapter;
    private RelativeLayout profileLikeRlayout;
    private ArrayList<BmobCollectBean> bmobCollectBeen;



    @Override
    public int setLayout() {
        return R.layout.fragment_profile_gift;
    }

    @Override
    public void initView(View view) {
        profileGiftGridview = (GridView) view.findViewById(R.id.profile_gift_gridview);
        profileLikeRlayout = (RelativeLayout) view.findViewById(R.id.profile_like_rlayout);

    }

    @Override
    public void initData() {
        giftGridviewAdapter = new ProfileGiftGridviewAdapter(context);

        profileGiftGridview.setOnItemClickListener(this);
        bmobCollectBeen = new ArrayList<BmobCollectBean>();
        //设置背景可见
        profileLikeRlayout.setVisibility(View.VISIBLE);
        queryBmob();



    }


    //查询网络数据库的方法
    private void queryBmob() {
        //查询是否登录的对象
        final BmobUser bmobUser = BmobUser.getCurrentUser(context);
        //判断是否登录
        if (bmobUser != null) {
            profileGiftGridview.setAdapter(giftGridviewAdapter);
            //查询数据库的对象
            BmobQuery<BmobCollectBean> query = new BmobQuery<BmobCollectBean>();

            //查询条件 即为查询所有
            query.addWhereEqualTo("isLike", true);
            query.addWhereEqualTo("userName", bmobUser.getUsername());
            //每次返回50条数据
            query.setLimit(50);
            query.findObjects(context, new FindListener<BmobCollectBean>() {
                @Override
                public void onSuccess(List<BmobCollectBean> list) {

                    for (BmobCollectBean bmobCollectBean : list) {
                        bmobCollectBeen.add(bmobCollectBean);
                    }
                    //设置背景不可见
                    if (bmobCollectBeen != null && bmobUser != null) {
                        profileLikeRlayout.setVisibility(View.GONE);
                    }

                    giftGridviewAdapter.setCollectBeans(bmobCollectBeen);
                }

                @Override
                public void onError(int i, String s) {

                }
            });
        }
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(context, SelectDetailActivity.class);
        intent.putExtra("url", bmobCollectBeen.get(position).getUrl());
        intent.putExtra("name", bmobCollectBeen.get(position).getName());
        intent.putExtra("id", String.valueOf(bmobCollectBeen.get(position).getId()));
        intent.putExtra("price", bmobCollectBeen.get(position).getPrice());
        intent.putExtra("imgUrl", bmobCollectBeen.get(position).getImgUrl());
        intent.putExtra("likeNum", String.valueOf(bmobCollectBeen.get(position).getLikeNum()));

        startActivity(intent);


    }
}
