package com.example.dllo.liwushuo.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.home.HomeRecyclerVIewOnClickListener;
import com.example.dllo.liwushuo.home.bean.RecyclerviewBean;
import com.example.dllo.liwushuo.base.App;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/5/24.
 */
public class RecyclerviewFeatureHomeAdapter extends RecyclerView.Adapter<RecyclerviewFeatureHomeAdapter.MyViewholder> {
    private RecyclerviewBean recyclerviewBean;
    private HomeRecyclerVIewOnClickListener homeRecyclerVIewOnClickListener;

    public void setHomeRecyclerVIewOnClickListener(HomeRecyclerVIewOnClickListener homeRecyclerVIewOnClickListener) {
        this.homeRecyclerVIewOnClickListener = homeRecyclerVIewOnClickListener;
    }

    public void setRecyclerviewBean(RecyclerviewBean recyclerviewBean) {
        this.recyclerviewBean = recyclerviewBean;
        notifyDataSetChanged();
    }

    public RecyclerviewBean getRecyclerviewBean() {
        return recyclerviewBean;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(App.context).inflate(R.layout.item_home_featured_recyclerview, parent, false);
        MyViewholder myViewholder = new MyViewholder(view);
        return myViewholder;
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, final int position) {
        if (recyclerviewBean != null){
            Picasso.with(App.context).load(recyclerviewBean.getData().getSecondary_banners().get(position)
                .getImage_url()).placeholder(R.mipmap.ig_logo_text).into(holder.itemHomeFeatureRecyclerviewImg);

        }else {
            holder.itemHomeFeatureRecyclerviewImg.setImageResource(R.mipmap.ic_about);
        }
        holder.itemHomeFeatureRecyclerviewLlyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeRecyclerVIewOnClickListener.onClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {

        return recyclerviewBean == null ? 0 :recyclerviewBean.getData().getSecondary_banners().size();
    }

    class MyViewholder extends RecyclerView.ViewHolder{

        private final ImageView itemHomeFeatureRecyclerviewImg;
        private final LinearLayout itemHomeFeatureRecyclerviewLlyout;

        public MyViewholder(View itemView) {
            super(itemView);
            itemHomeFeatureRecyclerviewImg = (ImageView) itemView.findViewById(R.id.item_home_feature_recyclerview_img);
            itemHomeFeatureRecyclerviewLlyout = (LinearLayout) itemView.findViewById(R.id.item_home_feature_recyclerview_llyout);
        }
    }
}
