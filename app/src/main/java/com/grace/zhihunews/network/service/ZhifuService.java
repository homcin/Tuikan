package com.grace.zhihunews.network.service;

import com.grace.zhihunews.network.entity.BeforeNews;
import com.grace.zhihunews.network.entity.LatestNews;
import com.grace.zhihunews.network.entity.NewsDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/8/31.
 */
public interface ZhifuService {

    //http://news-at.zhihu.com/api/4/news/latest
    @GET("api/4/news/latest")
    Call<LatestNews> getLatestNews();

    //http://news.at.zhihu.com/api/4/news/before/20160831
    @GET("api/4/news/before/{date}")
    Call<BeforeNews> getBeforeNews(@Path("date") String date);

    //http://news-at.zhihu.com/api/4/news/8725424
    @GET("api/4/news/{id}")
    Call<NewsDetail> getNewsDetail(@Path("id") int id);
}
