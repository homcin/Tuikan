package com.grace.zhihunews.deliveryLayer;

import com.grace.zhihunews.event.GirlsLoadedEvent;
import com.grace.zhihunews.event.LoadFailureEvent;
import com.grace.zhihunews.network.RetrofitFactory;
import com.grace.zhihunews.network.entity.BeforeNews;
import com.grace.zhihunews.network.entity.Benefit;
import com.grace.zhihunews.network.service.GankService;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/2.
 */
public class GirlsProvider implements IGirlsProvider {

    private GankService gankService;

    public GirlsProvider() {
        gankService = RetrofitFactory.getGankService();
    }

    @Override
    public void getGirls(int page) {
        Call<Benefit> call = gankService.getBenefit(page);
        call.enqueue(new Callback<Benefit>() {
            @Override
            public void onResponse(Call<Benefit> call, Response<Benefit> response) {
                EventBus.getDefault().post(new GirlsLoadedEvent(response.body().getResults()));
            }

            @Override
            public void onFailure(Call<Benefit> call, Throwable t) {
                EventBus.getDefault().post(new LoadFailureEvent("美女图片加载失败"));
            }
        });
    }

}
