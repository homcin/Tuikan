package com.grace.zhihunews.network.entity;

import com.litesuits.orm.db.annotation.Column;

import java.util.Date;

/**
 * Created by Administrator on 2016/9/2.
 */
public class Girl {

    private String url;
    private String who;
    private String createdAt;
    private String publishedAt;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getUrl() {
        return url;
    }

    public String getWho() {
        return who;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }
}
