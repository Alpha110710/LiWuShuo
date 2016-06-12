package com.example.dllo.liwushuo.profile;

import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;
import com.example.dllo.liwushuo.register.BmobCollectBean;
import com.example.dllo.liwushuo.tool.CollectCheckBoxTool;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by dllo on 16/5/21.
 */
public class GiftProfileFragment extends BaseFragment {

    private GridView profileGiftGridview;
    private ProfileGiftGridviewAdapter giftGridviewAdapter;
    private CollectCheckBoxTool checkBoxTool;


    @Override
    public int setLayout() {
        return R.layout.fragment_profile_gift;
    }

    @Override
    public void initView(View view) {
        profileGiftGridview = (GridView) view.findViewById(R.id.profile_gift_gridview);

    }

    @Override
    public void initData() {
        giftGridviewAdapter = new ProfileGiftGridviewAdapter(context);
        checkBoxTool = new CollectCheckBoxTool(context);

        profileGiftGridview.setAdapter(giftGridviewAdapter);
        BmobQuery<BmobCollectBean> query = new BmobQuery<BmobCollectBean>();

        //TODO:应该写在resume方法里
        query.addWhereEqualTo("isLike", true);
//        query.setLimit(100);
        query.findObjects(context, new FindListener<BmobCollectBean>() {
            @Override
            public void onSuccess(List<BmobCollectBean> list) {

                ArrayList<BmobCollectBean> bmobCollectBeen = new ArrayList<BmobCollectBean>();
                for (BmobCollectBean bmobCollectBean : list) {
                    bmobCollectBeen.add(bmobCollectBean);
                }

                giftGridviewAdapter.setCollectBeans(bmobCollectBeen);
            }

            @Override
            public void onError(int i, String s) {

            }
        });






    }
}
