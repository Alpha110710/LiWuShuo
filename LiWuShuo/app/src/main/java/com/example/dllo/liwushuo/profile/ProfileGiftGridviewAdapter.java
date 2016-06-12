package com.example.dllo.liwushuo.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.register.BmobCollectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/6/12.
 */
public class ProfileGiftGridviewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BmobCollectBean> collectBeans;

    public void setCollectBeans(ArrayList<BmobCollectBean> collectBeans) {
        this.collectBeans = collectBeans;
        notifyDataSetChanged();
    }

    public ProfileGiftGridviewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return collectBeans == null ? 0 : collectBeans.size();
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
        MyProfileGiftGridviewViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_select_gridview, parent, false);
            viewHolder = new MyProfileGiftGridviewViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (MyProfileGiftGridviewViewHolder) convertView.getTag();
        }
        viewHolder.itemSelectGridviewNameTv.setText(collectBeans.get(position).getName());
        viewHolder.itemSelectGridviewPriceTv.setText("aaa");
        //TODO:没写好


        return convertView;
    }

    class MyProfileGiftGridviewViewHolder {

        private final ImageView itemSelectGridviewImg;
        private final TextView itemSelectGridviewSupportTv, itemSelectGridviewPriceTv, itemSelectGridviewNameTv;

        public MyProfileGiftGridviewViewHolder(View view) {

            itemSelectGridviewImg = (ImageView) view.findViewById(R.id.item_select_gridview_img);
            itemSelectGridviewSupportTv = (TextView) view.findViewById(R.id.item_select_gridview_support_tv);
            itemSelectGridviewPriceTv = (TextView) view.findViewById(R.id.item_select_gridview_price_tv);
            itemSelectGridviewNameTv = (TextView) view.findViewById(R.id.item_select_gridview_name_tv);


        }
    }
}
