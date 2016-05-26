package com.example.dllo.liwushuo.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.home.bean.RollChannelBean;

/**
 * Created by dllo on 16/5/26.
 */
public class GridviewPopupFeatureAdapter extends BaseAdapter {
    private TextView itemHomePopupSelectTv;
    private TextView itemHomePopupGridviewTv;
    private RollChannelBean rollChannelBean;
    private Context context;
    private LinearLayout itemHomePopupGridviewLlayout;
    private int lastRed;

    public GridviewPopupFeatureAdapter(Context context) {
        this.context = context;
    }

    public void setRollChannelBean(RollChannelBean rollChannelBean) {
        this.rollChannelBean = rollChannelBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        int size = 0;
        if (rollChannelBean != null) {
            if (rollChannelBean.getData().getChannels().size() % 4 == 0) {
                size = rollChannelBean.getData().getChannels().size();
            } else {
                size = rollChannelBean.getData().getChannels().size() / 4 * 4 + 4;
            }
        }

        return rollChannelBean == null ? 0 : size;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_home_popup_gridview, parent, false);
        itemHomePopupSelectTv = (TextView) convertView.findViewById(R.id.item_home_popup_select_tv);
        itemHomePopupGridviewTv = (TextView) convertView.findViewById(R.id.item_home_popup_gridview_tv);


        //判断使4的倍数有背景
        if (position < rollChannelBean.getData().getChannels().size()) {
            itemHomePopupGridviewTv.setText(rollChannelBean.getData().getChannels().get(position).getName());
        }
        if (position >= rollChannelBean.getData().getChannels().size()) {
            itemHomePopupGridviewTv.setText("");
        }
        if (position < rollChannelBean.getData().getChannels().size()) {
            if (rollChannelBean.getData().getChannels().get(position).isEditable()) {
                itemHomePopupSelectTv.setVisibility(View.GONE);
            } else {
                itemHomePopupSelectTv.setVisibility(View.VISIBLE);
                lastRed = position;
            }

        } else {
            itemHomePopupSelectTv.setVisibility(View.GONE);
        }

        return convertView;
    }

    //传入当前位置的方法
    public void setRedLine(int pos) {
        if (pos < rollChannelBean.getData().getChannels().size()) {
            rollChannelBean.getData().getChannels().get(lastRed).setEditable(true);
            rollChannelBean.getData().getChannels().get(pos).setEditable(false);

            notifyDataSetChanged();
        }
    }
}
