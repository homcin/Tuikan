package com.grace.zhihunews.network.entity.video;

import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
public class Issue {

    //private long publishTime; //发布时间
    private int count; //List<Item> itemList中Item的个数
    private List<Item> itemList;


    public int getCount() {
        return count;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
