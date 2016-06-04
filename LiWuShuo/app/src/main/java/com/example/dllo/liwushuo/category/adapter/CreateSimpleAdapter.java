package com.example.dllo.liwushuo.category.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.example.dllo.liwushuo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dllo on 16/5/28.
 * ,
 */
public class CreateSimpleAdapter {
    public static SimpleAdapter CreateRaidersSimpleAdapter(final Context context, final ArrayList<HashMap<String, Object>> lst) {

        SimpleAdapter simpleAdapter = new SimpleAdapter(context, lst, R.layout.item_raiders_down_listview_gridview,
                new String[]{"categoryTv", "categoryImg"}, new int[]{R.id.item_category_down_listview_gridview_tv,
                R.id.item_category_down_listview_gridview_img});
        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if (view instanceof ImageView && data instanceof String) {

                    Picasso.with(context).load((String) data).placeholder(R.mipmap.ic_about).into((ImageView) view);
                    return true;
                }
                return false;
            }
        });
        return simpleAdapter;
    }

    public static SimpleAdapter CreateGiftSimpleAdapter(final Context context, final ArrayList<HashMap<String, Object>> lst) {

        SimpleAdapter simpleAdapter = new SimpleAdapter(context, lst, R.layout.item_gift_right_listview_gridview,
                new String[]{"giftTv", "giftImg"}, new int[]{R.id.item_gift_right_listview_gridview_tv,
                R.id.item_gift_right_listview_gridview_img});
        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if (view instanceof ImageView && data instanceof String) {

                    Picasso.with(context).load((String) data).placeholder(R.mipmap.ig_logo_text).into((ImageView) view);
                    return true;
                }
                return false;
            }
        });
        return simpleAdapter;
    }

    public static SimpleAdapter CreateGiftConditionPopupSimpleAdapter(final Context context, final ArrayList<HashMap<String, String>> lst) {

        SimpleAdapter simpleAdapter = new SimpleAdapter(context, lst, R.layout.item_gift_condition_popup_gridview,
                new String[]{"giftCondotionPopupTv"}, new int[]{R.id.item_gift_condition_popup_gridview_tv});
        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if (view instanceof ImageView && data instanceof String) {

                    Picasso.with(context).load((String) data).placeholder(R.mipmap.ic_about).into((ImageView) view);
                    return true;
                }
                return false;
            }
        });
        return simpleAdapter;
    }


}
