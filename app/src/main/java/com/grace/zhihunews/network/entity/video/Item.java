package com.grace.zhihunews.network.entity.video;

/**
 * Created by Administrator on 2016/9/9.
 */
public class Item {

    private String type; //type有三种：广告"banner1"；视频"video"；文字标题"textHeader"（内容为："- Sep. 08 -"）。
    private Data data;

    public String getType() {
        return type;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setType(String type) {
        this.type = type;
    }
}
