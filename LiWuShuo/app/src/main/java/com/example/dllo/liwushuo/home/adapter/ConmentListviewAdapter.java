package com.example.dllo.liwushuo.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;

/**
 * Created by dllo on 16/6/2.
 */
public class ConmentListviewAdapter extends BaseAdapter {
    private Context context;

    public ConmentListviewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
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
        return null;
    }

    class MyConmentListviewViewHolder{

        private final TextView itemConmentDownListviewContentTv;
        private final ImageView itemConmentDownListviewHeadImg;
        private final TextView itemConmentDownListviewNameTv;
        private final CheckBox itemConmentDownListviewPraiseCb;
        private final TextView itemConmentDownListviewviewTimeTv;

        public MyConmentListviewViewHolder(View itemView) {
            itemConmentDownListviewContentTv = (TextView) itemView.findViewById(R.id.item_conment_down_listview_content_tv);
            itemConmentDownListviewHeadImg = (ImageView) itemView.findViewById(R.id.item_conment_down_listview_head_img);
            itemConmentDownListviewNameTv = (TextView) itemView.findViewById(R.id.item_conment_down_listview_name_tv);
            itemConmentDownListviewPraiseCb = (CheckBox) itemView.findViewById(R.id.item_conment_down_listview_praise_cb);
            itemConmentDownListviewviewTimeTv = (TextView) itemView.findViewById(R.id.item_conment_down_listview_time_tv);
        }
    }
}
