package com.example.dllo.liwushuo.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.home.bean.ConmentBean;
import com.example.dllo.liwushuo.tool.CircleTool;
import com.example.dllo.liwushuo.tool.NetTool;
import com.example.dllo.liwushuo.tool.TimeTool;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/6/2.
 */
public class ConmentListviewAdapter extends BaseAdapter {
    private Context context;
    private ConmentBean conmentBean;

    public void setConmentBean(ConmentBean conmentBean) {
        this.conmentBean = conmentBean;
        notifyDataSetChanged();
    }

    public ConmentListviewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return conmentBean == null ? 0 : conmentBean.getData().getComments().size();
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
        MyConmentListviewViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_conment_down_listview, parent, false);
            holder = new MyConmentListviewViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyConmentListviewViewHolder) convertView.getTag();
        }

        holder.itemConmentDownListviewNameTv.setText(conmentBean.getData().getComments().get(position).getUser().getNickname());
        String hour = TimeTool.getStandardDate(String.valueOf(conmentBean.getData().getComments().get(position).getCreated_at()));
        holder.itemConmentDownListviewviewTimeTv.setText(hour + NetTool.formatData(" , HH:mm", conmentBean.getData().getComments().get(position).getCreated_at()));
        holder.itemConmentDownListviewContentTv.setText(conmentBean.getData().getComments().get(position).getContent());
        holder.itemConmentDownListviewPraiseCb.setText(String.valueOf(conmentBean.getData().getComments().get(position).getLikes_count()));
        if (!conmentBean.getData().getComments().get(position).getUser().getAvatar_url().trim().equals("")) {
            Picasso.with(context).load(conmentBean.getData().getComments().get(position).getUser().getAvatar_url()).placeholder(R.mipmap.me_avatar_girl).fit().transform(new CircleTool()).into(holder.itemConmentDownListviewHeadImg);
        }
        return convertView;
    }

    class MyConmentListviewViewHolder {

        private final TextView itemConmentDownListviewContentTv;
        private final ImageView itemConmentDownListviewHeadImg;
        private final TextView itemConmentDownListviewNameTv;
        private final CheckBox itemConmentDownListviewPraiseCb;
        private final TextView itemConmentDownListviewviewTimeTv;

        public MyConmentListviewViewHolder(View itemView) {
            itemConmentDownListviewContentTv = (TextView) itemView.findViewById(R.id.item_conment_down_listview_content_tv);
            itemConmentDownListviewHeadImg = (ImageView) itemView.findViewById(R.id.item_conment_down_listview_head_img);
            itemConmentDownListviewNameTv = (TextView) itemView.findViewById(R.id.item_conment_down_listview_name_tv);
            itemConmentDownListviewPraiseCb = (CheckBox) itemView.findViewById(R.id.item_conment_down_listview_praise_cb);
            itemConmentDownListviewviewTimeTv = (TextView) itemView.findViewById(R.id.item_conment_down_listview_time_tv);
        }

    }
}
