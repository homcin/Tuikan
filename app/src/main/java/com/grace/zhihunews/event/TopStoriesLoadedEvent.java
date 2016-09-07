package com.grace.zhihunews.event;

import com.grace.zhihunews.network.entity.TopStory;

import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
public class TopStoriesLoadedEvent {

    public List<TopStory> topstories;

    public TopStoriesLoadedEvent(List<TopStory> topstories) {
        this.topstories = topstories;
    }
}
