package com.example.dllo.liwushuo.register;

import cn.bmob.v3.BmobObject;

/**
 * Created by dllo on 16/6/13.
 */
public class BmobRaidersBean extends BmobObject {
    //像网络数据库传入数据 title isLike imgUrl raiders id
    private String title,imgurl, id, userName;
    private Boolean isLike, raiders;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
    }

    public Boolean getRaiders() {
        return raiders;
    }

    public void setRaiders(Boolean raiders) {
        this.raiders = raiders;
    }
}
