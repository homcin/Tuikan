package com.grace.zhihunews.deliveryLayer;

import android.util.Log;

import com.grace.zhihunews.App;
import com.grace.zhihunews.cache.LiteOrmManager;
import com.grace.zhihunews.event.GirlsLoadedEvent;
import com.grace.zhihunews.event.LoadFailureEvent;
import com.grace.zhihunews.network.RetrofitFactory;
import com.grace.zhihunews.network.entity.BeforeNews;
import com.grace.zhihunews.network.entity.Benefit;
import com.grace.zhihunews.network.entity.Girl;
import com.grace.zhihunews.network.service.GankService;
import com.grace.zhihunews.util.NetworkUtil;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.model.ConflictAlgorithm;

import java.util.List;

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
    public void getGirls(int page, boolean isLoadMore, boolean needClear) {
        List<Girl> girlList;
        if (!isLoadMore && !needClear) {
            QueryBuilder queryBuilder = new QueryBuilder(Girl.class);
            queryBuilder.appendOrderDescBy("publishedAt");
            queryBuilder.limit(0, 20);
            girlList = LiteOrmManager.getInstance(App.getContext()).query(queryBuilder);
            if (girlList.size() == 0) {
                getGirlsFromServer(page);
            } else {
                EventBus.getDefault().post(new GirlsLoadedEvent(girlList));
            }
        } else {
            if (needClear) {
                long count = LiteOrmManager.getInstance(App.getContext()).queryCount(Girl.class);
                LiteOrmManager.getInstance(App.getContext()).delete(Girl.class, 1, count-20, "publishedAt");
            }
            getGirlsFromServer(page);
        }
    }

    private void getGirlsFromServer(int page) {
        Call<Benefit> call = gankService.getBenefit(page);
        call.enqueue(new Callback<Benefit>() {
            @Override
            public void onResponse(Call<Benefit> call, Response<Benefit> response) {
                LiteOrmManager.getInstance(App.getContext()).insert(response.body().getResults(),ConflictAlgorithm.Replace);
                EventBus.getDefault().post(new GirlsLoadedEvent(response.body().getResults()));
            }

            @Override
            public void onFailure(Call<Benefit> call, Throwable t) {
                EventBus.getDefault().post(new LoadFailureEvent("美女图片加载失败"));
            }
        });
    }
}
