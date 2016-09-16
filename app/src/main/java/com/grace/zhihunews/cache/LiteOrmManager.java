package com.grace.zhihunews.cache;

import android.content.Context;

import com.litesuits.orm.LiteOrm;

/**
 * Created by Administrator on 2016/9/14.
 */
public class LiteOrmManager {

    private static final String DB_NAME = "tui_kan.db";
    private static LiteOrm sInstance;

    public static synchronized LiteOrm getInstance(Context context) {
        if (sInstance == null) {
            sInstance = LiteOrm.newSingleInstance(context, DB_NAME);
        }
        return sInstance;
    }

}
