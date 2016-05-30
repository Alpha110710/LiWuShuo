package com.example.dllo.liwushuo.category.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.category.RaidersDetailsActivity;
import com.example.dllo.liwushuo.category.bean.RaidersDownBean;
import com.example.dllo.liwushuo.view.NoScorllGridview;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dllo on 16/5/27.
 */
public class RaidersCategoryListviewAdapter extends BaseAdapter {
    private Context context;
    private RaidersDownBean raidersDownBean;
    ArrayList<SimpleAdapter> adapters;


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
        final MyRaidersListviewViewHolder myRaidersListviewViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_raiders_down_listview, parent, false);
            myRaidersListviewViewHolder = new MyRaidersListviewViewHolder(convertView);
            convertView.setTag(myRaidersListviewViewHolder);
        } else {
            myRaidersListviewViewHolder = (MyRaidersListviewViewHolder) convertView.getTag();
        }
        //设置局部变量pos为每一个listviewItem行位置
        myRaidersListviewViewHolder.pos = position;

        //初始化gridview适配器
        adapters = new ArrayList<>();
        for (int i = 0; i < raidersDownBean.getData().getChannel_groups().size(); i++) {
            SimpleAdapter adapter = null;
            ArrayList<HashMap<String, Object>> lst = new ArrayList<>();
            for (int i1 = 0; i1 < raidersDownBean.getData().getChannel_groups().get(i).getChannels().size(); i1++) {
                final HashMap<String, Object> l = new HashMap<>();
                //给gridview适配器传入图片url和名字
                l.put("categoryImg", raidersDownBean.getData().getChannel_groups().get(i).getChannels().get(i1).getIcon_url());
                l.put("categoryTv", raidersDownBean.getData().getChannel_groups().get(i).getChannels().get(i1).getName());
                lst.add(l);
                //创建gridview适配器
                adapter = CreateSimpleAdapter.CreateRaidersSimpleAdapter(context, lst);

            }
            //将适配器加入集合中
            adapters.add(adapter);

        }
        if (adapters != null) {
            myRaidersListviewViewHolder.itemCategoryDownListviewGridview.setAdapter(adapters.get(position));
        }
        //给listview设置大标题:对象...
        myRaidersListviewViewHolder.itemCategoryDownListviewTv.setText(raidersDownBean.getData().getChannel_groups().get(position).getName());

        myRaidersListviewViewHolder.itemCategoryDownListviewGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //myRaidersListviewViewHolder.pos 为大分组的位置
                // position为 小图标的位置
                Intent intent = new Intent(context, RaidersDetailsActivity.class);
                intent.putExtra("raidersDetailUrl", String.valueOf(raidersDownBean.getData().getChannel_groups().get(myRaidersListviewViewHolder.pos).getChannels().get(position).getId()));
                intent.putExtra("raidersDetailName",raidersDownBean.getData().getChannel_groups().get(myRaidersListviewViewHolder.pos).getChannels().get(position).getName());
                context.startActivity(intent);
            }
        });


        return convertView;
    }


    class MyRaidersListviewViewHolder {
        int pos;
        private final TextView itemCategoryDownListviewTv;
        private final NoScorllGridview itemCategoryDownListviewGridview;

        public MyRaidersListviewViewHolder(View itemView) {
            itemCategoryDownListviewTv = (TextView) itemView.findViewById(R.id.item_category_down_listview_tv);
            itemCategoryDownListviewGridview = (NoScorllGridview) itemView.findViewById(R.id.item_category_down_listview_gridview);

        }


    }

}
