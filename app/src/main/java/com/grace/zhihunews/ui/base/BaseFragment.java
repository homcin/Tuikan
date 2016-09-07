package com.grace.zhihunews.ui.base;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grace.zhihunews.R;

/**
 * Created by Administrator on 2016/9/6.
 */
public abstract  class BaseFragment extends Fragment {

    protected abstract int getLayoutResId();

    protected abstract void initVariables();

    protected abstract void initViews(View view, Bundle savedInstanceState);

    protected abstract void loadData();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        initVariables();
        initViews(view, savedInstanceState);
        loadData();
        return view;
    }

}
