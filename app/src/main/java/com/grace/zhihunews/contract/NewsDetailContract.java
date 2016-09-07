package com.grace.zhihunews.contract;

import com.grace.zhihunews.contract.base.BasePresenter;
import com.grace.zhihunews.contract.base.BaseView;
import com.grace.zhihunews.network.entity.NewsDetail;

/**
 * Created by Administrator on 2016/9/1.
 */
public interface NewsDetailContract {

    interface INewsDetailView extends BaseView {

        void showNewsDetail(NewsDetail newsDetail);

    }

    interface INewsDetailPresenter extends BasePresenter {

        void loadNewsDetail(int id);

    }

}
