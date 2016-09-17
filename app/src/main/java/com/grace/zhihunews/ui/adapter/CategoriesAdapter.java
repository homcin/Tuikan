package com.grace.zhihunews.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.grace.zhihunews.R;
import com.grace.zhihunews.network.entity.video.Category;
import com.zanlabs.widget.infiniteviewpager.InfinitePagerAdapter;

import java.util.List;

import io.vov.vitamio.utils.Log;

/**
 * Created by Administrator on 2016/9/17.
 */
public class CategoriesAdapter extends InfinitePagerAdapter {

    private Context mContext;
    private List<Category> mCategories;

    public CategoriesAdapter(Context mContext, List<Category> mCategories) {
        this.mContext = mContext;
        this.mCategories = mCategories;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        GridView gridView = (GridView) LayoutInflater.from(mContext).inflate(R.layout.item_viewpager, container, false);
        gridView.setAdapter(new GridViewAdapter(mContext, mCategories, position));
        return gridView;
    }

    @Override
    public int getItemCount() {
        int pageSize = 8;
        int pageCount = (int) Math.ceil(mCategories.size() * 1.0 / pageSize);
        return pageCount;
    }
}
