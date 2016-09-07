package com.grace.zhihunews.PresenterCompl;

import android.util.Log;

import com.grace.zhihunews.App;
import com.grace.zhihunews.contract.NewsListContact;
import com.grace.zhihunews.deliveryLayer.INewsListProvider;
import com.grace.zhihunews.deliveryLayer.NewsListProvider;
import com.grace.zhihunews.event.BeforeNewsLoadedEvent;
import com.grace.zhihunews.event.GotoNewsDetailEvent;
import com.grace.zhihunews.event.LatestNewsLoadedEvent;
import com.grace.zhihunews.event.LoadFailureEvent;
import com.grace.zhihunews.event.TopStoriesLoadedEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/9/1.
 */
public class NewsListPresenterCompl implements NewsListContact.INewsListPresenter {

    private App app;
    private NewsListContact.INewsListView mNewsListView;
    private INewsListProvider mNewsListProvider;

    public NewsListPresenterCompl(App app, NewsListContact.INewsListView newListView) {
        this.app = app;
        mNewsListView = newListView;
        mNewsListProvider = new NewsListProvider(app);

        EventBus.getDefault().register(this);
    }

    @Override
    public void loadLatestNews() {
        mNewsListProvider.getLatestNews();
    }

    @Override
    public void loadBeforeNews(String date) {
        mNewsListProvider.getBeforeNews(date);
    }

    @Override
    public void loadTopStories(Boolean needRefresh) {
        mNewsListProvider.getTopStories(needRefresh);
    }

    //EventBus的onEvent
    public void onEvent(LatestNewsLoadedEvent event) {
        mNewsListView.showLatestNews(event.latestNews);
    }

    public void onEvent(BeforeNewsLoadedEvent event) {
        mNewsListView.showBeforeNews(event.beforeNews);
    }

    public void onEvent(TopStoriesLoadedEvent event) {
        mNewsListView.showTopStories(event.topstories);
    }

    public void onEvent(LoadFailureEvent event) {
        mNewsListView.showLoadFailureMsg(event.errorMsg);
    }

    public void onEvent(GotoNewsDetailEvent event) {
        mNewsListView.gotoNewsDetailActivity(event.id);
    }
}
