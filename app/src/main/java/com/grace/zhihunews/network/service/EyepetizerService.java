package com.grace.zhihunews.network.service;

import com.grace.zhihunews.network.entity.video.Category;
import com.grace.zhihunews.network.entity.video.Feature;
import com.grace.zhihunews.network.entity.video.Issue;
import com.grace.zhihunews.network.entity.video.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/9/9.
 */
public interface EyepetizerService {

    //num为1返回一天的精选，为2返回两条的精选。
    @GET("api/v2/feed?num=1&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    Call<Feature> getTodayFeature();

    @GET("api/v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    Call<List<Category>> getCategories();


    @GET("api/v3/videos")
    Call<Issue> getCategoryFeature(
        @Query("strategy")String strategy1,
        @Query("udid")String udid1,
        @Query("vc")String vc1,
        @Query("categoryName")String categoryName1
    );

}
