package com.example.dllo.liwushuo.category.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.category.RecyclerviewOnClickListener;
import com.example.dllo.liwushuo.category.bean.RaidersTopicBean;
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
    private RecyclerviewOnClickListener recyclerviewOnClickListener;

    public void setRecyclerviewOnClickListener(RecyclerviewOnClickListener recyclerviewOnClickListener) {
        this.recyclerviewOnClickListener = recyclerviewOnClickListener;
    }

    public RaidersCategoryRecyclerAdapter(Context context) {
        this.context = context;
        EventBus.getDefault().register(this);

    }

    public RaidersTopicBean getRaidersTopicBean() {
        return raidersTopicBean;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_raiders_topic_recyclerview, parent, false);
        MyCategoryholder myCategoryholder = new MyCategoryholder(view);
        return myCategoryholder;
    }

    @Override
    public void onBindViewHolder(final MyCategoryholder holder, int position) {
        Picasso.with(context).load(raidersTopicBean.getData().getCollections().get(position).getBanner_webp_url()).placeholder(R.mipmap.ic_about)
                .transform(new RoundRectTool(20)).fit().into(holder.itemCategoryTopicRecyclerImg);

        if (recyclerviewOnClickListener != null){
            holder.itemCategoryTopicRecyclerImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerviewOnClickListener.onClick(holder.getLayoutPosition());
                }
            });
        }
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
