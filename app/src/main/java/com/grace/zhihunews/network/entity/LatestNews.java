package com.grace.zhihunews.network.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */
/**
 * http://news-at.zhihu.com/api/4/news/latest返回的JSON字符串对应的实体类。
 */
public class LatestNews implements Serializable {

    private String date;
    private List<Story> stories;
    private List<TopStory> top_stories;

    public void setDate(String date) {
        this.date = date;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public void setTop_stories(List<TopStory> topStories) {
        this.top_stories = topStories;
    }

    public String getDate() {
        return date;
    }

    public List<Story> getStories() {
        return stories;
    }

    public List<TopStory> getTopStories() {
        return top_stories;
    }


    /*
    public static class Story implements Serializable {
        private int type;
        private int id;
        private  String ga_prefix;
        private String title;
        private List<String> images;

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
    }
    */

    /*
    public static class TopStory implements Serializable {
        private int type;
        private int id;
        private  String ga_prefix;
        private String title;
        private List<String> images;

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
    }
    */
}
