package com.grace.zhihunews.PresenterCompl;

import android.util.Log;

import com.grace.zhihunews.contract.FeatureContact;
import com.grace.zhihunews.deliveryLayer.FeatureProvider;
import com.grace.zhihunews.event.CategoriesLoadedEvent;
import com.grace.zhihunews.event.CategoryFeatureLoadedEvent;
import com.grace.zhihunews.event.TodayFeatureLoadedEvent;
import com.grace.zhihunews.network.entity.video.Category;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/9/9.
 */
public class FeaturePresenterCompl implements FeatureContact.IFeaturePresenter {

    private FeatureContact.IFeatureView featureView;
    private FeatureProvider featureProvider;
    private List<String> categoryNameList = new ArrayList<>();

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

    @Override
    public void loadCategoryFeature(String categoryName) {
        featureProvider.getCategroyFeature(categoryName);
    }

    public void onEvent(TodayFeatureLoadedEvent event) {
        featureView.showTodayFeature(event.feature);
        Log.d("size", String.valueOf(categoryNameList.size()));
        for (String categoryName : categoryNameList) {
            loadCategoryFeature(categoryName);
            Log.d("categoryname1", categoryName);
        }
    }

    public void onEvent(CategoriesLoadedEvent event) {
        categoryNameList.clear();
        for (Category category : event.categories) {
            categoryNameList.add(category.getName());
            Log.d("categoryname", category.getName());
        }
        Log.d("categoryNameList", categoryNameList.get(17));
        featureView.showCategories(event.categories);
    }


    public void onEvent(CategoryFeatureLoadedEvent event) {
        featureView.showCategoryFeature(event.itemList);
    }

}
