package com.example.dllo.liwushuo.category.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.category.bean.GiftBean;
import com.example.dllo.liwushuo.view.CheckableLinearLayout;

/**
 * Created by dllo on 16/5/30.
 */
public class GiftCategoryListviewLeftAdapter extends BaseAdapter {
    private GiftBean giftBean;
    private Context context;
    private int selectPos = 0;

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

    //接受左侧listview点击位置
    public void setSelectPos(int selectPos){
        this.selectPos = selectPos;
        notifyDataSetChanged();
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
        //判断当前显示position位置如果与传入位置一致,则设置选中状态
        if(selectPos == position){
            myGiftLeftViewHolder.itemGiftLeftPinkCb.setSelected(true);

        }else {
            myGiftLeftViewHolder.itemGiftLeftPinkCb.setSelected(false);
        }

        myGiftLeftViewHolder.itemGiftLeftPinkCb.setText(giftBean.getData().getCategories().get(position).getName());

        return convertView;
    }



    class MyGiftLeftViewHolder{

        private final TextView itemGiftLeftPinkCb;

        public MyGiftLeftViewHolder(View itemView) {
            itemGiftLeftPinkCb = (TextView) itemView.findViewById(R.id.item_gift_left_pink_cb);

        }
    }
}
