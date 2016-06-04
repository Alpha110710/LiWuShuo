package com.example.dllo.liwushuo.category.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.category.bean.RaidersTopicBean;
import com.example.dllo.liwushuo.tool.RoundRectTool;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/6/4.
 */
public class RaidersSeeAllLiseviewAdapter extends BaseAdapter {
    private RaidersTopicBean raidersTopicBean;
    private Context context;

    public RaidersSeeAllLiseviewAdapter(Context context) {
        this.context = context;
    }

    public void setRaidersTopicBean(RaidersTopicBean raidersTopicBean) {
        this.raidersTopicBean = raidersTopicBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return raidersTopicBean == null ? 0 : raidersTopicBean.getData().getCollections().size();
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
        MyRaidersSeeAllLiseviewViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_raiders_see_all_listview, parent, false);
            holder = new MyRaidersSeeAllLiseviewViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MyRaidersSeeAllLiseviewViewHolder) convertView.getTag();
        }
        holder.itemRaidersListviewSeeAllTitleTv.setText(raidersTopicBean.getData().getCollections().get(position).getTitle());
        holder.itemRaidersListviewSeeAllTitleDetailTv.setText(raidersTopicBean.getData().getCollections().get(position).getSubtitle());
        Picasso.with(context).load(raidersTopicBean.getData().getCollections().get(position).getCover_webp_url()).fit().placeholder(R.mipmap.ic_about).transform(new RoundRectTool(20))
                .into(holder.itemRaidersListviewSeeAllImg);
        return convertView;
    }

    class MyRaidersSeeAllLiseviewViewHolder {

        private final ImageView itemRaidersListviewSeeAllImg;
        private final TextView itemRaidersListviewSeeAllTitleDetailTv;
        private final TextView itemRaidersListviewSeeAllTitleTv;

        public MyRaidersSeeAllLiseviewViewHolder(View itemView) {
            itemRaidersListviewSeeAllImg = (ImageView) itemView.findViewById(R.id.item_raiders_listview_see_all_img);
            itemRaidersListviewSeeAllTitleDetailTv = (TextView) itemView.findViewById(R.id.item_raiders_listview_see_all_title_detail_tv);
            itemRaidersListviewSeeAllTitleTv = (TextView) itemView.findViewById(R.id.item_raiders_listview_see_all_title_tv);

        }
    }
}
