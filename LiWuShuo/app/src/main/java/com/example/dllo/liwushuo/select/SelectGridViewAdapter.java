package com.example.dllo.liwushuo.select;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/20.
 */
public class SelectGridViewAdapter extends BaseAdapter {
    private ArrayList<SelectBean> selectBeans;
    private Context context;

    public SelectGridViewAdapter(Context context) {
        this.context = context;
    }

    public void setSelectBeans(ArrayList<SelectBean> selectBeans) {
        this.selectBeans = selectBeans;
    }

    @Override
    public int getCount() {
        return selectBeans != null && selectBeans.size() > 0 ? selectBeans.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return selectBeans != null && selectBeans.size() > 0 ? selectBeans.get(position) : null;
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

        viewHolder.itemSelectGridviewNameTv.setText(selectBeans.get(position).getName());
        viewHolder.itemSelectGridviewSupportTv.setText(selectBeans.get(position).getFavorites_count());
        viewHolder.itemSelectGridviewPriceTv.setText(selectBeans.get(position).getPrice());

        //TODO:解析图片没有适配好
//         String url = selectBeans.get(position).getCover_webp_url();

        viewHolder.itemSelectGridviewImg.setImageResource(R.mipmap.ic_about);

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
