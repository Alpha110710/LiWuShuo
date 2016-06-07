package com.example.dllo.liwushuo.category.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.category.bean.RaidersUpBean;
import com.example.dllo.liwushuo.home.bean.NormalListviewBean;
import com.example.dllo.liwushuo.tool.RoundRectTool;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/5/25.
 */
public class ListviewRaidersUpAdapter extends BaseAdapter {
    private Context context;
    private RaidersUpBean raidersUpBean;

    public void setRaidersUpBean(RaidersUpBean raidersUpBean) {
        this.raidersUpBean = raidersUpBean;
        notifyDataSetChanged();
    }
    public void  addBean(List<RaidersUpBean.DataBean.PostsBean> postsBeen){
        raidersUpBean.getData().getPosts().addAll(postsBeen);
        notifyDataSetChanged();

    }

    public ListviewRaidersUpAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {

        return raidersUpBean == null ? 0 : raidersUpBean.getData().getPosts().size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewholder myViewholder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_normal_listview, parent, false);
            myViewholder = new MyViewholder(convertView);
            convertView.setTag(myViewholder);
        } else {
            myViewholder = (MyViewholder) convertView.getTag();
        }

        Picasso.with(context).load(raidersUpBean.getData().getPosts().get(position).getCover_image_url()).centerCrop()
                .transform(new RoundRectTool(20)).fit().into(myViewholder.itemHomeNormalListviewImg);
        myViewholder.itemHomeNormalListviewLikeCb.setText(String.valueOf(raidersUpBean.getData().getPosts().get(position).getLikes_count()));
        myViewholder.itemHomeNormalListviewTitleTv.setText(raidersUpBean.getData().getPosts().get(position).getTitle());
        myViewholder.itemHomeNormalListviewLikeCb.setChecked(raidersUpBean.getData().getPosts().get(position).isLiked());

        //checkbox加监听, 解决复用问题
        myViewholder.itemHomeNormalListviewLikeCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                raidersUpBean.getData().getPosts().get(position).setLiked(checkBox.isChecked());
               //TODO:checkbox继续设置
            }
        });

        return convertView;
    }

    class MyViewholder {

        private final ImageView itemHomeNormalListviewImg;
        private final CheckBox itemHomeNormalListviewLikeCb;
        private final TextView itemHomeNormalListviewTitleTv;

        public MyViewholder(View itemView) {
            itemHomeNormalListviewImg = (ImageView) itemView.findViewById(R.id.item_home_normal_listview_img);
            itemHomeNormalListviewLikeCb = (CheckBox) itemView.findViewById(R.id.item_home_normal_listview_like_cb);
            itemHomeNormalListviewTitleTv = (TextView) itemView.findViewById(R.id.item_home_normal_listview_title_tv);
        }
    }
}
