package com.grace.zhihunews.network.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
public class TopStory implements Serializable {

    private int type;
    private int id;
    private  String ga_prefix;
    private String title;
    private String image;

    public void setType(int type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }
}
