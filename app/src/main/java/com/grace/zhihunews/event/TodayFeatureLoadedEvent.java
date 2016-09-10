package com.grace.zhihunews.event;

import com.grace.zhihunews.network.entity.video.Feature;

/**
 * Created by Administrator on 2016/9/9.
 */
public class TodayFeatureLoadedEvent {

    public Feature feature;

    public TodayFeatureLoadedEvent(Feature feature) {
        this.feature = feature;
    }

}
