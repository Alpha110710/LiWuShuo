package com.example.dllo.liwushuo.profile;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.register.BmobCollectBean;
import com.example.dllo.liwushuo.register.BmobRaidersBean;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by dllo on 16/5/21.
 */
public class RaidersProfileFragment extends BaseFragment {

    private ListView profileRaidersListview;
    private ProfileRaidersListviewAdapter raidersListviewAdapter;


    @Override
    public int setLayout() {
        return R.layout.fragment_profile_raiders;
    }

    @Override
    public void initView(View view) {
        profileRaidersListview = (ListView) view.findViewById(R.id.profile_raiders_listview);

    }

    @Override
    public void initData() {
        raidersListviewAdapter = new ProfileRaidersListviewAdapter(context);
        queryBmob();

    }

    //查询网络数据库的方法
    private void queryBmob() {
        //查询是否登录的对象
        final BmobUser bmobUser = BmobUser.getCurrentUser(context);
        //判断是否登录
        if (bmobUser != null) {
            profileRaidersListview.setAdapter(raidersListviewAdapter);

            //查询数据库的对象
            BmobQuery<BmobRaidersBean> query = new BmobQuery<BmobRaidersBean>();

            //查询条件 即为查询所有
            query.addWhereEqualTo("raiders", true);
            query.addWhereEqualTo("userName", bmobUser.getUsername());
            //每次返回50条数据
            query.setLimit(50);
            query.findObjects(context, new FindListener<BmobRaidersBean>() {
                @Override
                public void onSuccess(List<BmobRaidersBean> list) {

                    ArrayList<BmobRaidersBean> bmobRaidersBeen = new ArrayList<BmobRaidersBean>();
                    for (BmobRaidersBean bmobRaidersBean : list) {
                        bmobRaidersBeen.add(bmobRaidersBean);
                    }
//                    //设置背景不可见
//                    if (bmobCollectBeen != null && bmobUser != null) {
//                        profileLikeRlayout.setVisibility(View.GONE);
//                    }
//
                    raidersListviewAdapter.setBmobRaidersBeen(bmobRaidersBeen);
                }

                @Override
                public void onError(int i, String s) {

                }
            });
        }
    }
}
