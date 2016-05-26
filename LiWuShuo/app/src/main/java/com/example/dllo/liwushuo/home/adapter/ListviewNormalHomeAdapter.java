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
import com.example.dllo.liwushuo.tool.App;

/**
 * Created by dllo on 16/5/25.
 */
public class ListviewNormalHomeAdapter extends BaseAdapter {
    private Context context;

    public ListviewNormalHomeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        //TODO:数据数量
        return 10;
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
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_normal_listview, parent, false);
            myViewholder = new MyViewholder(convertView);
            convertView.setTag(myViewholder);
        } else {
            myViewholder = (MyViewholder) convertView.getTag();
        }
        //TODO:放图片
        myViewholder.itemHomeNormalListviewImg.setImageResource(R.mipmap.ic_about);
        return convertView;
    }

    class MyViewholder{

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
