package com.grace.zhihunews.event;

import com.grace.zhihunews.network.entity.NewsDetail;

/**
 * Created by Administrator on 2016/8/31.
 */
public class NewsDetailLoadedEvent {

    public NewsDetail newsDetail;

    public NewsDetailLoadedEvent(NewsDetail newsDetail) { this.newsDetail = newsDetail; }
}
