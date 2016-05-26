package com.example.dllo.day21eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by dllo on 16/5/25.
 */
public class TwoFragment extends Fragment {


    private Button btn2;
    private TextView tv4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sec, container, false);
        btn2 = (Button) view.findViewById(R.id.btn2);
        tv4 = (TextView) view.findViewById(R.id.tv4);
        //注册
        EventBus.getDefault().register(this);
//        Intent intent = getActivity().getIntent();
//        String a = intent.getStringExtra("a");
//        btn2.setText(a);

        String a = getArguments().getString("a");

        tv4.setText(a);
        return view;

    }

    @Subscribe
    public void receive(TestEvent testEvent) {
        String a = testEvent.text;
        btn2.setText(a);
        tv4.setText(a);
        Log.d("TwoFragment---default", Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void testThread(TestEvent testEvent) {
        Log.d("TwoFragment------back---->", Thread.currentThread().getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
