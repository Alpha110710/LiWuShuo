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
import android.widget.Toast;

import com.example.dllo.liwushuo.R;
import com.example.dllo.liwushuo.home.bean.NormalListviewBean;
import com.example.dllo.liwushuo.register.BmobRaidersBean;
import com.example.dllo.liwushuo.register.RegisterActivity;
import com.example.dllo.liwushuo.select.SelectBean;
import com.example.dllo.liwushuo.tool.App;
import com.example.dllo.liwushuo.tool.CollectCheckBoxTool;
import com.example.dllo.liwushuo.tool.RoundRectTool;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/5/25.
 */
public class ListviewNormalHomeAdapter extends BaseAdapter {
    private Context context;
    private NormalListviewBean normalListviewBean;
    public BmobRaidersBean collectRaidersBean;
    public CollectCheckBoxTool checkBoxTool;


    public void setNormalListviewBean(NormalListviewBean normalListviewBean) {
        this.normalListviewBean = normalListviewBean;
        notifyDataSetChanged();
    }

    public ListviewNormalHomeAdapter(Context context) {
        this.context = context;
        checkBoxTool = new CollectCheckBoxTool(this.context);
        checkBoxTool.queryAllLike(this);
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
        myViewholder.setPos(position);

        Picasso.with(context).load(normalListviewBean.getData().getItems().get(position).getCover_image_url()).centerCrop()
                .transform(new RoundRectTool(20)).fit().skipMemoryCache().into(myViewholder.itemHomeNormalListviewImg);
        myViewholder.itemHomeNormalListviewLikeCb.setText(String.valueOf(normalListviewBean.getData().getItems().get(position).getLikes_count()));
        myViewholder.itemHomeNormalListviewTitleTv.setText(normalListviewBean.getData().getItems().get(position).getTitle());
//        myViewholder.itemHomeNormalListviewLikeCb.setChecked(normalListviewBean.getData().getItems().get(position).isLiked());
        checkBoxTool.queryIsLikeRaiders(String.valueOf(normalListviewBean.getData().getItems().get(position).getId()), myViewholder.itemHomeNormalListviewLikeCb);


        return convertView;
    }

    class MyViewholder {
        private int pos;

        private final ImageView itemHomeNormalListviewImg;
        private final CheckBox itemHomeNormalListviewLikeCb;
        private final TextView itemHomeNormalListviewTitleTv;

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }

        public MyViewholder(View itemView) {
            itemHomeNormalListviewImg = (ImageView) itemView.findViewById(R.id.item_home_normal_listview_img);
            itemHomeNormalListviewLikeCb = (CheckBox) itemView.findViewById(R.id.item_home_normal_listview_like_cb);
            itemHomeNormalListviewTitleTv = (TextView) itemView.findViewById(R.id.item_home_normal_listview_title_tv);


            //完全不能改  耦合度很高
            itemHomeNormalListviewLikeCb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //判断是否登录
                    BmobUser bmobUser = BmobUser.getCurrentUser(context);
                    if (bmobUser == null) {
                        Intent intent = new Intent(context, RegisterActivity.class);
                        context.startActivity(intent);
                        itemHomeNormalListviewLikeCb.setChecked(false);
                    } else {
                        CheckBox checkBox = (CheckBox) v;
                        normalListviewBean.getData().getItems().get(pos).setLiked(checkBox.isChecked());

                        if (checkBox.isChecked()) {
                            //像网络数据库传入数据 title isLike imgUrl raiders id
                            collectRaidersBean = new BmobRaidersBean();
                            collectRaidersBean.setLike(normalListviewBean.getData().getItems().get(pos).isLiked());
                            collectRaidersBean.setId(String.valueOf(normalListviewBean.getData().getItems().get(pos).getId()));
                            collectRaidersBean.setImgurl(normalListviewBean.getData().getItems().get(pos).getCover_image_url());
                            collectRaidersBean.setRaiders(true);
                            collectRaidersBean.setTitle(normalListviewBean.getData().getItems().get(pos).getTitle());
                            collectRaidersBean.setUserName(bmobUser.getUsername());

                            collectRaidersBean.save(context, new SaveListener() {
                                @Override
                                public void onSuccess() {
                                    Toast.makeText(context, "喜欢成功", Toast.LENGTH_SHORT).show();
                                    //查询方法
                                    checkBoxTool.queryAllLike();
                                }

                                @Override
                                public void onFailure(int i, String s) {
                                    Toast.makeText(context, "喜欢失败" + s, Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            //取消喜欢
                            checkBoxTool.cancleLikeRaiders(String.valueOf(normalListviewBean.getData().getItems().get(pos).getId()));
                            checkBoxTool.queryAllLike();
                        }
                    }
                }


            });
        }


    }
}
