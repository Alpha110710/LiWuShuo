package com.example.dllo.liwushuo.category.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.category.RaidersTopicBean;
import com.example.dllo.liwushuo.tool.RoundRectTool;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by dllo on 16/5/27.
 */
public class RaidersCategoryRecyclerAdapter extends RecyclerView.Adapter<RaidersCategoryRecyclerAdapter.MyCategoryholder> {
    private RaidersTopicBean raidersTopicBean;
    private Context context;

    public RaidersCategoryRecyclerAdapter(Context context) {
        this.context = context;
        EventBus.getDefault().register(this);


    }

    public void unResgisterBus() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getRaidersTopicBean(RaidersTopicBean raidersTopicBean) {
        this.raidersTopicBean = raidersTopicBean;
        notifyDataSetChanged();
    }

    @Override
    public MyCategoryholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_topic_recyclerview, parent, false);
        MyCategoryholder myCategoryholder = new MyCategoryholder(view);
        return myCategoryholder;
    }

    @Override
    public void onBindViewHolder(MyCategoryholder holder, int position) {
        Picasso.with(context).load(raidersTopicBean.getData().getCollections().get(position).getBanner_webp_url()).placeholder(R.mipmap.ic_about)
                .transform(new RoundRectTool(20)).fit().into(holder.itemCategoryTopicRecyclerImg);
    }

    @Override
    public int getItemCount() {
        return raidersTopicBean == null ? 0 : raidersTopicBean.getData().getCollections().size();
    }

    class MyCategoryholder extends RecyclerView.ViewHolder {

        private final ImageView itemCategoryTopicRecyclerImg;

        public MyCategoryholder(View itemView) {
            super(itemView);
            itemCategoryTopicRecyclerImg = (ImageView) itemView.findViewById(R.id.item_category_topic_recycler_img);
        }
    }
}
