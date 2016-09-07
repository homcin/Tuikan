package com.grace.zhihunews.PresenterCompl;

import com.grace.zhihunews.contract.GirlsContact;
import com.grace.zhihunews.deliveryLayer.GirlsProvider;
import com.grace.zhihunews.deliveryLayer.IGirlsProvider;
import com.grace.zhihunews.event.GirlsLoadedEvent;
import com.grace.zhihunews.event.LoadFailureEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/9/2.
 */
public class GirlsPresenterCompl implements GirlsContact.IGirlsPresenter {

    private GirlsContact.IGirlsView girlsView;
    private IGirlsProvider girlsProvider;

    public GirlsPresenterCompl(GirlsContact.IGirlsView girlsView) {
        this.girlsView = girlsView;
        girlsProvider = new GirlsProvider();
        EventBus.getDefault().register(this);
    }

    @Override
    public void loadBenefit(int page) {
        girlsProvider.getGirls(page);
    }

    public void onEvent(GirlsLoadedEvent event) {
        girlsView.showGirls(event.girls);
    }

    public void onEvent(LoadFailureEvent event) {
        girlsView.showLoadFailureMsg(event.errorMsg);
    }

}
