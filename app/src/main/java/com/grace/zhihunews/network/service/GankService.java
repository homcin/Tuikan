package com.grace.zhihunews.network.service;

import com.grace.zhihunews.network.entity.Benefit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/9/2.
 */
public interface GankService {

    @GET("api/data/福利/10/{page}")
    Call<Benefit> getBenefit(@Path("page") int page);

}
