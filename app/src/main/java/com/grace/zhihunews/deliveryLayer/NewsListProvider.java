package com.grace.zhihunews.deliveryLayer;

import android.util.Log;

import com.grace.zhihunews.App;
import com.grace.zhihunews.cache.ACache;
import com.grace.zhihunews.cache.DBHelper;
import com.grace.zhihunews.cache.LiteOrmManager;
import com.grace.zhihunews.event.BeforeNewsLoadedEvent;
import com.grace.zhihunews.event.LatestNewsLoadedEvent;
import com.grace.zhihunews.event.LoadFailureEvent;
import com.grace.zhihunews.event.TopStoriesLoadedEvent;
import com.grace.zhihunews.network.RetrofitFactory;
import com.grace.zhihunews.network.entity.BeforeNews;
import com.grace.zhihunews.network.entity.LatestNews;
import com.grace.zhihunews.network.entity.Story;
import com.grace.zhihunews.network.entity.TopStory;
import com.grace.zhihunews.network.service.ZhifuService;
import com.grace.zhihunews.util.DateUtil;
import com.grace.zhihunews.util.NetworkUtil;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.model.ConflictAlgorithm;

import java.util.ArrayList;
import java.util.List;

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
        String todayDate = DateUtil.getTodayDate();
        QueryBuilder<LatestNews> qbLatestNews = new QueryBuilder<LatestNews>(LatestNews.class)
                .where("date like ?", new String[]{todayDate});
        ArrayList<LatestNews> latestNewsArrayList = LiteOrmManager.getInstance(app).query(qbLatestNews);
        if (latestNewsArrayList.size() > 0) {
            LatestNews latestNews = latestNewsArrayList.get(0);
            QueryBuilder<TopStory> dbTopStory = new QueryBuilder<TopStory>(TopStory.class)
                    .where("date like ?", new String[]{todayDate});
            ArrayList<TopStory> topStories = LiteOrmManager.getInstance(app).query(dbTopStory);
            List<Story> stories = DBHelper.getInstance(app).loadStoriesByDate(todayDate);
            latestNews.setStories(stories);
            latestNews.setTop_stories(topStories);
            EventBus.getDefault().post(new LatestNewsLoadedEvent(latestNews));
        } else {
            Call<LatestNews> call = zhifuService.getLatestNews();
            call.enqueue(new Callback<LatestNews>() {
                @Override
                public void onResponse(Call<LatestNews> call, Response<LatestNews> response) {
                    LiteOrmManager.getInstance(app).insert(response.body(), ConflictAlgorithm.Replace);
                    List<TopStory> topStories = response.body().getTopStories();
                    for (TopStory topStory : topStories) {
                        topStory.setDate(response.body().getDate());
                    }
                    LiteOrmManager.getInstance(app).save(topStories);
                    List<Story> stories = response.body().getStories();
                    for (Story story : stories) {
                        story.setDate(response.body().getDate());
                        DBHelper.getInstance(app).saveStory(story);
                    }
                    EventBus.getDefault().post(new LatestNewsLoadedEvent(response.body()));
                }
                @Override
                public void onFailure(Call<LatestNews> call, Throwable t) {
                    EventBus.getDefault().post(new LoadFailureEvent("最新文章列表加载失败"));
                }
            });
        }
    }

    @Override
    public void getBeforeNews(final String date){
        QueryBuilder<BeforeNews> qb = new QueryBuilder<BeforeNews>(BeforeNews.class)
                .where("date like ?", new String[]{date});
        ArrayList<BeforeNews> beforeNewsArrayList = LiteOrmManager.getInstance(app).query(qb);
        if (beforeNewsArrayList.size() > 0) {
            List<Story> stories = DBHelper.getInstance(app).loadStoriesByDate(date);
            BeforeNews beforeNews = beforeNewsArrayList.get(0);
            beforeNews.setStories(stories);
            EventBus.getDefault().post(new BeforeNewsLoadedEvent(beforeNews));
        } else {
            Call<BeforeNews> call = zhifuService.getBeforeNews(date);
            call.enqueue(new Callback<BeforeNews>() {
                @Override
                public void onResponse(Call<BeforeNews> call, Response<BeforeNews> response) {
                    LiteOrmManager.getInstance(app).insert(response.body(), ConflictAlgorithm.Replace);
                    List<Story> stories = response.body().getStories();
                    for (Story story : stories) {
                        story.setDate(date);
                        DBHelper.getInstance(app).saveStory(story);
                    }
                    EventBus.getDefault().post(new BeforeNewsLoadedEvent(response.body()));
                }
                @Override
                public void onFailure(Call<BeforeNews> call, Throwable t) {
                    EventBus.getDefault().post(new LoadFailureEvent("过往文章列表加载失败"));
                }
            });
        }
    }

    @Override
    public void refreshData() {
        LiteOrmManager.getInstance(app).deleteAll(LatestNews.class);
        LiteOrmManager.getInstance(app).deleteAll(BeforeNews.class);
        LiteOrmManager.getInstance(app).deleteAll(TopStory.class);
        DBHelper.getInstance(app).deleteAllStory();
    }
}
