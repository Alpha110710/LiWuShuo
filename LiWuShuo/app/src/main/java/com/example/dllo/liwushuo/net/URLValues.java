package com.example.dllo.liwushuo.net;

/**
 * Created by dllo on 16/5/23.
 */
public final class URLValues {
    //hometab标题url
    public static final String ROLL_CHANNEL = "http://api.liwushuo.com/v2/channels/preset?gender=2&generation=1";
    //home轮播图url
    public static final String CAROUSEL = "http://api.liwushuo.com/v2/banners";
    //home小方框url
    public static final String  SPECIAL= "http://api.liwushuo.com/v2/secondary_banners?gender=2&generation=1";
    //home的listview url
    public static final String HOME_CELL = "http://api.liwushuo.com/v2/channels/103/items?limit=20&ad=2&gender=2&offset=0&generation=1";

    //select的分类页面
    public static final String SELECT = "http://api.liwushuo.com/v2/items?limit=20&offset=0&gender=2&generation=1";
    //home的tab分页url 穿搭
    public static final String ITEM_BEFORE = "http://api.liwushuo.com/v2/channels/";
    public static final String ITEM_AFTER = "/items?";

    //raidersCategoryfragment界面的上方平滑图片url
    public static final String RAIDERS_TOPIC = "http://api.liwushuo.com/v2/collections?limit=10&offset=0";

    //raidersCategoryfragment界面的下方的圆形图片所有内容url
    public static final String RAIDERS_DOWN = "http://api.liwushuo.com/v2/channel_groups/all";

    //raidersCategoryfragment界面下方圆形图片的点击url
    public static final String ITEM_RAIDERS_BEFORE = "http://api.liwushuo.com/v2/channels/";
    public static final String ITEM_RAIDERS_AFTER = "/items?limit=20&gender=1&offset=0&generation=1&order_by=now";

    //gift 礼物界面的listview解析url
    public static final String GIFT_LISTVIEW = "http://api.liwushuo.com/v2/item_categories/tree";


}
