package com.grace.zhihunews;

import android.app.Application;
import android.content.Context;

import com.grace.zhihunews.cache.ACache;

/**
 * Created by Administrator on 2016/9/1.
 */
public class App extends Application {

    private ACache cache;

    @Override
    public void onCreate() {
        super.onCreate();
        if (cache == null) {
            cache = ACache.get(getApplicationContext());
        }
    }

    public ACache getCacheInstance() {
        return cache;
    }
}
