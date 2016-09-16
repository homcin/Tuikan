package com.grace.zhihunews;

import android.app.Application;
import android.content.Context;

import com.grace.zhihunews.cache.ACache;
import com.litesuits.orm.LiteOrm;

/**
 * Created by Administrator on 2016/9/1.
 */
public class App extends Application {

    private ACache cache;
    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        super.onCreate();
        if (cache == null) {
            cache = ACache.get(getApplicationContext());
        }
    }

    public ACache getCacheInstance() {
        return cache;
    }

    public static Context getContext() {
        return context;
    }

}
