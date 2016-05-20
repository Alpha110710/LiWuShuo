package com.example.dllo.liwushuo.select;

/**
 * Created by dllo on 16/5/20.
 */
public class SelectBean {
    private String cover_webp_url, favorites_count, name, price;

    public String getCover_webp_url() {
        return cover_webp_url;
    }

    public void setCover_webp_url(String cover_webp_url) {
        this.cover_webp_url = cover_webp_url;
    }

    public String getFavorites_count() {
        return favorites_count;
    }

    public void setFavorites_count(String favorites_count) {
        this.favorites_count = favorites_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
