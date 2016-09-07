package com.grace.zhihunews.event;

import com.grace.zhihunews.network.entity.BeforeNews;

/**
 * Created by Administrator on 2016/8/31.
 */
public class BeforeNewsLoadedEvent {

    public BeforeNews beforeNews;

    public BeforeNewsLoadedEvent(BeforeNews beforeNews) { this.beforeNews = beforeNews; }
}
