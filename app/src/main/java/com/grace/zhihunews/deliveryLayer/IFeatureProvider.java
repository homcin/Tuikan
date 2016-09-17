package com.grace.zhihunews.deliveryLayer;

import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
public interface IFeatureProvider {

    void getCategories();

    void getTodayFeature();

    void getCategroyFeature(String categoryName);

}
