package com.grace.zhihunews.contract;

import com.grace.zhihunews.contract.base.BasePresenter;
import com.grace.zhihunews.contract.base.BaseView;
import com.grace.zhihunews.network.entity.Girl;

import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */
public interface GirlsContact {

    interface IGirlsView extends BaseView {

        void showGirls(List<Girl> girls);

    }

    interface IGirlsPresenter extends BasePresenter {

        void loadBenefit(int page);

    }
}
