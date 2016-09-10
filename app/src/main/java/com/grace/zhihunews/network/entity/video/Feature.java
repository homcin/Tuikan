package com.grace.zhihunews.network.entity.video;

import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
public class Feature { //精选页类

    private String nextPageUrl; //下一页精选页的Url
    private List<Issue> issueList;

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public List<Issue> getIssueList() {
        return issueList;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public void setIssueList(List<Issue> issueList) {
        this.issueList = issueList;
    }
}
