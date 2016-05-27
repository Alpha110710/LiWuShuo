package com.example.dllo.liwushuo.category.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.category.RaidersDownBean;

/**
 * Created by dllo on 16/5/27.
 */
public class RaidersCategoryListviewAdapter extends BaseAdapter {
    private Context context;
    private RaidersDownBean raidersDownBean;


    public void setRaidersDownBean(RaidersDownBean raidersDownBean) {
        this.raidersDownBean = raidersDownBean;
        notifyDataSetChanged();
    }

    public RaidersCategoryListviewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return raidersDownBean == null ? 0 : raidersDownBean.getData().getChannel_groups().size();
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
        MyRaidersListviewViewHolder myRaidersListviewViewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_category_down_listview, parent, false);
            myRaidersListviewViewHolder = new MyRaidersListviewViewHolder(convertView);
            convertView.setTag(myRaidersListviewViewHolder);
        } else {
            myRaidersListviewViewHolder = (MyRaidersListviewViewHolder) convertView.getTag();
        }

        myRaidersListviewViewHolder.itemCategoryDownListviewTv.setText(raidersDownBean.getData().getChannel_groups().get(position).getName());
        myRaidersListviewViewHolder.itemCategoryDownListviewGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return convertView;
    }

    class MyRaidersListviewViewHolder{

        private final TextView itemCategoryDownListviewTv;
        private final GridView itemCategoryDownListviewGridview;

        public MyRaidersListviewViewHolder(View itemView) {
            itemCategoryDownListviewTv = (TextView) itemView.findViewById(R.id.item_category_down_listview_tv);
            itemCategoryDownListviewGridview = (GridView) itemView.findViewById(R.id.item_category_down_listview_gridview);
        }
    }

}
