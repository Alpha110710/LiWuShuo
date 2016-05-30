package com.example.dllo.liwushuo.category.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.category.bean.GiftBean;

/**
 * Created by dllo on 16/5/30.
 */
public class GiftCategoryListviewLeftAdapter extends BaseAdapter {
    private GiftBean giftBean;
    private Context context;

    public GiftCategoryListviewLeftAdapter(Context context) {
        this.context = context;
    }

    public void setGiftBean(GiftBean giftBean) {
        this.giftBean = giftBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return giftBean == null ? 0: giftBean.getData().getCategories().size();
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
        final MyGiftLeftViewHolder myGiftLeftViewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gift_left, parent, false);
            myGiftLeftViewHolder = new MyGiftLeftViewHolder(convertView);
            convertView.setTag(myGiftLeftViewHolder);
        } else {
            myGiftLeftViewHolder = (MyGiftLeftViewHolder) convertView.getTag();
        }

        myGiftLeftViewHolder.itemGiftLeftTagTv.setText(giftBean.getData().getCategories().get(position).getName());

        //TODO:设置粉色小条可见不可见
        myGiftLeftViewHolder.itemGiftLeftLlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myGiftLeftViewHolder.itemGiftLeftPinkTv.setVisibility(View.VISIBLE);
            }
        });

//        myGiftLeftViewHolder.itemGiftLeftPinkTv.setVisibility(View.GONE);



        return convertView;
    }

    class MyGiftLeftViewHolder{

        private final TextView itemGiftLeftPinkTv;
        private final TextView itemGiftLeftTagTv;
        private final LinearLayout itemGiftLeftLlayout;

        public MyGiftLeftViewHolder(View itemView) {
            itemGiftLeftPinkTv = (TextView) itemView.findViewById(R.id.item_gift_left_pink_tv);
            itemGiftLeftTagTv = (TextView) itemView.findViewById(R.id.item_gift_left_tag_tv);
            itemGiftLeftLlayout = (LinearLayout) itemView.findViewById(R.id.item_gift_left_llayout);
        }
    }
}
