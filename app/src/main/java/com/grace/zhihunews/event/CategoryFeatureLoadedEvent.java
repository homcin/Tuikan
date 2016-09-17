package com.grace.zhihunews.event;

import com.grace.zhihunews.network.entity.video.Item;

import java.util.List;

/**
 * Created by Administrator on 2016/9/17.
 */
public class CategoryFeatureLoadedEvent {

    public List<Item> itemList;

    public CategoryFeatureLoadedEvent(List<Item> itemList) {
        this.itemList = itemList;
    }
}
