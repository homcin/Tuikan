package com.grace.zhihunews.PresenterCompl;

import com.grace.zhihunews.App;
import com.grace.zhihunews.contract.NewsDetailContract;
import com.grace.zhihunews.deliveryLayer.NewsDetailProvider;
import com.grace.zhihunews.event.LoadFailureEvent;
import com.grace.zhihunews.event.NewsDetailLoadedEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/9/1.
 */
public class NewsDetailPresenterCompl implements NewsDetailContract.INewsDetailPresenter {

    private App app;
    private NewsDetailContract.INewsDetailView mNewsDetailView;
    private NewsDetailProvider mNewsDetailManager;

    public NewsDetailPresenterCompl(App app, NewsDetailContract.INewsDetailView newsDetailView) {
        this.app = app;
        mNewsDetailView = newsDetailView;
        mNewsDetailManager = new NewsDetailProvider(app);

        EventBus.getDefault().register(this);
    }

    @Override
    public void loadNewsDetail(int id) {
        mNewsDetailManager.getNewsDetail(id);
    }

    public void onEvent(NewsDetailLoadedEvent event) {
        mNewsDetailView.showNewsDetail(event.newsDetail);
    }

    public void onEvent(LoadFailureEvent event) {
        mNewsDetailView.showLoadFailureMsg(event.errorMsg);
    }

}
