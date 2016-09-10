package com.grace.zhihunews.network.entity.video;

/**
 * Created by Administrator on 2016/9/10.
 */
public class Category {

    private int id;
    private String name;
    private Object alias;
    private String bgPicture;
    private String bgColor;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Object getAlias() {
        return alias;
    }

    public String getBgPicture() {
        return bgPicture;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(Object alias) {
        this.alias = alias;
    }

    public void setBgPicture(String bgPicture) {
        this.bgPicture = bgPicture;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }
}
