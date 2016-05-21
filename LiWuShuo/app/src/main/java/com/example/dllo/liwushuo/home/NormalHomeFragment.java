package com.example.dllo.liwushuo.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.base.BaseFragment;

/**
 * Created by dllo on 16/5/21.
 */
public class NormalHomeFragment extends BaseFragment {
        private TextView textView;

    public static Fragment createFragment(String data){
        Fragment fragment = new NormalHomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data", data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_home_normal;
    }

    @Override
    public void initView(View view) {
        textView = findView(R.id.tv);

    }

    @Override
    public void initData() {
        Bundle bundle = getArguments();
        String str = bundle.getString("data");
        textView.setText(str);

    }
}
