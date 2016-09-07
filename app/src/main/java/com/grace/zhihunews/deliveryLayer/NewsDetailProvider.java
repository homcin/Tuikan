package com.grace.zhihunews.deliveryLayer;


import com.grace.zhihunews.App;
import com.grace.zhihunews.cache.ACache;
import com.grace.zhihunews.event.LoadFailureEvent;
import com.grace.zhihunews.event.NewsDetailLoadedEvent;
import com.grace.zhihunews.network.RetrofitFactory;
import com.grace.zhihunews.network.entity.NewsDetail;
import com.grace.zhihunews.network.service.ZhifuService;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/1.
 */
public class NewsDetailProvider implements INewsDetailProvider {

    private App app;
    private ACache cache;
    private ZhifuService zhifuService;

    public NewsDetailProvider(App app) {
        this.app = app;
        cache = app.getCacheInstance();
        zhifuService = RetrofitFactory.getZhifuService();
    }

    @Override
    public void getNewsDetail(final int id){
        NewsDetail newsDetail;
        if ((newsDetail = readNewsDetailFromCache(id)) != null) {
            EventBus.getDefault().post(new NewsDetailLoadedEvent(newsDetail));
        } else {
            Call<NewsDetail> call = zhifuService.getNewsDetail(id);
            call.enqueue(new Callback<NewsDetail>() {
                @Override
                public void onResponse(Call<NewsDetail> call, Response<NewsDetail> response) {
                    EventBus.getDefault().post(new NewsDetailLoadedEvent(response.body()));
                    saveNewsDetailToCache(id, response.body());
                }

                @Override
                public void onFailure(Call<NewsDetail> call, Throwable t) {
                    EventBus.getDefault().post(new LoadFailureEvent("文章内容加载失败"));
                }
            });
        }
    }

    private void saveNewsDetailToCache(int id, NewsDetail newsDetail) {
        cache.put(Integer.toString(id), newsDetail, ACache.TIME_DAY * 14);
    }

    private NewsDetail readNewsDetailFromCache(int id) {
        NewsDetail newsDetail = (NewsDetail) cache.getAsObject(Integer.toString(id));
        return newsDetail;
    }

}
