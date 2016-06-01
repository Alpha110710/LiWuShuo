package com.example.dllo.liwushuo.category.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dllo on 16/6/1.
 */
public class GiftConditionSelectPopupGridviewAdapter extends BaseAdapter {
    private ArrayList<String> beans;
    private Context context;
    private int pos = -1;

    public void setPos(int pos) {
        this.pos = pos;
    }

    public GiftConditionSelectPopupGridviewAdapter(Context context) {
        this.context = context;
    }

    public void setBeans(ArrayList<String> beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beans == null ? 0 : beans.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        MyConditionSelectPopupGridviewViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gift_condition_popup_gridview, parent, false);
            holder = new MyConditionSelectPopupGridviewViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyConditionSelectPopupGridviewViewHolder) convertView.getTag();
        }
        holder.itemGiftConditionPopupGridviewTv.setText(beans.get(position));
        if (pos == position){
            holder.itemGiftConditionPopupGridviewTv.setSelected(true);
        }else {
            holder.itemGiftConditionPopupGridviewTv.setSelected(false);
        }
        return convertView;
    }

    class MyConditionSelectPopupGridviewViewHolder {
        TextView itemGiftConditionPopupGridviewTv;

        public MyConditionSelectPopupGridviewViewHolder(View itemView) {
            itemGiftConditionPopupGridviewTv = (TextView) itemView.findViewById(R.id.item_gift_condition_popup_gridview_tv);
        }
    }

}
