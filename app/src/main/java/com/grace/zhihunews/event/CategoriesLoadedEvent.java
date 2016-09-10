package com.grace.zhihunews.event;

import com.grace.zhihunews.network.entity.video.Category;

import java.util.List;

/**
 * Created by Administrator on 2016/9/10.
 */
public class CategoriesLoadedEvent {

    public List<Category> categories;

    public CategoriesLoadedEvent(List<Category> categories) {
        this.categories = categories;
    }

}
