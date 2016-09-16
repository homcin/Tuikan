package com.grace.zhihunews.deliveryLayer;

/**
 * Created by Administrator on 2016/9/1.
 */
public interface INewsListProvider {

    void getLatestNews();

    void getBeforeNews(String date);

    void refreshData();

    //void getTopStories(boolean needRefresh);
}
