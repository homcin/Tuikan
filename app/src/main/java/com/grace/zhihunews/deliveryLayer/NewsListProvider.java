package com.grace.zhihunews.deliveryLayer;

import android.util.Log;

import com.grace.zhihunews.App;
import com.grace.zhihunews.cache.ACache;
import com.grace.zhihunews.event.BeforeNewsLoadedEvent;
import com.grace.zhihunews.event.LatestNewsLoadedEvent;
import com.grace.zhihunews.event.LoadFailureEvent;
import com.grace.zhihunews.event.TopStoriesLoadedEvent;
import com.grace.zhihunews.network.RetrofitFactory;
import com.grace.zhihunews.network.entity.BeforeNews;
import com.grace.zhihunews.network.entity.LatestNews;
import com.grace.zhihunews.network.service.ZhifuService;
import com.grace.zhihunews.util.NetworkUtil;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/1.
 */
public class NewsListProvider implements INewsListProvider {

    public static final String LatestNews_FILE_NAME = "LatestNews";

    private App app;
    private ACache cache;
    private ZhifuService zhifuService;

    public NewsListProvider(App app) {
        this.app = app;
        cache = app.getCacheInstance();
        zhifuService = RetrofitFactory.getZhifuService();
    }

    @Override
    public void getLatestNews(){
        LatestNews latestNews;
        if (!NetworkUtil.isConnected(app.getApplicationContext())) {
            latestNews = readLatestNewsFromCache();
        } else {
            Call<LatestNews> call = zhifuService.getLatestNews();
            call.enqueue(new Callback<LatestNews>() {
                @Override
                public void onResponse(Call<LatestNews> call, Response<LatestNews> response) {
                    EventBus.getDefault().post(new LatestNewsLoadedEvent(response.body()));
                    //saveLatestNewsToCache(response.body());
                    Log.d("LatestNews", "success");
                    Log.d("LatestNews", response.body().getDate());

                }
                @Override
                public void onFailure(Call<LatestNews> call, Throwable t) {
                    EventBus.getDefault().post(new LoadFailureEvent("最新文章列表加载失败"));
                    Log.d("LatestNews", "Fail");
                }
            });
        }
    }

    @Override
    public void getBeforeNews(final String date){
        BeforeNews beforeNews;
        if (!NetworkUtil.isConnected(app.getApplicationContext())) {
            beforeNews = readBeforeNewsFromCache(date);
        } else {
            Call<BeforeNews> call = zhifuService.getBeforeNews(date);
            call.enqueue(new Callback<BeforeNews>() {
                @Override
                public void onResponse(Call<BeforeNews> call, Response<BeforeNews> response) {
                    EventBus.getDefault().post(new BeforeNewsLoadedEvent(response.body()));
                    saveBeforeNewsToCache(date, response.body());

                }
                @Override
                public void onFailure(Call<BeforeNews> call, Throwable t) {
                    EventBus.getDefault().post(new LoadFailureEvent("过往文章列表加载失败"));
                }
            });
        }
    }

    @Override
    public void getTopStories(boolean needRefresh){
        LatestNews latestNews;
        if (!needRefresh && (latestNews = readLatestNewsFromCache()) != null) {
            EventBus.getDefault().post(new TopStoriesLoadedEvent(latestNews.getTopStories()));
        } else {
            if (zhifuService == null) {
                Log.e("error", "null");
            }
            Call<LatestNews> call = zhifuService.getLatestNews();
            call.enqueue(new Callback<LatestNews>() {
                @Override
                public void onResponse(Call<LatestNews> call, Response<LatestNews> response) {
                    EventBus.getDefault().post(new TopStoriesLoadedEvent(response.body().getTopStories()));
                    Log.d("TopStories", "success");
                    Log.d("TopStories", response.body().getTopStories().get(0).getTitle());
                    saveLatestNewsToCache(response.body());
                }
                @Override
                public void onFailure(Call<LatestNews> call, Throwable t) {
                    EventBus.getDefault().post(new LoadFailureEvent("最新文章列表加载失败"));
                    Log.d("TopStories", "Fail");
                }
            });
        }
    }


    private void saveLatestNewsToCache(LatestNews latestNews) {
        cache.put(LatestNews_FILE_NAME, latestNews, ACache.TIME_DAY * 3);
    }

    private LatestNews readLatestNewsFromCache() {
        LatestNews latestNews = (LatestNews) cache.getAsObject(LatestNews_FILE_NAME);
        return latestNews;
    }

    private void saveBeforeNewsToCache(String date, BeforeNews beforeNews) {
        cache.put(date, beforeNews, ACache.TIME_DAY * 14);
    }

    private BeforeNews readBeforeNewsFromCache(String date) {
        BeforeNews beforeNews = (BeforeNews) cache.getAsObject(date);
        return beforeNews;
    }
}
