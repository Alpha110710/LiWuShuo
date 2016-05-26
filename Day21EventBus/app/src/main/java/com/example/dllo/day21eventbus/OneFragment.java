package com.example.dllo.day21eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by dllo on 16/5/25.
 */
public class OneFragment extends Fragment implements View.OnClickListener {


    private Button btn1;
    private EditText tv1;
    private EventBus eventBus;
    private TwoFragment twoFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        btn1 = (Button) view.findViewById(R.id.btn1);
        tv1 = (EditText) view.findViewById(R.id.tv1);
        btn1.setOnClickListener(this);
        //获得eventBus对象
        eventBus = EventBus.getDefault();

       twoFragment =  MainActivity.getTwoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("a", "aaaa");
        twoFragment.setArguments(bundle);

        return view;
    }

    @Override
    public void onClick(View v) {
        String a = tv1.getText().toString();
        TestEvent event = new TestEvent(a);
        //把事件发布出去
//        eventBus.post(event);

//        Intent intent = new Intent();
//        intent.putExtra("a", a);



    }
}
