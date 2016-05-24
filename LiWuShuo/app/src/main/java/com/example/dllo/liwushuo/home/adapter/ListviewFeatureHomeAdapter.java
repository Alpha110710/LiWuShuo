package com.example.dllo.liwushuo.home.adapter;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.home.bean.ListviewBean;
import com.example.dllo.liwushuo.tool.App;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

/**
 * Created by dllo on 16/5/24.
 */
public class ListviewFeatureHomeAdapter extends BaseAdapter{
    private ListviewBean listviewBean;

    public void setListviewBean(ListviewBean listviewBean) {
        this.listviewBean = listviewBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listviewBean == null ? 0 : listviewBean.getData().getItems().size();
    }

    @Override
    public Object getItem(int position) {
        return listviewBean == null ? null : listviewBean.getData().getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Myholder myholder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(App.context).inflate(R.layout.item_home_featured_listview, parent, false);
            myholder = new Myholder(convertView);
            convertView.setTag(myholder);
        }else {
            myholder = (Myholder) convertView.getTag();
        }
        //设置数据
        myholder.itemHomeFeatureListviewTitleTv.setText(listviewBean.getData().getItems().get(position).getTitle());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd EE");
        String data = format.format(listviewBean.getData().getItems().get(position).getCreated_at());
        myholder.itemHomeFeatureDataTv.setText(data);
        myholder.itemHomeFeatureListviewLikeTv.setText(String.valueOf(listviewBean.getData().getItems().get(position).getLikes_count()));
        Picasso.with(App.context).load(listviewBean.getData().getItems().get(position).getCover_image_url()).placeholder(R.mipmap.ic_about).fit()
                .into(myholder.itemHomeFeatureListviewImg);

        return convertView;
    }
    class Myholder{

        private final ImageView itemHomeFeatureListviewImg;
        private final TextView itemHomeFeatureListviewLikeTv;
        private final TextView itemHomeFeatureDataTv;
        private final TextView itemHomeFeatureListviewTitleTv;

        public Myholder(View view) {
            itemHomeFeatureListviewImg = (ImageView) view.findViewById(R.id.item_home_feature_listview_img);
            itemHomeFeatureListviewLikeTv = (TextView) view.findViewById(R.id.item_home_feature_listview_like_tv);
            itemHomeFeatureDataTv = (TextView) view.findViewById(R.id.item_home_feature_data_tv);
            itemHomeFeatureListviewTitleTv = (TextView) view.findViewById(R.id.item_home_feature_listview_title_tv);
        }
    }
}
