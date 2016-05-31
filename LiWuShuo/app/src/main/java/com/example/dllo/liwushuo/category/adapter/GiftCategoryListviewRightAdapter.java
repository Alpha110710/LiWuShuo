package com.example.dllo.liwushuo.category.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.category.GiftDetailsActivity;
import com.example.dllo.liwushuo.category.bean.GiftBean;
import com.example.dllo.liwushuo.view.NoScorllGridview;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dllo on 16/5/30.
 */
public class GiftCategoryListviewRightAdapter extends BaseAdapter {
    private GiftBean giftBean;
    private Context context;

    public GiftCategoryListviewRightAdapter(Context context) {
        this.context = context;
    }

    public void setGiftBean(GiftBean giftBean) {
        this.giftBean = giftBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return giftBean == null ? 0 : giftBean.getData().getCategories().size();
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
        final MyGiftRightViewHolder myGiftRightViewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gift_right, parent, false);
            myGiftRightViewHolder = new MyGiftRightViewHolder(convertView);
            convertView.setTag(myGiftRightViewHolder);
        } else {
            myGiftRightViewHolder = (MyGiftRightViewHolder) convertView.getTag();
        }

        myGiftRightViewHolder.pos = position;

        ArrayList<SimpleAdapter> adapters = new ArrayList<>();
        for (int i = 0; i < giftBean.getData().getCategories().size(); i++) {

            ArrayList<HashMap<String, Object>> lst = new ArrayList<>();
            for (int i1 = 0; i1 < giftBean.getData().getCategories().get(i).getSubcategories().size(); i1++) {
                HashMap<String, Object> hasmap = new HashMap<>();
                hasmap.put("giftTv", giftBean.getData().getCategories().get(i).getSubcategories().get(i1).getName());
                hasmap.put("giftImg", giftBean.getData().getCategories().get(i).getSubcategories().get(i1).getIcon_url());
                lst.add(hasmap);
            }
            SimpleAdapter adapter = CreateSimpleAdapter.CreateGiftSimpleAdapter(context, lst);
            adapters.add(adapter);
        }
        if (adapters != null) {
            myGiftRightViewHolder.itemGiftRightListviewGridview.setAdapter(adapters.get(position));
        }

        myGiftRightViewHolder.itemGiftRightTitleTv.setText(giftBean.getData().getCategories().get(position).getName());

        //设置第一个textview标题不可见
        if (position == 0) {
            myGiftRightViewHolder.itemGiftRightTitleLlayout.setVisibility(View.GONE);
        } else {
            myGiftRightViewHolder.itemGiftRightTitleLlayout.setVisibility(View.VISIBLE);
        }

        //设置每一个小的gridview圆形图片的点击事件
        myGiftRightViewHolder.itemGiftRightListviewGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(context, GiftDetailsActivity.class);
                intent.putExtra("giftDetailUrl", String.valueOf(giftBean.getData().getCategories().get(myGiftRightViewHolder.pos).getSubcategories().get(position).getId()));
                intent.putExtra("giftDetailName", giftBean.getData().getCategories().get(myGiftRightViewHolder.pos).getSubcategories().get(position).getName());
                context.startActivity(intent);

            }
        });


        return convertView;
    }

    class MyGiftRightViewHolder {

        private final NoScorllGridview itemGiftRightListviewGridview;
        private final TextView itemGiftRightTitleTv;
        private final LinearLayout itemGiftRightTitleLlayout;
        int pos;

        public MyGiftRightViewHolder(View itemView) {
            itemGiftRightListviewGridview = (NoScorllGridview) itemView.findViewById(R.id.item_gift_right_listview_gridview);
            itemGiftRightTitleTv = (TextView) itemView.findViewById(R.id.item_gift_right_title_tv);
            itemGiftRightTitleLlayout = (LinearLayout) itemView.findViewById(R.id.item_gift_right_title_llayout);
        }
    }
}
