package com.grace.zhihunews.network.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */
public class Benefit {

    private Boolean error;

    private List<Girl> results;

    public void setError(Boolean error) {
        this.error = error;
    }

    public void setResults(List<Girl> girls) {
        this.results = girls;
    }

    public Boolean getError() {
        return error;
    }

    public List<Girl> getResults() {
        return results;
    }

}
