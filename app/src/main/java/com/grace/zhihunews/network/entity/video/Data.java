package com.grace.zhihunews.network.entity.video;

/**
 * Created by Administrator on 2016/9/9.
 */
public class Data {

    private int id; //id编号
    private long date; //日期
    private int idx; //index
    private String title; //标题
    private String description; //描述
    private String category; //分类
    private int duration; //视频时长
    private String playUrl;
    private Consumption consumption; //用户体验统计类：包含收藏个数、分享次数、评论个数
    private Cover cover; //封面类

    private String text; //如"- Sep. 09 -", item类型为textHeader时的Data成员text
    private String image;

    public int getId() {
        return id;
    }

    public long getDate() {
        return date;
    }

    public int getIdx() {
        return idx;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getDuration() {
        return duration;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public Consumption getConsumption() {
        return consumption;
    }

    public Cover getCover() {
        return cover;
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public void setConsumption(Consumption consumption) {
        this.consumption = consumption;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static class Consumption {
        private int collectionCount; //收藏个数
        private int shareCount; //分享次数
        private int replyCount; //评论个数

        public void setCollectionCount(int collectionCount) {
            this.collectionCount = collectionCount;
        }

        public void setShareCount(int shareCount) {
            this.shareCount = shareCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public int getCollectionCount() {
            return collectionCount;
        }

        public int getShareCount() {
            return shareCount;
        }


        public int getReplyCount() {
            return replyCount;
        }
    }

    public static class Cover {
        private String feed;
        private String detail;
        private String blurred;
        private String sharing;

        public void setFeed(String feed) {
            this.feed = feed;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public void setBlurred(String blurred) {
            this.blurred = blurred;
        }

        public void setSharing(String sharing) {
            this.sharing = sharing;
        }

        public String getFeed() {
            return feed;
        }

        public String getDetail() {
            return detail;
        }

        public String getBlurred() {
            return blurred;
        }

        public String getSharing() {
            return sharing;
        }
    }

}

