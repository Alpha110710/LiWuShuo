package com.example.dllo.liwushuo.select;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.tool.CircleTool;
import com.example.dllo.liwushuo.tool.NetTool;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/6/6.
 */
public class SelectConmentListviewAdapter extends BaseAdapter {
    private SelectConmentBean selectConmentBean;
    private Context context;

    public SelectConmentListviewAdapter(Context context) {
        this.context = context;
    }

    public void setSelectConmentBean(SelectConmentBean selectConmentBean) {
        this.selectConmentBean = selectConmentBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return selectConmentBean == null ? 0 : selectConmentBean.getData().getComments().size();
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
        MySelectConmentListviewViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_select_conment_listview, parent, false);
            holder = new MySelectConmentListviewViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MySelectConmentListviewViewHolder) convertView.getTag();
        }
        if (selectConmentBean.getData().getComments().get(position).getReplied_user() != null) {
            holder.itemSelecConmentListviewContentTv.setText("回复" + selectConmentBean.getData().getComments().get(position).getReplied_user().getNickname() + ": " + selectConmentBean.getData().getComments().get(position).getContent());
        } else {
            holder.itemSelecConmentListviewContentTv.setText(selectConmentBean.getData().getComments().get(position).getContent());

        }
        holder.itemSelecConmentListviewNameTv.setText(selectConmentBean.getData().getComments().get(position).getUser().getNickname());
        holder.itemSelecConmentListviewTimeTv.setText(NetTool.formatData("yyyy/MM/dd HH:mm", selectConmentBean.getData().getComments().get(position).getCreated_at()));
        if (!selectConmentBean.getData().getComments().get(position).getUser().getAvatar_url().equals("")) {
            Picasso.with(context).load(selectConmentBean.getData().getComments().get(position).getUser().getAvatar_url()).placeholder(R.mipmap.me_avatar_girl)
                    .transform(new CircleTool()).fit().into(holder.itemSelecConmentListviewHeadImg);
        }

        return convertView;
    }


    class MySelectConmentListviewViewHolder {

        private final ImageView itemSelecConmentListviewHeadImg;
        private final TextView itemSelecConmentListviewContentTv;
        private final TextView itemSelecConmentListviewNameTv;
        private final TextView itemSelecConmentListviewTimeTv;

        public MySelectConmentListviewViewHolder(View itemView) {
            itemSelecConmentListviewHeadImg = (ImageView) itemView.findViewById(R.id.item_select_conment_listview_head_img);
            itemSelecConmentListviewContentTv = (TextView) itemView.findViewById(R.id.item_select_conment_listview_content_tv);
            itemSelecConmentListviewNameTv = (TextView) itemView.findViewById(R.id.item_select_conment_listview_name_tv);
            itemSelecConmentListviewTimeTv = (TextView) itemView.findViewById(R.id.item_select_conment_listview_time_tv);

        }
    }
}
