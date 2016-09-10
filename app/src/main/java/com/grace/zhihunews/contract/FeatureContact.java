package com.grace.zhihunews.contract;

import com.grace.zhihunews.contract.base.BasePresenter;
import com.grace.zhihunews.contract.base.BaseView;
import com.grace.zhihunews.network.entity.video.Category;
import com.grace.zhihunews.network.entity.video.Feature;

import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
public interface FeatureContact {

    interface IFeatureView extends BaseView {

        void showTodayFeature(Feature feature);

        void showCategories(List<Category> categories);

    }

    interface IFeaturePresenter extends BasePresenter {

        void loadTodayFeature();

        void loadCategories();

    }
}
