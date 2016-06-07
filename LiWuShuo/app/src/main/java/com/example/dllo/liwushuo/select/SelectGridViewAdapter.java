package com.example.dllo.liwushuo.select;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.tool.RoundRectTool;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/20.
 */
public class SelectGridViewAdapter extends BaseAdapter {
    private SelectBean selectBean;
    private Context context;

    public SelectGridViewAdapter(Context context) {
        this.context = context;
        EventBus.getDefault().register(this);
    }

    public void setSelectBean(SelectBean selectBean) {
        this.selectBean = selectBean;
        notifyDataSetChanged();
    }
    public void addItems(List<SelectBean.DataBean.ItemsBean> itemsBeen){
        selectBean.getData().getItems().addAll(itemsBeen);
        notifyDataSetChanged();
    }

    public void unRegisterBus(){
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getAnlysisSelect(SelectBean selectBean){
        this.selectBean = selectBean;
        notifyDataSetChanged();
    }

    public SelectBean getSelectBean() {
        return selectBean;
    }

    @Override
    public int getCount() {
        return selectBean == null ? 0 : selectBean.getData().getItems().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SelectGridViewViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_select_gridview, parent, false);
            viewHolder = new SelectGridViewViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SelectGridViewViewHolder) convertView.getTag();
        }

        viewHolder.itemSelectGridviewPriceTv.setText(selectBean.getData().getItems().get(position).getData().getPrice());
        viewHolder.itemSelectGridviewSupportTv.setText(String.valueOf(selectBean.getData().getItems().get(position).getData().getFavorites_count()));
        viewHolder.itemSelectGridviewNameTv.setText(selectBean.getData().getItems().get(position).getData().getName());
        Picasso.with(context).load(selectBean.getData().getItems().get(position).getData().getCover_image_url()).placeholder(R.mipmap.ig_logo_text)
        .centerCrop().fit().into(viewHolder.itemSelectGridviewImg);


        return convertView;
    }

    private class SelectGridViewViewHolder {

        private final ImageView itemSelectGridviewImg;
        private final TextView itemSelectGridviewSupportTv, itemSelectGridviewPriceTv, itemSelectGridviewNameTv;

        public SelectGridViewViewHolder(View view) {
            itemSelectGridviewImg = (ImageView) view.findViewById(R.id.item_select_gridview_img);
            itemSelectGridviewSupportTv = (TextView) view.findViewById(R.id.item_select_gridview_support_tv);
            itemSelectGridviewPriceTv = (TextView) view.findViewById(R.id.item_select_gridview_price_tv);
            itemSelectGridviewNameTv = (TextView) view.findViewById(R.id.item_select_gridview_name_tv);

        }
    }
}
