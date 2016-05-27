package com.example.dllo.liwushuo.category.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.liwushuo.category.RaidersDownBean;

/**
 * Created by dllo on 16/5/27.
 */
public class RaidersCategoryListviewGridviewAdapter  extends BaseAdapter{
    private RaidersDownBean raidersDownBean;
    private Context context;

    public RaidersCategoryListviewGridviewAdapter(Context context) {
        this.context = context;
    }

    public void setRaidersDownBean(RaidersDownBean raidersDownBean) {
        this.raidersDownBean = raidersDownBean;
        notifyDataSetChanged();
    }

    //TODO:修改listview gridview嵌套问题

    @Override
    public int getCount() {
        return raidersDownBean.getData().getChannel_groups().get(0).getChannels().size();
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
        return null;
    }
}
