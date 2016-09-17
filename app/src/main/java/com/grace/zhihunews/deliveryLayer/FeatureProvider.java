package com.grace.zhihunews.deliveryLayer;

import android.util.Log;

import com.grace.zhihunews.event.CategoriesLoadedEvent;
import com.grace.zhihunews.event.CategoryFeatureLoadedEvent;
import com.grace.zhihunews.event.TodayFeatureLoadedEvent;
import com.grace.zhihunews.event.LoadFailureEvent;
import com.grace.zhihunews.network.RetrofitFactory;
import com.grace.zhihunews.network.entity.video.Category;
import com.grace.zhihunews.network.entity.video.Feature;
import com.grace.zhihunews.network.entity.video.Issue;
import com.grace.zhihunews.network.entity.video.Item;
import com.grace.zhihunews.network.service.EyepetizerService;

import java.net.URLEncoder;
import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/9.
 */
public class FeatureProvider implements IFeatureProvider {

    private EyepetizerService eyepetizerService;

    public FeatureProvider() {
        eyepetizerService = RetrofitFactory.getEyepetizerService();
    }

    public void getTodayFeature() {
        Call<Feature> call = eyepetizerService.getTodayFeature();
        call.enqueue(new Callback<Feature>() {
            @Override
            public void onResponse(Call<Feature> call, Response<Feature> response) {
                EventBus.getDefault().post(new TodayFeatureLoadedEvent(response.body()));
            }

            @Override
            public void onFailure(Call<Feature> call, Throwable t) {
                EventBus.getDefault().post(new LoadFailureEvent("今日精选加载失败"));
            }
        });
    }

    public void getCategories() {
        Call<List<Category>> call = eyepetizerService.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                EventBus.getDefault().post(new CategoriesLoadedEvent(response.body()));
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                EventBus.getDefault().post(new LoadFailureEvent("发现分类加载失败"));
            }
        });
    }

    @Override
    public void getCategroyFeature(String categoryName) {
        Call<Issue> call = eyepetizerService.getCategoryFeature(
                "date",
                "26868b32e808498db32fd51fb422d00175e179df",
                "83",
                categoryName);
        call.enqueue(new Callback<Issue>() {
            @Override
            public void onResponse(Call<Issue> call, Response<Issue> response) {
                EventBus.getDefault().post(new CategoryFeatureLoadedEvent(response.body().getItemList().subList(0, 4)));
            }

            @Override
            public void onFailure(Call<Issue> call, Throwable t) {
                EventBus.getDefault().post(new LoadFailureEvent("分类视频内容加载失败"));
            }
        });
    }
}
