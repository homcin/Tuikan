package com.grace.zhihunews.event;

import com.grace.zhihunews.network.entity.LatestNews;

/**
 * Created by Administrator on 2016/8/31.
 */
public class LatestNewsLoadedEvent {

    public LatestNews latestNews;

    public LatestNewsLoadedEvent(LatestNews latestNews) { this.latestNews = latestNews; }
}
