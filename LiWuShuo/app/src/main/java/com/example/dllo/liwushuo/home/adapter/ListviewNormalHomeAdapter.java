package com.example.dllo.liwushuo.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.home.bean.NormalListviewBean;
import com.example.dllo.liwushuo.register.RegisterActivity;
import com.example.dllo.liwushuo.select.SelectBean;
import com.example.dllo.liwushuo.tool.App;
import com.example.dllo.liwushuo.tool.RoundRectTool;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.bmob.v3.BmobUser;

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

    public void addItemBean(List<NormalListviewBean.DataBean.ItemsBean> itemsBeans) {
        normalListviewBean.getData().getItems().addAll(itemsBeans);
        notifyDataSetChanged();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewholder myViewholder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_normal_listview, parent, false);
            myViewholder = new MyViewholder(convertView);
            convertView.setTag(myViewholder);
        } else {
            myViewholder = (MyViewholder) convertView.getTag();
        }

        Picasso.with(context).load(normalListviewBean.getData().getItems().get(position).getCover_image_url()).centerCrop()
                .transform(new RoundRectTool(20)).fit().skipMemoryCache().into(myViewholder.itemHomeNormalListviewImg);
        myViewholder.itemHomeNormalListviewLikeCb.setText(String.valueOf(normalListviewBean.getData().getItems().get(position).getLikes_count()));
        myViewholder.itemHomeNormalListviewTitleTv.setText(normalListviewBean.getData().getItems().get(position).getTitle());
        myViewholder.itemHomeNormalListviewLikeCb.setChecked(normalListviewBean.getData().getItems().get(position).isLiked());

        //checkbox加监听, 解决复用问题
        final MyViewholder finalMyViewholder = myViewholder;
        myViewholder.itemHomeNormalListviewLikeCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否登录
                BmobUser bmobUser = BmobUser.getCurrentUser(context);
                if (bmobUser == null) {
                    Intent intent = new Intent(context, RegisterActivity.class);
                    context.startActivity(intent);
                    finalMyViewholder.itemHomeNormalListviewLikeCb.setChecked(false);
                } else {
                    CheckBox checkBox = (CheckBox) v;
                    normalListviewBean.getData().getItems().get(position).setLiked(checkBox.isChecked());
                }

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
