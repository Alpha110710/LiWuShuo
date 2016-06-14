package com.example.dllo.liwushuo.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.register.BmobRaidersBean;
import com.example.dllo.liwushuo.tool.RoundRectTool;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/6/13.
 */
public class ProfileRaidersListviewAdapter extends BaseAdapter {
    private List<BmobRaidersBean> bmobRaidersBeen;
    private Context context;

    public ProfileRaidersListviewAdapter(Context context) {
        this.context = context;
    }

    public void setBmobRaidersBeen(List<BmobRaidersBean> bmobRaidersBeen) {
        this.bmobRaidersBeen = bmobRaidersBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bmobRaidersBeen == null ? 0 : bmobRaidersBeen.size();
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
        MyProfileRaidersListviewViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_profile_raiders_listview, parent, false);
            viewHolder = new MyProfileRaidersListviewViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (MyProfileRaidersListviewViewHolder) convertView.getTag();
        }
        viewHolder.itemProfileRaidersListviewTv.setText(bmobRaidersBeen.get(position).getTitle());
        Picasso.with(context).load(bmobRaidersBeen.get(position).getImgurl()).transform(new RoundRectTool(20)).fit().into(viewHolder.itemProfileRaidersListviewImg);

        return convertView;
    }

    public class MyProfileRaidersListviewViewHolder{

        private final ImageView itemProfileRaidersListviewImg;
        private final TextView itemProfileRaidersListviewTv;

        public MyProfileRaidersListviewViewHolder(View itemView) {
            itemProfileRaidersListviewImg = (ImageView) itemView.findViewById(R.id.item_profile_raiders_listview_img);
            itemProfileRaidersListviewTv = (TextView) itemView.findViewById(R.id.item_profile_raiders_listview_tv);
        }
    }
}
