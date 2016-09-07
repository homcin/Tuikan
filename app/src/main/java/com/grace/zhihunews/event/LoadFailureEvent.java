package com.grace.zhihunews.event;

/**
 * Created by Administrator on 2016/8/31.
 */
public class LoadFailureEvent {

    public String errorMsg;

    public LoadFailureEvent(String errorMsg) { this.errorMsg = errorMsg; }
}
