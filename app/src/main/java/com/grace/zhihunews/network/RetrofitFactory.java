package com.grace.zhihunews.network;

import com.grace.zhihunews.network.service.EyepetizerService;
import com.grace.zhihunews.network.service.GankService;
import com.grace.zhihunews.network.service.ZhifuService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/8/31.
 */
public class RetrofitFactory {

    public static final String ZHIHU_URL = "http://news-at.zhihu.com/";
    public static final String GANK_URL = "http://gank.io/";
    public static final String EYEPETIZER_URL = "http://baobab.wandoujia.com/";

    private static ZhifuService mZhifuService;
    private static GankService gankService;
    private static EyepetizerService eyepetizerService;

    //ZhifuService：单例模式。
    public static synchronized ZhifuService getZhifuService() {
        if (mZhifuService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ZHIHU_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mZhifuService = retrofit.create(ZhifuService.class);
        }
        return mZhifuService;
    }

    public static synchronized GankService getGankService() {
        if (gankService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(GANK_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            gankService = retrofit.create(GankService.class);
        }
        return gankService;
    }

    public static synchronized EyepetizerService getEyepetizerService() {
        if (eyepetizerService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(EYEPETIZER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            eyepetizerService = retrofit.create(EyepetizerService.class);
        }
        return eyepetizerService;
    }

}
