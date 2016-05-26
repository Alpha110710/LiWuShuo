package com.example.dllo.liwushuo.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.home.bean.NormalListviewBean;
import com.example.dllo.liwushuo.tool.App;
import com.example.dllo.liwushuo.tool.RoundRectTool;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/5/25.
 */
public class ListviewNormalHomeAdapter extends BaseAdapter {
    private Context context;
    private NormalListviewBean normalListviewBean;

    public void setNormalListviewBean(NormalListviewBean normalListviewBean) {
        this.normalListviewBean = normalListviewBean;
        notifyDataSetChanged();
    }

    public ListviewNormalHomeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {

        return normalListviewBean == null ? 0 : normalListviewBean.getData().getItems().size();
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
        MyViewholder myViewholder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_normal_listview, parent, false);
            myViewholder = new MyViewholder(convertView);
            convertView.setTag(myViewholder);
        } else {
            myViewholder = (MyViewholder) convertView.getTag();
        }

        Picasso.with(App.context).load(normalListviewBean.getData().getItems().get(position).getCover_image_url()).centerCrop()
                .transform(new RoundRectTool(20)).fit().into(myViewholder.itemHomeNormalListviewImg);
        myViewholder.itemHomeNormalListviewLikeTv.setText(String.valueOf(normalListviewBean.getData().getItems().get(position).getLikes_count()));
        myViewholder.itemHomeNormalListviewTitleTv.setText(normalListviewBean.getData().getItems().get(position).getTitle());
        return convertView;
    }

    class MyViewholder {

        private final ImageView itemHomeNormalListviewImg;
        private final TextView itemHomeNormalListviewLikeTv;
        private final TextView itemHomeNormalListviewTitleTv;

        public MyViewholder(View itemView) {
            itemHomeNormalListviewImg = (ImageView) itemView.findViewById(R.id.item_home_normal_listview_img);
            itemHomeNormalListviewLikeTv = (TextView) itemView.findViewById(R.id.item_home_normal_listview_like_tv);
            itemHomeNormalListviewTitleTv = (TextView) itemView.findViewById(R.id.item_home_normal_listview_title_tv);
        }
    }
}
