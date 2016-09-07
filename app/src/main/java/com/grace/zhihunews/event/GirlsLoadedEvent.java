package com.grace.zhihunews.event;

import com.grace.zhihunews.network.entity.Girl;

import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */
public class GirlsLoadedEvent {

    public List<Girl> girls;

    public GirlsLoadedEvent(List<Girl> girls) {
        this.girls = girls;
    }

}
