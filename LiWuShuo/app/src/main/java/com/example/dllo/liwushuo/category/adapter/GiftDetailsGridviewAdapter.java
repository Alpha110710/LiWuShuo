package com.example.dllo.liwushuo.category.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.category.bean.GiftDetailBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/5/31.
 */
public class GiftDetailsGridviewAdapter extends BaseAdapter {
    private GiftDetailBean giftDetailBean;
    private Context context;

    public GiftDetailsGridviewAdapter(Context context) {
        this.context = context;
    }

    public void setGiftDetailBean(GiftDetailBean giftDetailBean) {
        this.giftDetailBean = giftDetailBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return giftDetailBean == null ? 0 : giftDetailBean.getData().getItems().size();
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
        SelectGridViewViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_select_gridview, parent, false);
            viewHolder = new SelectGridViewViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SelectGridViewViewHolder) convertView.getTag();
        }

        viewHolder.itemSelectGridviewPriceTv.setText(giftDetailBean.getData().getItems().get(position).getPrice());
        viewHolder.itemSelectGridviewSupportTv.setText(String.valueOf(giftDetailBean.getData().getItems().get(position).getFavorites_count()));
        viewHolder.itemSelectGridviewNameTv.setText(giftDetailBean.getData().getItems().get(position).getName());
        Picasso.with(context).load(giftDetailBean.getData().getItems().get(position).getCover_image_url()).placeholder(R.mipmap.ic_about)
                .centerCrop().fit().into(viewHolder.itemSelectGridviewImg);


        return convertView;
    }

    //复用Select页面布局
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
