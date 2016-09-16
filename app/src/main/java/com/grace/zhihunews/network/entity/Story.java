package com.grace.zhihunews.network.entity;

import com.litesuits.orm.db.annotation.Collate;
import com.litesuits.orm.db.annotation.Ignore;
import com.litesuits.orm.db.annotation.MapCollection;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.annotation.Unique;
import com.litesuits.orm.db.annotation.UniqueCombine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
public class Story extends BaseEntity implements Serializable {

    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    private List<String> images;

    private String date;

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

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setDate(String date) {
        this.date = date;
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

    public List<String> getImages() {
        return images;
    }

    public String getDate() {
        return date;
    }
}
