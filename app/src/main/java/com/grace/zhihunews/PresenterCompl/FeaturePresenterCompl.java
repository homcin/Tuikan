package com.grace.zhihunews.PresenterCompl;

import com.grace.zhihunews.contract.FeatureContact;
import com.grace.zhihunews.deliveryLayer.FeatureProvider;
import com.grace.zhihunews.event.CategoriesLoadedEvent;
import com.grace.zhihunews.event.TodayFeatureLoadedEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/9/9.
 */
public class FeaturePresenterCompl implements FeatureContact.IFeaturePresenter {

    private FeatureContact.IFeatureView featureView;
    private FeatureProvider featureProvider;

    public FeaturePresenterCompl(FeatureContact.IFeatureView featureView) {
        this.featureView = featureView;
        featureProvider = new FeatureProvider();
        EventBus.getDefault().register(this);
    }

    @Override
    public void loadTodayFeature() {
        featureProvider.getTodayFeature();
    }

    @Override
    public void loadCategories() {
        featureProvider.getCategories();
    }

    public void onEvent(TodayFeatureLoadedEvent event) {
        featureView.showTodayFeature(event.feature);
    }

    public void onEvent(CategoriesLoadedEvent event) {
        featureView.showCategories(event.categories);
    }

}
